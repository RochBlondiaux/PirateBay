package me.rochblondiaux.piratebay.util;

import de.gurkenlabs.litiengine.Game;
import lombok.experimental.UtilityClass;

import javax.swing.*;

/**
 * ZurviveValley
 * 04/11/2022
 *
 * @author Roch Blondiaux (Kiwix).
 */
@UtilityClass
public class ErrorUtils {

    public static void displayError(String message) {
        displayError("Pirate Bay", message);
    }

    public static void displayError(String title, String message) {
        JOptionPane.showMessageDialog(Game.window().getHostControl(),
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    public static void displayErrorAndExit(String message) {
        displayErrorAndExit("Pirate Bay", message);
    }

    public static void displayErrorAndExit(String title, String message) {
        ((JFrame) Game.window().getHostControl()).dispose();
        displayError(title, message);
        Game.exit();
    }

}
