package me.rochblondiaux.piratebay.entities.animation;

import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.AnimationListener;
import de.gurkenlabs.litiengine.graphics.animation.EntityAnimationController;
import me.rochblondiaux.piratebay.entities.environment.Door;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
public class DoorAnimationController extends EntityAnimationController<Door> {

    /**
     * Initializes a new instance of the {@code PropAnimationController} class.
     *
     * @param prop The prop related to this controller.
     */
    public DoorAnimationController(Door prop) {
        super(prop);
        scaleSprite(1.5f, 1.5f);

        this.setDefault(new Animation("prop-door-intact", false, false));
        this.add(new Animation("prop-door-damaged", false, false));
        this.add(new Animation("prop-door-destroyed", false, false));

        addRule(door -> door.getDoorState().equals(Door.State.CLOSED), door -> "prop-door-intact");
        addRule(door -> door.getDoorState().equals(Door.State.OPENING), door -> "prop-door-damaged");
        addRule(door -> door.getDoorState().equals(Door.State.CLOSING), door -> "prop-door-destroyed");

        addListener(new AnimationListener() {
            @Override
            public void finished(Animation animation) {
                if (animation.getName().equals("prop-door-damaged") || animation.getName().equals("prop-door-destroyed"))
                    DoorAnimationController.this.getEntity().setDoorState(Door.State.CLOSED);
            }
        });
    }
}
