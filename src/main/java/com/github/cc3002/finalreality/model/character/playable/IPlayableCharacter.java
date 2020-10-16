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
  void equip(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior;


    /* Double Dispatch of "equip" */

  /**
   * If this character has an {@param equippedWeapon}, the unEquips it
   *  and, no matter what, sets {@param aWeapon} as the new equipped
   *  weapon of this playable character.
   */
  void actuallyEquip(@NotNull final IWeapon aWeapon) throws NonAvailableWeapon, UnexpectedBehavior;

}
