package me.rochblondiaux.piratebay.entities.environment;

import de.gurkenlabs.litiengine.entities.AnimationInfo;
import de.gurkenlabs.litiengine.entities.Prop;
import de.gurkenlabs.litiengine.graphics.animation.IEntityAnimationController;
import lombok.Getter;
import lombok.Setter;
import me.rochblondiaux.piratebay.entities.animation.BombAnimationController;
import me.rochblondiaux.piratebay.entities.behavior.BombBehavior;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
@AnimationInfo(spritePrefix = "prop-bomb")
public class Bomb extends Prop {

    @Getter
    @Setter
    private Status bombStatus;

    public Bomb() {
        super("bomb");
        this.bombStatus = Status.OFF;


        // Controllers
        addController(new BombBehavior(this));

        attachControllers();
    }

    @Override
    protected IEntityAnimationController<?> createAnimationController() {
        return new BombAnimationController(this);
    }


    public enum Status {
        OFF,
        ON,
        EXPLODING
    }
}
