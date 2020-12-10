package com.github.cc3002.finalreality.model.character;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.github.cc3002.finalreality.model.character.playable.AbstractPlayableCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Integer.max;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Adrian Arellano.
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected static BlockingQueue<ICharacter> turns;
  protected static ICharacter testCharacter;
  protected static ICharacter attackedCharacter;

  protected final String DUMMY_NAME = "Dummy";
  protected final int DUMMY_HP = 100;
  protected final int DUMMY_DEF = 0;

  /**
   * Initializes the {@code testCharacter} which is been tested.
   * And the Dummy {@code attackedCharacter}.
   */
  protected abstract void setUpCharacter();

  /**
   * Initializes every variable to run properly
   *  the test suite of this class.
   */ @BeforeEach
  void setUp() {
    turns = new LinkedBlockingQueue<>();
    this.setUpCharacter();
  }

  /**
   * Auxiliary method created to test the constructor of all the classes which implement an
   *  ICharacter.
   *
   * All the parameters explain their functionalities by themself.
   */
  protected void checkConstruction(
      @NotNull final ICharacter expectedCharacter,
      @NotNull final ICharacter sameClassDifferentCharacter,
      @NotNull final ICharacter differentClassCharacter
  ) {
    assertEquals(testCharacter, testCharacter);
    assertEquals(testCharacter, expectedCharacter);
    /* equals */
    final var o = new Object();
    assertNotEquals(testCharacter, o);
    assertNotEquals(testCharacter, sameClassDifferentCharacter);
    assertNotEquals(testCharacter, differentClassCharacter);
    /* hashCode */
    assertEquals(testCharacter.hashCode(), expectedCharacter.hashCode());
  }

  /**
   * Test the constructor, the {@code equals()} and the {@code hashCode()} methods of the sub-class
   *  of ICharacter, where it's implemented.
   *
   * @see #checkConstruction(ICharacter, ICharacter, ICharacter)
   */ @Test
  protected abstract void constructorTest();

  /**
   * Test {@code getAtk()} and {@code getWeight()} to see if they properly.
   * In the case of a {@code IPlayableCharacter}, this method test if {@code equip(IWeapon)} is
   *  consistence and returns Attack an Weight as it should.
   *
   * @see AbstractCharacter#getAtk()
   * @see AbstractCharacter#getWeight()
   * @see AbstractPlayableCharacter#equip(IWeapon)
   */ @Test
  protected abstract void getAtkAndWeightTest()
      throws NonEquippedWeapon, NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior;

  /**
   * Tests if the character answer properly to {@code .isPlayable()}.
   */ @Test
  protected abstract void isPlayableTest();

  /**
   * Let the {@code testCharacter} ready to use the {@code waitTurn()} and {@code getAtk()}
   *  without the possibility of failing.
   */
  protected abstract void getReadyToPlay()
      throws NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior;

  /** The Character receives an attack such as powerful, to get K.O. */
  protected void defeatCharacter() {
    assertEquals(testCharacter.getMaxHp(), testCharacter.getCurrentHp());
    final int powerfulAtk = testCharacter.getMaxHp() + testCharacter.getDef();
    testCharacter.receiveAtk(powerfulAtk);
    assertEquals(0, testCharacter.getCurrentHp());
  }

  /**
   * Test the {@code receiveAtk(int)} method of a character.
   */ @Test
  void receiveAtkTest() {
    int expectedHp = testCharacter.getMaxHp();
    assertEquals(expectedHp, testCharacter.getCurrentHp());
    testCharacter.receiveAtk(testCharacter.getDef() - 1);
    assertEquals(expectedHp, testCharacter.getCurrentHp());
    testCharacter.receiveAtk(testCharacter.getDef());
    assertEquals(expectedHp, testCharacter.getCurrentHp());
    testCharacter.receiveAtk(-10);
    assertEquals(expectedHp, testCharacter.getCurrentHp());

    testCharacter.receiveAtk(testCharacter.getDef() + 3);
    expectedHp = max(0, expectedHp - 3);
    assertEquals(expectedHp, testCharacter.getCurrentHp());
    testCharacter.receiveAtk(testCharacter.getDef() + 5);
    expectedHp = max(0, expectedHp - 5);
    assertEquals(expectedHp, testCharacter.getCurrentHp());

    testCharacter.receiveAtk(testCharacter.getDef() + expectedHp);
    assertEquals(0, testCharacter.getCurrentHp());
    testCharacter.receiveAtk(20);
    assertEquals(0, testCharacter.getCurrentHp());
  }

  /**
   * Test the {@code attack(ICharacter)} method of a character.
   */ @Test
  void attackTest()
      throws UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon, NonEquippedWeapon {
    assertEquals(testCharacter.getMaxHp(), testCharacter.getCurrentHp());
    assertEquals(attackedCharacter.getMaxHp(), attackedCharacter.getCurrentHp());

    getReadyToPlay();
    testCharacter.attack(attackedCharacter);
    final int expectedHP = max(0, DUMMY_HP - testCharacter.getAtk());
    assertEquals(expectedHP, attackedCharacter.getCurrentHp());

    defeatCharacter();
    testCharacter.attack(attackedCharacter);
    /* Attack was not received */
    assertEquals(expectedHP, attackedCharacter.getCurrentHp());
  }

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */ @Test
  void waitTurnTest() throws NonEquippedWeapon, NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior {
    assertTrue(turns.isEmpty());

    this.getReadyToPlay();
    /* getWeight() and waitTurn() should not to throw an exception. */
    final long expectedWaitingTime = (long) testCharacter.getWeight() * 10;  // divided by 10
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
