package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.Enemy;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * This class is used by the controller to know when any enemy character of the game is knocked out;
 *  when all the enemies are knockout, the game has ended, and the Enemy has lost.
 *
 * @author Adrian Arellano.
 *
 * @see GameController
 * @see GameController#updateEnemyKo(Enemy)
 */
public class EnemyKoHandler implements PropertyChangeListener {

  private final GameController controller;

  /**
   * Creates a new EnemyKoHandler. Every handler has to have an associated controller.
   *
   * @param controller : the controller associated to this handler.
   */
  public EnemyKoHandler(@NotNull final GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(@NotNull final PropertyChangeEvent evt) {
    controller.updateEnemyKo((Enemy) evt.getNewValue());
  }

}
