package com.github.cc3002.finalreality.model.character;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests
 *  for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Adrian Arellano.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected ICharacter testCharacter;

  /**
   * Initialize the {@code testCharacter} which is been tested.
   */
  protected abstract void setUpCharacter();

  /**
   * Initialize every variable to run properly
   *  the test suite of this class.
   */ @BeforeEach
  void setUp() {
    turns = new LinkedBlockingQueue<>();
    this.setUpCharacter();
  }

  /**
   * Auxiliary method created to test the constructor
   *  of all the classes which implement an ICharacter.
   *
   * All the parameters explain their functionalities by themself.
   */
  protected void checkConstruction(
      @NotNull final ICharacter expectedCharacter,
      @NotNull final ICharacter sameClassDifferentCharacter,
      @NotNull final ICharacter differentClassCharacter
  ) {
    assertEquals(expectedCharacter, testCharacter);
    /* equals */
    final var o = new Object();
    assertNotEquals(o, testCharacter);
    assertNotEquals(sameClassDifferentCharacter, testCharacter);
    assertNotEquals(differentClassCharacter, testCharacter);
    /* hashCode */
    assertEquals(expectedCharacter.hashCode(), testCharacter.hashCode());
  }

  /**
   * Test the constructor, the {@code equals()} and the {@code hashCode()} methods
   *  of the sub-class of ICharacter, where it's implemented.
   *
   * @see #checkConstruction(ICharacter, ICharacter, ICharacter)
   */ @Test
  protected abstract void constructorTest();

  /**
   * Test if {@code getWeight()} works properly,
   *  it could throws an exception.
   */ @Test
  protected abstract void getWeightTest() throws NonEquippedWeapon, NonAvailableWeapon, UnsupportedWeapon;

  /**
   * Let the {@code testCharacter} ready to use the {@code waitTurn()},
   *  without the possibility of failing.
   */
  protected abstract void getReadyToWaitTurn() throws NonAvailableWeapon, UnsupportedWeapon;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */ @Test
  protected void waitTurnTest() throws NonEquippedWeapon, NonAvailableWeapon, UnsupportedWeapon {
    assertTrue(turns.isEmpty());

    this.getReadyToWaitTurn();
    /* getWeight() and waitTurn() should not to throw an exception. */
    final int expectedWaitingTime = testCharacter.getWeight() * 10;  // divided by 10
    testCharacter.waitTurn();

    try {
      /* Thread.sleep is not accurate so this values may be changed to adjust the
       *  acceptable error margin.
       *  We're testing that the character waits approximately 1 second. */
      Thread.sleep(9 * expectedWaitingTime);
      assertEquals(0, turns.size());
      Thread.sleep(2 * expectedWaitingTime);
      assertEquals(1, turns.size());
      assertEquals(testCharacter, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
