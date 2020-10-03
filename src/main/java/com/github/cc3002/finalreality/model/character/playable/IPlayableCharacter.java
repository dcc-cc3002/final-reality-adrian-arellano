package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.NonEquippableWeapon;

public interface IPlayableCharacter extends ICharacter {

  /* Getters */

  /** Return this PlayableCharacter's equipped weapon. */
  IWeapon getEquippedWeapon();


  /* Setters */

  /** Equips a weapon to the PlayableCharacter. */
  void equip(IWeapon aWeapon) throws NonEquippableWeapon, NonAvailableWeapon;

}
