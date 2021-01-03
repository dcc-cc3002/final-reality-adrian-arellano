package com.github.cc3002.finalreality.model.weapon;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is a factory of {@code ICharacter}s, the idea of been a factory is to create new
 *  characters, been sure that they are all different.
 * This factory implements a Flyweight Pattern.
 *
 * @author Adrian Arellano.
 *
 * @see com.github.cc3002.finalreality.model.character.CharacterFactory : similar to this.
 */
public class WeaponFactory {

  private final Set<IWeapon> inventory = new HashSet<>();

  /**
   * Creates a new character's factory.
   */
  public WeaponFactory() {
  }

  /**
   * Adds the given {@code IWeapon} character to the set {@code inventory}, if and only if the
   *  weapon was not present before.
   *
   * @param newWeapon : the weapon to add.
   *
   * @return True if the weapon was not created before, an false if it was.
   */
  private boolean addWeapon(@NotNull final IWeapon newWeapon) {
    return inventory.add(newWeapon);
  }

  /**
   * Creates a new Axe, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Axe createAnAxe(@NotNull final String name, final int damage, final int weight) {
    Axe newAxe = new Axe(name, damage, weight);
    if (addWeapon(newAxe)) {
      return newAxe;
    }
    return null;
  }

  /**
   * Creates a new Bow, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Bow createABow(@NotNull final String name, final int damage, final int weight) {
    Bow newBow = new Bow(name, damage, weight);
    if (addWeapon(newBow)) {
      return newBow;
    }
    return null;
  }

  /**
   * Creates a new Knife, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Knife createAKnife(@NotNull final String name, final int damage, final int weight) {
    Knife newKnife = new Knife(name, damage, weight);
    if (addWeapon(newKnife)) {
      return newKnife;
    }
    return null;
  }

  /**
   * Creates a new Staff, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Staff createAStaff(@NotNull final String name, final int damage, final int weight) {
    Staff newStaff = new Staff(name, damage, weight);
    if (addWeapon(newStaff)) {
      return newStaff;
    }
    return null;
  }

  /**
   * Creates a new Sword, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Sword createASword(@NotNull final String name, final int damage, final int weight) {
    Sword newSword = new Sword(name, damage, weight);
    if (addWeapon(newSword)) {
      return newSword;
    }
    return null;
  }

}
