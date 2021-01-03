package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A suite of test to the the gamePhase class and sub-classes.
 *
 * @author Adrian Arellano.
 *
 * @see GamePhase
 */
class GamePhaseTest {

  private final GameController controller = new GameController();
  private GamePhase phase;

  @BeforeEach
  void setUp() {
    phase = new Phase0(controller);
    controller.setCurrentPhase(phase);
  }

  private void updatePhase() {
    phase = controller.getCurrentPhase();
  }

  @Test
  void transitionsTest() {
    assertTrue(phase.isPhase(0));
    assertFalse(phase.isPhase(1));
    assertFalse(phase.isPhase(2));
    assertFalse(phase.isPhase(3));

    phase.nextPlayer();
    updatePhase();

    assertFalse(phase.isPhase(0));
    assertTrue(phase.isPhase(1));
    assertFalse(phase.isPhase(2));
    assertFalse(phase.isPhase(3));

    phase.nextPhase();
    updatePhase();

    assertFalse(phase.isPhase(0));
    assertFalse(phase.isPhase(1));
    assertTrue(phase.isPhase(2));
    assertFalse(phase.isPhase(3));

    phase.nextPhase();
    updatePhase();

    assertFalse(phase.isPhase(0));
    assertFalse(phase.isPhase(1));
    assertFalse(phase.isPhase(2));
    assertTrue(phase.isPhase(3));

    phase.nextPlayer();
    updatePhase();

    assertFalse(phase.isPhase(0));
    assertTrue(phase.isPhase(1));
    assertFalse(phase.isPhase(2));
    assertFalse(phase.isPhase(3));
  }

  @Test
  void failingTransitions() {
    assertTrue(phase.isPhase(0));
    assertThrows(RuntimeException.class, () -> phase.nextPhase());

    phase.nextPlayer();
    updatePhase();

    assertTrue(phase.isPhase(1));
    assertThrows(RuntimeException.class, () -> phase.nextPlayer());

    phase.nextPhase();
    updatePhase();

    assertTrue(phase.isPhase(2));
    assertThrows(RuntimeException.class, () -> phase.nextPlayer());

    phase.nextPhase();
    updatePhase();

    assertTrue(phase.isPhase(3));
    assertThrows(RuntimeException.class, () -> phase.nextPhase());
  }

  @Test
  void failedMethods() {
    CharacterCode character = new CharacterCode(1, false, false);
    WeaponCode weapon = new WeaponCode(1);


    assertTrue(phase.isPhase(0));
    assertThrows(RuntimeException.class, () -> phase.tryToAttackTo(character));
    assertThrows(RuntimeException.class, () -> phase.tryToEquipWeapon(weapon));

    phase.nextPlayer();
    updatePhase();

    assertTrue(phase.isPhase(1));
    assertThrows(RuntimeException.class, () -> phase.tryToAttackTo(character));
    assertThrows(RuntimeException.class, () -> phase.tryToEquipWeapon(weapon));

    phase.nextPhase();
    updatePhase();
    phase.nextPhase();
    updatePhase();

    assertTrue(phase.isPhase(3));
    assertThrows(RuntimeException.class, () -> phase.tryToAttackTo(character));
    assertThrows(RuntimeException.class, () -> phase.tryToEquipWeapon(weapon));
  }

  @Test
  void byAccidentCreatingAGenericPhase() {
    CharacterCode character = new CharacterCode(1, false, false);
    WeaponCode weapon = new WeaponCode(1);

    phase = new GamePhase();

    assertFalse(phase.isPhase(0));
    assertFalse(phase.isPhase(1));
    assertFalse(phase.isPhase(2));
    assertFalse(phase.isPhase(3));

    assertThrows(RuntimeException.class, () -> phase.nextPhase());
    assertThrows(RuntimeException.class, () -> phase.nextPlayer());

    assertThrows(RuntimeException.class, () -> phase.tryToAttackTo(character));
    assertThrows(RuntimeException.class, () -> phase.tryToEquipWeapon(weapon));
  }

}