package me.rochblondiaux.piratebay.util;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * ZurviveValley
 * 03/11/2022
 *
 * @author Roch Blondiaux (Kiwix).
 */
@UtilityClass
public class WindowUtils {

    public static @NotNull Dimension screenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static int screenWidth() {
        return screenSize().width;
    }

    public static int screenHeight() {
        return screenSize().height;
    }

    public static @NotNull Point screenCenter() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Container container = Game.window().getHostControl();

        int x = (int) ((dimension.getWidth() - container.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - container.getHeight()) / 2);
        return new Point(x, y);
    }

    public static @NotNull Resolution fullScreenResolution() {
        return Resolution.custom(screenWidth(), screenHeight(), "fullscreen");
    }
}
