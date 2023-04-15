package me.rochblondiaux.piratebay.entities.animation;

import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.PropAnimationController;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.piratebay.entities.environment.Bottle;

import java.awt.image.BufferedImage;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
public class BottleAnimationController extends PropAnimationController<Bottle> {

    private final Spritesheet spritesheet;

    /**
     * Initializes a new instance of the {@code PropAnimationController} class.
     *
     * @param prop The prop related to this controller.
     */
    public BottleAnimationController(Bottle prop) {
        super(prop);
        this.spritesheet = Resources.spritesheets().get("prop-bottles-intact");
    }

    @Override
    public BufferedImage getCurrentImage() {
        return switch (this.getEntity().getType()) {
            case 2 -> this.spritesheet.getSprite(1);
            case 3 -> this.spritesheet.getSprite(2);
            default -> this.spritesheet.getSprite(0);
        };
    }
}
