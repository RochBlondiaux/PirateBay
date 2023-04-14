package me.rochblondiaux.piratebay.entities.animation;

import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.CreatureAnimationController;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.piratebay.entities.Player;

/**
 * ZurviveValley
 * 04/11/2022
 *
 * @author Roch Blondiaux (Kiwix).
 */
public class PlayerAnimationController extends CreatureAnimationController<Player> {

    public PlayerAnimationController(Player creature) {
        super(creature, true);

        // Custom animations
        registerAnimations("jump");
        registerAnimations("fall");
        registerAnimations("ground");

        // Rules
        addRule(player -> player.movement().getVelocity() == 0, player -> "player-idle-%s".formatted(player.getFacingDirection().toString().toLowerCase()));
        addRule(player -> player.getJumpAbility().isActive(), player -> "player-jump-%s".formatted(player.getFacingDirection().toString().toLowerCase()));
        addRule(player -> !player.isOnGround(), player -> "player-fall-%s".formatted(player.getFacingDirection().toString().toLowerCase()));
    }

    private void registerAnimations(String action) {
        Animation rollAnimation = new Animation(Resources.spritesheets().get("player-%s-right".formatted(action)), false, false);
        add(rollAnimation);
        add(flipAnimation(rollAnimation, "player-%s-left".formatted(action)));
    }

    @Override
    public boolean isPlaying(String animationName) {
        return this.getCurrent() != null && this.getCurrent().getName() != null && (this.getCurrent().getName().equalsIgnoreCase(animationName) && this.getCurrent().getName().contains(animationName));
    }
}
