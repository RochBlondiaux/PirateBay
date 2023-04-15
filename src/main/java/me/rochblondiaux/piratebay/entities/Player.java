package me.rochblondiaux.piratebay.entities;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import de.gurkenlabs.litiengine.graphics.emitters.Emitter;
import de.gurkenlabs.litiengine.graphics.emitters.particles.SpriteParticle;
import de.gurkenlabs.litiengine.physics.Collision;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resources;
import lombok.Getter;
import lombok.Setter;
import me.rochblondiaux.piratebay.entities.abilities.JumpAbility;
import me.rochblondiaux.piratebay.entities.animation.PlayerAnimationController;
import me.rochblondiaux.piratebay.entities.controllers.PlayerMovementController;
import me.rochblondiaux.piratebay.entities.emitter.FallParticleSpawner;
import me.rochblondiaux.piratebay.entities.emitter.WalkParticleSpawner;

import java.awt.geom.Rectangle2D;

/**
 * PirateBay
 * 14/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
@EntityInfo(width = 58, height = 58)
@CollisionInfo(collision = true, collisionBoxWidth = 58, collisionBoxHeight = 60)
@MovementInfo(velocity = 250)
@AnimationInfo(spritePrefix = "player")
@Getter
public class Player extends Creature implements IUpdateable {

    private static Player instance;
    private static final int MAX_ADDITIONAL_JUMPS = 2;

    private JumpAbility jumpAbility;

    // State
    private int consecutiveJumps = 0;
    private boolean onGround = false;
    @Setter
    private boolean isEnteringDoor = false, isExitingDoor = false;
    @Setter
    private boolean controlsEnabled;

    public Player() {
        super("player");
        if (instance != null)
            throw new IllegalStateException("There can only be one player.");
        else
            instance = this;

        this.jumpAbility = new JumpAbility(this);
        this.controlsEnabled = true;
        animations().scaleSprite(2);

        onMoved(new WalkParticleSpawner());
    }

    @Override
    public void update() {
        boolean isTouchingGround = isTouchingGround();
        if (!this.onGround && isTouchingGround) {
            animations().play("player-ground-%s".formatted(getFacingDirection().toString().toLowerCase()));


            Game.world().environment().add(new FallParticleSpawner(this));
        }
        onGround = isTouchingGround;

        if (this.onGround)
            this.consecutiveJumps = 0;
    }

    @Action(description = "This performs the jump ability for the player's entity.")
    public void jump() {
        if (this.consecutiveJumps >= MAX_ADDITIONAL_JUMPS || !this.jumpAbility.canCast())
            return;

        this.jumpAbility.cast();
        this.consecutiveJumps++;
    }

    private boolean isTouchingGround() {
        Rectangle2D groundCheck = new Rectangle2D.Double(this.getCollisionBox().getX(), this.getCollisionBox().getY(), this.getCollisionBoxWidth(), this.getCollisionBoxHeight() + 1);
        if (groundCheck.getMaxY() > Game.physics().getBounds().getMaxY())
            return true;
        return Game.physics().collides(groundCheck, Collision.STATIC);
    }


    public static Player get() {
        if (instance == null)
            instance = new Player();
        return instance;
    }

    @Override
    protected IMovementController createMovementController() {
        // setup movement controller
        return new PlayerMovementController(this);
    }

    @Override
    protected IEntityAnimationController<?> createAnimationController() {
        return new PlayerAnimationController(this);
    }
}
