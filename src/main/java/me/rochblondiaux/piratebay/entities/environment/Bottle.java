package me.rochblondiaux.piratebay.entities.environment;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import lombok.Getter;
import me.rochblondiaux.piratebay.entities.animation.BottleAnimationController;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
@AnimationInfo(spritePrefix = "prop-bottles")
public class Bottle extends Prop {

    @Getter
    private final int type;

    public Bottle() {
        super("bottles");
        this.type = this.getProperties().getIntValue("type");
    }


    @Override
    protected IEntityAnimationController<?> createAnimationController() {
        return new BottleAnimationController(this);
    }
}
