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
@EntityInfo(width = 63, height = 67)
@CollisionInfo(collision = true, collisionBoxWidth = 63, collisionBoxHeight = 67)
@MovementInfo(velocity = 100)
public class Pirate extends Creature {

    public Pirate() {
        super("pirate");
    }
}
