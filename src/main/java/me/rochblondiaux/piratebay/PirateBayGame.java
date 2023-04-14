package me.rochblondiaux.piratebay;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameInfo;
import de.gurkenlabs.litiengine.GameWindow;
import de.gurkenlabs.litiengine.gui.screens.Resolution;
import de.gurkenlabs.litiengine.gui.screens.ScreenManager;
import lombok.extern.java.Log;
import me.rochblondiaux.piratebay.core.GameManager;
import me.rochblondiaux.piratebay.ui.InGameScreen;
import me.rochblondiaux.piratebay.util.ErrorUtils;
import me.rochblondiaux.piratebay.util.WindowUtils;

import javax.swing.*;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;

/**
 * PirateBay
 * 14/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
@Log(topic = "PirateBay")
public class PirateBayGame {

    public static final Path DATA_FOLDER = Path.of(System.getProperty("user.home") + "/.piratebay/");

    public PirateBayGame(String[] args) {
        initialize(args);
    }

    private void initialize(String[] args) {
        registerGameInformation();

        Game.init(args);

        writeLckFile();
        initializeWindow();
        initInputs();
        registerScreens();

        Game.start();
    }

    private void registerGameInformation() {
        log.info("Registering game information...");
        GameInfo info = new GameInfo();
        info.setDevelopers("Rocco");
        info.setName("Pirate Bay");
        info.setSubTitle("Made by Rocco!");
        info.setVersion("v1.0.0");
        info.setWebsite("https://www.roch-blondiaux.net");
        Game.setInfo(info);

        log.info("Game information registered.");
    }

    private void registerScreens() {
        log.info("Registering game screens...");
        ScreenManager manager = Game.screens();
        manager.add(new InGameScreen());
        log.info("Game screens registered.");

        GameManager.launchGame();
    }

    private void initializeWindow() {
        final GameWindow window = Game.window();
        final JFrame frame = (JFrame) window.getHostControl();

        log.info("Initializing window...");

        // Frame
//        frame.dispose();
//        frame.setUndecorated(true);
//        frame.setVisible(true);

        // Title & Resolution
        window.setTitle("Pirate Bay");
        window.setResolution(Resolution.Ratio16x9.RES_1360x768);

        // Center window
        frame.setLocation(WindowUtils.screenCenter());

        // Logo
        // window.setIcon(Resources.images().get("logo"));

        // Cursor
        // Game.window().cursor().set(Resources.images().get("cursor.normal"));
        log.info("Window initialized!");
    }

    private void initInputs() {
        log.info("Initializing inputs...");
//        InputManager.init();
//        InputManager.load();
        log.info("Inputs initialized!");
    }

    private void writeLckFile() {
        if (!Files.isDirectory(DATA_FOLDER)) {
            try {
                Files.createDirectories(DATA_FOLDER);
            } catch (IOException e) {
                log.log(Level.SEVERE, "Failed to create data folder.", e);
            }
        }

        try {
            FileChannel fc = FileChannel.open(DATA_FOLDER.resolve("game.lock"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            FileLock lock = fc.tryLock();
            if (lock == null)
                ErrorUtils.displayErrorAndExit("Another instance of PirateBay is already running.");
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}
