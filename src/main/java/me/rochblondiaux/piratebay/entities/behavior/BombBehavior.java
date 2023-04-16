package me.rochblondiaux.piratebay.entities.behavior;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.behavior.IBehaviorController;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.AnimationListener;
import me.rochblondiaux.piratebay.entities.environment.Bomb;

/**
 * PirateBay
 * 15/04/2023
 *
 * @author Roch Blondiaux (Kiwix).
 */
public class BombBehavior implements IBehaviorController {

    private final Bomb bomb;
    private final AnimationListener listener;
    private static final long DELAY = 3_000;
    private long onSince;
    private boolean exploded;


    public BombBehavior(Bomb bomb) {
        this.bomb = bomb;
        this.exploded = false;

        this.listener = new AnimationListener() {
            @Override
            public void finished(Animation animation) {
                if (animation.getName().contains("destroyed") && !bomb.isDead())
                    bomb.die();
            }
        };
        bomb.animations().addListener(listener);
    }

    @Override
    public void update() {
        switch (bomb.getBombStatus()) {
            case OFF -> {
                if (bomb.getEnvironment()
                        .getCombatEntities()
                        .stream()
                        .filter(iCombatEntity -> !iCombatEntity.equals(bomb))
                        .anyMatch(iCombatEntity -> iCombatEntity.getLocation().distance(bomb.getLocation()) < 130)) {
                    bomb.setBombStatus(Bomb.Status.ON);
                    onSince = Game.time().now();
                }
            }
            case ON -> {
                if (bomb.getEnvironment()
                        .getCombatEntities()
                        .stream()
                        .noneMatch(iCombatEntity -> iCombatEntity.getLocation().distance(bomb.getLocation()) < 130)) {
                    onSince = 0;
                    bomb.setBombStatus(Bomb.Status.OFF);
                    return;
                }

                if (Game.time().since(onSince) > DELAY)
                    bomb.setBombStatus(Bomb.Status.EXPLODING);
            }
            case EXPLODING -> {
                if (bomb.isDead() && !exploded) {
                    double x = bomb.getX();
                    double y = bomb.getY();

                    bomb.animations().removeListener(listener);
                    bomb.getEnvironment().remove(bomb);
                    bomb.detachControllers();
                    exploded = true;

                    // TOOD: Apply explosion force to all the nearby entities
                }
            }
        }

    }



    @Override
    public IEntity getEntity() {
        return bomb;
    }
}
