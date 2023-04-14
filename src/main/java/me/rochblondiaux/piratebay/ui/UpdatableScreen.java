package me.rochblondiaux.piratebay.ui;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.gui.screens.Screen;
import lombok.extern.java.Log;

import java.util.logging.Level;

/**
 * AncientValley
 *
 * @author Roch Blondiaux
 * @date 05/10/2022
 */
@Log
public abstract class UpdatableScreen extends Screen implements IUpdateable {

    public UpdatableScreen(String screenName) {
        super(screenName);
    }

    @Override
    public void prepare() {
        super.prepare();

        log.log(Level.INFO, String.format("Attaching screen %s to main loop.", this.getName()));
        Game.loop().attach(this);
    }

    @Override
    public void suspend() {
        super.suspend();

        log.log(Level.INFO, String.format("Detaching screen %s to main loop.", this.getName()));
        Game.loop().detach(this);
    }
}