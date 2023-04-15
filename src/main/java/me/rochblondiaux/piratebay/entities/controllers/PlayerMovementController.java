package me.rochblondiaux.piratebay.entities.controllers;

import de.gurkenlabs.litiengine.input.PlatformingMovementController;
import me.rochblondiaux.piratebay.entities.Player;

import java.awt.event.KeyEvent;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
public class PlayerMovementController extends PlatformingMovementController<Player> {
    public PlayerMovementController(Player entity) {
        super(entity);
    }

    @Override
    public void handlePressedKey(KeyEvent keyCode) {
        if (getEntity().isControlsEnabled())
            super.handlePressedKey(keyCode);
    }
}
