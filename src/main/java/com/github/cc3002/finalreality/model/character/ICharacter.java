package com.github.cc3002.finalreality.model.character;

/**
 * This interface represents a character of the game,
 *  and each functionality that a generic character should have.
 *
 * @author Ignacio Slater Muñoz.
 * @author Adrian Arellano
 */
public interface ICharacter {

  /* Getters */

  /** Returns this character's name. */
  String getName();

  /**
   * Returns this character's weight to the calculus of the delay,
   *  in the case of a {@code IPlayableCharacter}, the weight could not exists,
   *  when that happens, the method throws an {@exception NonEquippedWeapon}.
   *
   * @see #waitTurn()
   */
  int getWeight() throws NonEquippedWeapon;


  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   *  seconds before adding the character to the queue.
   *
   * This method does not catch exception thrown by {@link #getWeight()}.
   */
  void waitTurn() throws NonEquippedWeapon;

}
