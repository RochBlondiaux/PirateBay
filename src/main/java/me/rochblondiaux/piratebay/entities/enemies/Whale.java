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
@EntityInfo(width = 68, height = 48)
@CollisionInfo(collision = true, collisionBoxWidth = 68, collisionBoxHeight = 48)
@MovementInfo(velocity = 100)
public class Whale extends Creature {

    public Whale() {
        super("whale");
    }
}
