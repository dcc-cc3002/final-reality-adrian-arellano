package com.github.cc3002.finalreality.controller;

/**
 * This class is a factory of {@code WeaponCode}, the idea of been a factory is to create new
 *  codes of weapons, been sure that they are all different.
 * This factory implements a Flyweight Pattern.
 *
 * @author Adrian Arellano.
 *
 * @see CharacterCodeFactory : this is a simplified copy of that.
 */
public class WeaponCodeFactory {
  private int availableIndex = 1;

  /** Creates a new Factory of WeaponCodes. */
  public WeaponCodeFactory() {
  }

  /** Creates a new WeaponCode. */
  public WeaponCode create() {
    return new WeaponCode(availableIndex++);
  }

}
