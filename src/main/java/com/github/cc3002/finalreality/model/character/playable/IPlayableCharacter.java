package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a playable character of the game,
 *  and each functionality that a generic one, should have.
 *
 * @author Adrian Arellano
 */
public interface IPlayableCharacter extends ICharacter {

  /* Getters */

  /** Return this PlayableCharacter's equipped weapon. */
  IWeapon getEquippedWeapon();


  /* Setters */

  /** Equips a weapon to the PlayableCharacter. */
  void equip(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon;


    /* Double Dispatch of "equip" */

  /** Indicates that the Weapon which is trying to be equip, is a Axe. */
  void equipAnAxe(@NotNull final Axe anAxe) throws UnsupportedWeapon, NonAvailableWeapon;

  /** Indicates that the Weapon which is trying to be equip, is a Bow. */
  void equipABow(@NotNull final Bow aBow) throws UnsupportedWeapon, NonAvailableWeapon;

  /** Indicates that the Weapon which is trying to be equip, is a Knife. */
  void equipAKnife(@NotNull final Knife aKnife) throws UnsupportedWeapon, NonAvailableWeapon;

  /** Indicates that the Weapon which is trying to be equip, is a Staff. */
  void equipAStaff(@NotNull final Staff aStaff) throws UnsupportedWeapon, NonAvailableWeapon;

  /** Indicates that the Weapon which is trying to be equip, is a Sword. */
  void equipASword(@NotNull final Sword aSword) throws UnsupportedWeapon, NonAvailableWeapon;
}
