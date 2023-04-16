package me.rochblondiaux.piratebay.entities.enemies;

import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;

/**
 * PirateBay
 * 16/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
@EntityInfo(width = 77, height = 74)
@CollisionInfo(collision = true, collisionBoxWidth = 77, collisionBoxHeight = 74)
@MovementInfo(velocity = 100)
public class Sailer extends Creature {

    public Sailer() {
        super("sailer");
    }
}
