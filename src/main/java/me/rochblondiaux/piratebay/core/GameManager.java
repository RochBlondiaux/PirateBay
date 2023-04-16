package me.rochblondiaux.piratebay.core;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.Environment;
import de.gurkenlabs.litiengine.environment.EnvironmentListener;
import de.gurkenlabs.litiengine.environment.GameWorld;
import de.gurkenlabs.litiengine.environment.PropMapObjectLoader;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.resources.Resources;
import me.rochblondiaux.piratebay.entities.Player;
import me.rochblondiaux.piratebay.entities.environment.Bomb;
import me.rochblondiaux.piratebay.entities.environment.Bottle;
import me.rochblondiaux.piratebay.entities.environment.Door;

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

        // Props
        PropMapObjectLoader.registerCustomPropType(Door.class);
        PropMapObjectLoader.registerCustomPropType(Bottle.class);
        PropMapObjectLoader.registerCustomPropType(Bomb.class);

        world.addListener(new EnvironmentListener() {

            @Override
            public void loaded(Environment e) {
                final GameWorld world = Game.world();
                final Player player = Player.get();


                // Spawn
                Spawnpoint spawn = e.getSpawnpoint("spawnpoint");
                if (spawn == null)
                    throw new IllegalStateException("No spawn point detected!");

                // Camera
                Camera camera = new PositionLockCamera(player);
                world.setCamera(camera);
                camera.setZoom(.3f, 0);
                camera.setFocus(player.getCenter());

                // Player
                player.setControlsEnabled(false);

                Door door = (Door) e.getProp("start");

                if (door != null) {
                    player.setVisible(false);
                    spawn.spawn(player);
                    camera.setZoom(.8f, 750);
                    Game.loop().perform(750, () -> {
                        door.setDoorState(Door.State.OPENING);

                        Game.loop().perform(200, () -> {
                            player.setVisible(true);
                            player.animations().play("player-doorout-right");
                        });

                        camera.setClampToMap(true);
                        Game.loop().perform(1500, () -> {
                            camera.setZoom(.4f, 750);
                            player.setControlsEnabled(true);
                        });
                    });
                } else {
                    camera.setClampToMap(true);
                    spawn.spawn(player);
                }
            }
        });

        // Load the game world
        world.setGravity(400);
        world.loadEnvironment("lvl1");
    }
}
