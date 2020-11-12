package com.github.cc3002.finalreality.model.character.playable.wizard;

import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;

/**
 * This interface represents a wizard character of the game, and each functionality that any wizard
 *  should have.
 *
 * @author Adrian Arellano.
 */
public interface IWizard extends IPlayableCharacter {

  /* Getters */

  /** Returns this character's max mana points. */
  int getMaxMana();

  /** Returns this character's current health points. */
  int getCurrentMana();

}
