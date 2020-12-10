package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playable.AbstractPlayableCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a character of the game, and implements each functionality that any
 *  character should have.
 *
 * @author Ignacio Slater Muñoz.
 * @author Adrian Arellano.
 */
public interface ICharacter {

  /* Getters */

  /** Returns this character's name. */
  String getName();

  /** Returns this character's max health points. */
  int getMaxHp();

  /** Returns this character's current health points. */
  int getCurrentHp();

  /** Returns this character's defense. */
  int getDef();

  /**
   * Returns this character's current attack power.
   *
   * If this character is an {@code IPlayableCharacter}, and it doesn't have a weapon equipped, an
   *  {@exception NonEquippedWeapon} will be raised ({@link AbstractPlayableCharacter#getAtk()}).
   *
   * @see #attack(ICharacter)
   */
  int getAtk() throws NonEquippedWeapon;

  /**
   * Returns this character's current weight.
   * This info is used in the delay calculus.
   *
   * If this character is a {@code IPlayableCharacter}, and it doesn't have a weapon equipped, an
   *  {@exception NonEquippedWeapon} will be raised ({@link AbstractPlayableCharacter#getWeight()}).
   *
   * @see #waitTurn()
   */
  int getWeight() throws NonEquippedWeapon;

  /** Returns true is this character is out of combat, and false if not. */
  boolean isKo();

  /**
   * Returns true if this character is an instance of {@code IPlayableCharacter}, and false if not.
   */
  boolean isPlayable();


  /**
   * [This character] tries to attack the opponent.
   * If this character is K.O. can not attack.
   *
   * @param opponent : the character which receives the attack.
   *
   * @throws NonEquippedWeapon : possible exception thrown by {@code getAtk()}, this method does not
   *                             catch this exception.
   *
   * @see #getAtk()
   */
  void attack(@NotNull final ICharacter opponent) throws NonEquippedWeapon;

  /**
   * [This character] receives an attack with determinate attack power, and decreases the this
   *  character's HP according to this character's defense and the power of the attack.
   *
   * @param atkPower : the power or points of the attack which is received.
   */
  void receiveAtk(int atkPower);

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10} seconds
   *  before adding the character to the queue.
   *
   * @throws NonEquippedWeapon : possible exception thrown by {@code getAtk()}, this method does not
   *                             catch this exception.
   *
   * @see #getWeight()
   */
  void waitTurn() throws NonEquippedWeapon;

}
