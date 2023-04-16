package me.rochblondiaux.piratebay.entities.emitter;

import de.gurkenlabs.litiengine.Direction;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityMovedEvent;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.Emitter;
import de.gurkenlabs.litiengine.graphics.emitters.particles.SpriteParticle;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.piratebay.entities.Player;

import java.awt.geom.Point2D;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
public class WalkParticleSpawner implements IMobileEntity.EntityMovedListener {
    private long lastWalkDust;

    @Override
    public void moved(EntityMovedEvent event) {
        final int STEP_DELAY = 250;
        if (event.getDistance() == 0
                || Game.world().environment() == null
                || (event.getEntity() instanceof Player player && (!player.isOnGround() || player.getJumpAbility().isActive())))
            return;
        if (Game.time().since(this.lastWalkDust) < STEP_DELAY) {
            return;
        }

        Spritesheet walkDustSprite = Resources.spritesheets().get("player-walk-particles");
        this.lastWalkDust = Game.loop().getTicks();

        double x = event.getEntity().getCollisionBoxCenter().getX() - walkDustSprite.getSpriteWidth() / 4.0;
        if (event.getEntity() instanceof Creature creature && creature.getFacingDirection().equals(Direction.LEFT))
            x = event.getEntity().getCollisionBoxCenter().getX() + walkDustSprite.getSpriteWidth() / 4.0;
        double y = event.getEntity().getCollisionBox().getMinY() + walkDustSprite.getSpriteHeight() / 8.0;
        Point2D walkLocation = new Point2D.Double(x, y);

        Emitter walkDust = new Emitter(walkLocation);
        SpriteParticle particle = new SpriteParticle(walkDustSprite);
        particle.setAnimateSprite(true);
        particle.setTimeToLive(500);
        walkDust.data().setMaxParticles(1);
        walkDust.data().setEmitterDuration(500);
        walkDust.addParticle(particle);
        walkDust.setRenderType(RenderType.NORMAL);

        Game.world().environment().add(walkDust);
    }

}