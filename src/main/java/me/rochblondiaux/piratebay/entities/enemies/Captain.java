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
@EntityInfo(width = 80, height = 72)
@CollisionInfo(collision = true, collisionBoxWidth = 80, collisionBoxHeight = 72)
@MovementInfo(velocity = 100)
public class Captain extends Creature {

    public Captain() {
        super("captain");
    }
}
