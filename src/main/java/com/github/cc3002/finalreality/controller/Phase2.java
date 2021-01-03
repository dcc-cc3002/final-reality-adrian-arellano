package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.NonEquippedWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Phase2 of the game.
 * To know more about the implemented phases, please read the {@code README.md} of this project, the
 *  section {@code Tackling the problem of the phases}, explains deeply how this phases it works and
 *  why.
 *
 * @author Adrian Arellano.
 */
public class Phase2 extends GamePhase {

  @Override
  boolean isPhase(final int phaseNumber) {
    return phaseNumber == 2;
  }

  @Override
  public void nextPhase() {
    super.changeGamePhase(new Phase3());
  }

  @Override
  void tryToEquipWeapon(@NotNull final WeaponCode weaponCode)
      throws UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon {
    controller.tryToEquipWeapon(weaponCode);
  }

  @Override
  void tryToAttackTo(@NotNull final CharacterCode attackedCode) throws NonEquippedWeapon {
    controller.tryToAttackTo(attackedCode);
  }

}
