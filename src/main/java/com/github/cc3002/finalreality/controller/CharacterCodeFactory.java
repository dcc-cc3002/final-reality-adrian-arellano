package com.github.cc3002.finalreality.controller;

/**
 * This class is a factory of {@code CharacterCode}, the idea of been a factory is to create new
 *  codes of characters, been sure that they are all different.
 * This factory implements a Flyweight Pattern.
 *
 * @author Adrian Arellano.
 */
public class CharacterCodeFactory {
  private int availableIndex = 1;

  /** Creates a new Factory of CharacterCodes. */
  public CharacterCodeFactory() {
  }

  /** Creates a new CharacterCode for a Playable Character. */
  public CharacterCode createPlayable() {
    return new CharacterCode(availableIndex++, true, false);
  }

  /** Creates a new CharacterCode for a Wizard Character. */
  public CharacterCode createWizard() {
    return new CharacterCode(availableIndex++, true, true);
  }

  /** Creates a new CharacterCode for an Enemy. */
  public CharacterCode createEnemy() {
    return new CharacterCode(availableIndex++, false, false);
  }

  /* Is impossible to have a CharacterCode where: isPlayable=false and isWizard=true. */

}
