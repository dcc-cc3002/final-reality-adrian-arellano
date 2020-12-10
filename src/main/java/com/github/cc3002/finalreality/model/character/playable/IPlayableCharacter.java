package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.controller.PlayableKoHandler;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * This interface represents a playable character of the game, and each functionality that any
 *  character should have.
 *
 * @author Adrian Arellano.
 */
public interface IPlayableCharacter extends ICharacter {

  /* Observer Pattern */

  /**
   * Allows a {@code PlayableKoHandler} subscribe itself to this publisher.
   * We only let this kind of listener to subscribe, to have no problems with typing.
   */
  void addPlayableKoListener(@NotNull final PlayableKoHandler listener);


  /* Getters */

  /** Return this PlayableCharacter's equipped weapon. */
  IWeapon getEquippedWeapon();


  /* Setters */

  /**
   * Tries to equip a weapon to the PlayableCharacter.
   * If this character is K.O., it can't, so nothing will happen.
   *
   * @param aWeapon : the weapon which this method will try to equip (to this character).
   *
   * @throws NonAvailableWeapon : when the given weapon is been carried by another character.
   * @throws UnsupportedWeapon  : when the given weapon can not be used by this kind of character.
   * @throws UnexpectedBehavior : when something else went wrong.
   */
  void equip(@NotNull final IWeapon aWeapon) throws NonAvailableWeapon, UnexpectedBehavior,
      UnsupportedWeapon;


  /* Double Dispatch of "equip" */

  /**
   * If this character has a weapon equipped, this method will unequip it and, no matter what,
   *  will set the given weapon as the new {@code equippedWeapon} (inner parameter).
   *
   * @param aWeapon : the given weapon which will be the new currentWeapon of the character.
   *
   * @throws UnexpectedBehavior : exception thrown by {@link IWeapon#unEquippedBy(IPlayableCharacter
   *                              )} but not caught by this method.
   *
   * @see #equip(IWeapon)
   */
  void actuallyEquip(@NotNull final IWeapon aWeapon) throws UnexpectedBehavior;

}
