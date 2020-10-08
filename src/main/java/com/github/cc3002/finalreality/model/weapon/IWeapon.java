package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.*;
import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a weapon in the game, and each
 *  functionality that a generic weapon, should have.
 *
 * @author Adrian Arellano
 */
public interface IWeapon {

  /* Getters */

  /** Returns the name of the Weapon. */
  String getName();

  /** Returns the damage of the Weapon. */
  int getDamage();

  /** Returns the weight of the Weapon. */
  int getWeight();

  /** Returns the currentCarrier of the Weapon. */
  IPlayableCharacter getCurrentCarrier();


    /* Double Dispatch of "equip" */

  /**
   * Used in the double dispatch of
   *  {@code IPlayableCharacter.equip(IWeapon aWeapon)}
   *  to let the IPlayableCharacter knows this IWeapon sub-Type.
   */
  void tryingToBeEquippedBy(@NotNull final IPlayableCharacter aPlayableCharacter) throws UnsupportedWeapon, NonAvailableWeapon;

    /* Setters use in that Double Dispatch. */

  /**
   * Checks if this weapon has a carrier:
   *  throws an {@exception NonAvailableWeapon} if it has
   *  sets the {@param aPlayableCharacter} as the carrier if not.
   */
  void equippedBy(@NotNull final IPlayableCharacter newCarrier) throws NonAvailableWeapon;

  /**
   * Sets the currentCarrier of this weapon as null.
   *  For security it only works, when the {@param supposedCarrier}
   *  actually is the currentCarrier of the Weapon.
   */
  void unEquippedBy(@NotNull final IPlayableCharacter supposedCarrier);

}
