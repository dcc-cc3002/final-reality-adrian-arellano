package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * This class is used by the controller to know when any playable character of the game is knocked
 *  out; when all the playable characters are knockout, the game has ended, and the Player has lost.
 *
 * @author Adrian Arellano.
 *
 * @see GameController
 * @see GameController#updatePlayableKo(IPlayableCharacter)
 */
public class PlayableKoHandler implements PropertyChangeListener {

  private final GameController controller;

  /**
   * Creates a new PlayableKoHandler. Every handler has to have an associated controller.
   *
   * @param controller : the controller associated to this handler.
   */
  public PlayableKoHandler(@NotNull final GameController controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(@NotNull final PropertyChangeEvent evt) {
    controller.updatePlayableKo((IPlayableCharacter) evt.getNewValue());
  }

}
