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
   * @throws WeaponAlreadyCreated : when the given character was present in {@code inventory}.
   */
  private void addWeapon(@NotNull final IWeapon newWeapon)
      throws WeaponAlreadyCreated {

    if (! (inventory.add(newWeapon))) {
      throw new WeaponAlreadyCreated(newWeapon.getName());
    }
  }

  /**
   * Creates a new Axe, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Axe createAnAxe(@NotNull final String name, final int damage, final int weight)
      throws WeaponAlreadyCreated {
    Axe newAxe = new Axe(name, damage, weight);
    addWeapon(newAxe);
    return newAxe;
  }

  /**
   * Creates a new Bow, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Bow createABow(@NotNull final String name, final int damage, final int weight)
      throws WeaponAlreadyCreated {
    Bow newBow = new Bow(name, damage, weight);
    addWeapon(newBow);
    return newBow;
  }

  /**
   * Creates a new Knife, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Knife createAKnife(@NotNull final String name, final int damage, final int weight)
      throws WeaponAlreadyCreated {
    Knife newKnife = new Knife(name, damage, weight);
    addWeapon(newKnife);
    return newKnife;
  }

  /**
   * Creates a new Staff, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Staff createAStaff(@NotNull final String name, final int damage, final int weight)
      throws WeaponAlreadyCreated {
    Staff newStaff = new Staff(name, damage, weight);
    addWeapon(newStaff);
    return newStaff;
  }

  /**
   * Creates a new Sword, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addWeapon(IWeapon)
   */
  public Sword createASword(@NotNull final String name, final int damage, final int weight)
      throws WeaponAlreadyCreated {
    Sword newSword = new Sword(name, damage, weight);
    addWeapon(newSword);
    return newSword;
  }

}
