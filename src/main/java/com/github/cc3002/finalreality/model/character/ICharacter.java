package com.github.cc3002.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

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

  /** Returns this character's max health points. */
  int getMaxHp();

  /** Returns the current health points of this character. */
  int getCurrentHp();

  /** Returns this character's defense. */
  int getDef();

  /**
   * Returns this character's attack power.
   * If this character is a {@code IPlayableCharacter}, and it doesn't
   *  have a weapon, an {@exception NonEquippedWeapon} is raised.
   *
   * @see #attack(ICharacter)
   */
  int getAtk() throws NonEquippedWeapon;

  /**
   * Returns this character's weight. This info is used to the delay calculus.
   * If this character is a {@code IPlayableCharacter}, and it doesn't
   *  have a weapon, an {@exception NonEquippedWeapon} is raised.
   *
   * @see #waitTurn()
   */
  int getWeight() throws NonEquippedWeapon;


  /**
   * This characters attacks to the opponent.
   * If this character is K.O. can not attack.
   *
   * @param opponent : the character which receives the attack.
   *
   * @throws NonEquippedWeapon : does not catch this exception.
   *
   * @see #getAtk()
   */
  void attack(@NotNull final ICharacter opponent) throws NonEquippedWeapon;

  /**
   * Receive an Attack with and decreases the HP of this character according to that.
   *
   * @param damage : the points of the attack which is received.
   */
  void receiveAtk(final int damage);

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   *  seconds before adding the character to the queue.
   *
   * @throws NonEquippedWeapon : does not catch this exception.
   *
   * @see #getWeight()
   */
  void waitTurn() throws NonEquippedWeapon;

}
