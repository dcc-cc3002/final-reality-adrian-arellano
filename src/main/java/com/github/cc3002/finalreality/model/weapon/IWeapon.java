package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.*;

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


  /*
   * These methods are supposed to be called, only
   *  by their respective IPlayableCharacter sub-class.
   */

  /** Indicates to the Weapon that a Knight is trying to take it. */
  void takenByKnight(Knight aKnight) throws NonEquippableWeapon, NonAvailableWeapon;

  /** Indicates to the Weapon that a Engineer is trying to take it. */
  void takenByEngineer(Engineer anEngineer) throws NonEquippableWeapon, NonAvailableWeapon;

  /** Indicates to the Weapon that a Thief is trying to take it. */
  void takenByThief(Thief aThief) throws NonEquippableWeapon, NonAvailableWeapon;

  /** Indicates to the Weapon that a BlackWizard is trying to take it. */
  void takenByBlackWizard(BlackWizard aBlackWizard) throws NonEquippableWeapon, NonAvailableWeapon;

  /** Indicates to the Weapon that a WhiteWizard is trying to take it. */
  void takenByWhiteWizard(WhiteWizard aWhiteWizard) throws NonEquippableWeapon, NonAvailableWeapon;

  /* Setter which is supposed to be called, only by a IPlayableCharacter sub-class. */
  /**
   * Sets the currentCarrier of this weapon as null.
   *  For security it only works, when the {@param supposedCarrier}
   *  actually is the currentCarrier of the Weapon.
   */
  void unEquippedBy(IPlayableCharacter supposedCarrier);

}
