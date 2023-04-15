package me.rochblondiaux.piratebay.entities.abilities;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.abilities.Ability;
import de.gurkenlabs.litiengine.abilities.AbilityInfo;
import de.gurkenlabs.litiengine.abilities.effects.Effect;
import de.gurkenlabs.litiengine.abilities.effects.EffectApplication;
import de.gurkenlabs.litiengine.abilities.effects.EffectTarget;
import de.gurkenlabs.litiengine.abilities.effects.ForceEffect;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.Emitter;
import de.gurkenlabs.litiengine.graphics.emitters.particles.SpriteParticle;
import de.gurkenlabs.litiengine.physics.Force;
import de.gurkenlabs.litiengine.physics.GravityForce;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.geom.Point2D;

/**
 * PirateBay
 * 14/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
@AbilityInfo(cooldown = 500, origin = EntityPivotType.COLLISIONBOX_CENTER, duration = 200, value = 750)
public class JumpAbility extends Ability {

    /**
     * Initializes a new instance of the {@code Ability} class.
     *
     * @param executor The executing entity
     */
    public JumpAbility(Creature executor) {
        super(executor);
        this.addEffect(new JumpEffect(this));
        this.addEffect(new JumpParticleEffect(this));
    }

    private static class JumpParticleEffect extends Effect {
        protected JumpParticleEffect(Ability ability) {
            super(ability, EffectTarget.EXECUTINGENTITY);
        }

        @Override
        protected void apply(ICombatEntity entity) {
            Spritesheet particleSprite = Resources.spritesheets().get("player-jump-particles");

            double x = entity.getCollisionBoxCenter().getX();
            double y = entity.getCollisionBox().getMinY() + particleSprite.getSpriteHeight() / 8.0;

            Emitter emitter = new Emitter(new Point2D.Double(x, y));
            SpriteParticle particle = new SpriteParticle(particleSprite);
            particle.setAnimateSprite(true);
            particle.setTimeToLive(500);
            emitter.addParticle(particle);
            emitter.setRenderType(RenderType.NORMAL);

            Game.world().environment().add(emitter);
        }
    }

    private static class JumpEffect extends ForceEffect {

        protected JumpEffect(Ability ability) {
            super(ability, ability.getAttributes().value().get().intValue(), EffectTarget.EXECUTINGENTITY);
        }

        @Override
        protected Force applyForce(IMobileEntity affectedEntity) {
            // create a new force and apply it to the player
            GravityForce force = new GravityForce(affectedEntity, this.getStrength(), Direction.UP);
            affectedEntity.movement().apply(force);
            return force;
        }

        @Override
        protected boolean hasEnded(final EffectApplication appliance) {
            return super.hasEnded(appliance) || this.isTouchingCeiling();
        }

        /**
         * Make sure that the jump is cancelled when the entity touches a static collision box above it.
         *
         * @return True if the entity touches a static collision box above it.
         */
        private boolean isTouchingCeiling() {
           return Game.world().environment()
                    .getCollisionBoxes()
                    .stream()
                    .filter(x -> x.getBoundingBox().intersects(this.getAbility().getExecutor().getBoundingBox()))
                    .findFirst()
                    .map(CollisionBox::getCollisionBox)
                    .map(x -> x.getMaxY() <= this.getAbility().getExecutor().getCollisionBox().getMinY())
                   .orElse(false);
        }
    }
}
