package com.github.cc3002.finalreality.model.character;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public interface ICharacter {

  /* Getters */

  /** Returns this character's name. */
  String getName();

  /* Getter related with the scheduleExecutor. */
  /* In the future this method will throw an Exception. */
  /** Returns this character's weight to the delay calculus. */
  int getWeight();


  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

}
