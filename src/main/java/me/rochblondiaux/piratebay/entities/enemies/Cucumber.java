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
@EntityInfo(width = 64, height = 68)
@CollisionInfo(collision = true, collisionBoxWidth = 64, collisionBoxHeight = 68)
@MovementInfo(velocity = 100)
public class Cucumber extends Creature {

    public Cucumber() {
        super("cucumber");
    }
}
