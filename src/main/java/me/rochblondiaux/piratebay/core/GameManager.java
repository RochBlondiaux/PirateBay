package me.rochblondiaux.piratebay.core;

import com.sun.source.tree.Tree;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.EnvironmentListener;
import de.gurkenlabs.litiengine.environment.GameWorld;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.piratebay.PirateBayGame;
import me.rochblondiaux.piratebay.entities.Player;

/**
 * PirateBay
 * 14/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
public class GameManager {

    public static void launchGame() {
        final GameWorld world = Game.world();

        // Game info
        Resources.load("game.litidata");

        // Spritesheets
        Resources.spritesheets().loadFrom("sprites/sprites.info");


        world.addListener(new EnvironmentListener() {

            @Override
            public void initialized(Environment e) {
                final GameWorld world = Game.world();
                final Player player = Player.get();

                Spawnpoint spawn = e.getSpawnpoint("spawnpoint");
                if (spawn != null)
                    spawn.spawn(player);
            }

            @Override
            public void loaded(Environment e) {
                final GameWorld world = Game.world();
                final Player player = Player.get();

                // Camera
                Camera camera = new PositionLockCamera(player);
                camera.setClampToMap(true);
                world.setCamera(camera);

                // Spawn
                Spawnpoint spawn = e.getSpawnpoint("spawnpoint");
                if (spawn != null) {
                    camera.setFocus(spawn.getCenter());
                    camera.setZoom(.3f, 0);
                }
            }
        });

        // Load the game world
        world.setGravity(350);
        world.loadEnvironment("lvl1");
    }
}
