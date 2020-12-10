package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.*;
import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a weapon in the game, and each
 *  functionality that a generic weapon, should have.
 *
 * @author Adrian Arellano.
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


  /* Setters use in that Double Dispatch. */

  /**
   * Sets the currentCarrier of this weapon as null.
   * For security it only works, when the given character, is actually the {@code currentCarrier}
   *  of this Weapon.
   *
   * @param supposedCarrier : the character which suppose to be the one who is carrying this weapon.
   */
  void unEquippedBy(@NotNull final IPlayableCharacter supposedCarrier) throws UnexpectedBehavior;


  /* Double Dispatch of "equip" */

  /** Let this weapon knows that an Engineer is trying to take it. */
  void equippedByAnEngineer(@NotNull final Engineer anEngineer) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon;

  /** Let this weapon knows that a Knight is trying to take it. */
  void equippedByAKnight(@NotNull final Knight aKnight) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon;

  /** Let this weapon knows that a Thief is trying to take it. */
  void equippedByAThief(@NotNull final Thief aThief) throws NonAvailableWeapon, UnexpectedBehavior,
      UnsupportedWeapon;

  /** Let this weapon knows that a Black Wizard is trying to take it. */
  void equippedByABlackWizard(@NotNull final BlackWizard aBlackWizard) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon;

  /** Let this weapon knows that a White Wizard is trying to take it. */
  void equippedByAWhiteWizard(@NotNull final WhiteWizard aWhiteWizard) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon;

}
