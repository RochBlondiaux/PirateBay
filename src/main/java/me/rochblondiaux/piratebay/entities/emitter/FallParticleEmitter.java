package me.rochblondiaux.piratebay.entities.emitter;

import de.gurkenlabs.litiengine.entities.EmitterInfo;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.emitters.Emitter;
import de.gurkenlabs.litiengine.graphics.emitters.particles.Particle;
import de.gurkenlabs.litiengine.graphics.emitters.particles.SpriteParticle;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.piratebay.entities.Player;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
@EmitterInfo(duration = 200, particleMaxTTL = 500, maxParticles = 1, spawnAmount = 1)
public class FallParticleEmitter extends Emitter {

    private final Spritesheet particleSprite;

    public FallParticleEmitter(Player player) {
        super();
        this.data().setEmitterDuration(500);
        this.data().setMaxParticles(1);

        this.particleSprite = Resources.spritesheets().get("player-fall-particles");
        double x = player.getCollisionBox().getCenterX();
        double y = player.getCollisionBox().getMinY() + particleSprite.getSpriteHeight() / 8d;

        setLocation(x, y);
    }



    @Override
    protected Particle createNewParticle() {
        SpriteParticle particle = new SpriteParticle(particleSprite);
        particle.setAnimateSprite(true);
        particle.setTimeToLive(500);
        return particle;
    }


}