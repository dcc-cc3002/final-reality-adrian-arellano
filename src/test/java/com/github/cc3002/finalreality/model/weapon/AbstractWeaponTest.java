package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playable.AbstractPlayableCharacter;
import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of weapons.
 *
 * @author Adrian Arellano.
 * @see IWeapon
 */
public abstract class AbstractWeaponTest {

  protected static IWeapon testWeapon1;
  protected static IWeapon testWeapon2;

  protected static IPlayableCharacter sampleCharacter1;
  protected static IPlayableCharacter sampleCharacter2;

  /* Only used to create characters. */
  protected static final BlockingQueue<ICharacter> turns = new LinkedBlockingQueue<>();

  /**
   * Creates two IWeapon's and two ICharacter's, which can equip those Sub-WeaponType.
   */ @BeforeEach
  protected abstract void setUp();

  /**
   * Auxiliary method created to test the constructor of all the classes which implement an IWeapon.
   *
   * @see com.github.cc3002.finalreality.model.character.AbstractCharacterTest
   */
  protected void checkConstruction(
      @NotNull final IWeapon expectedWeapon,
      @NotNull final IWeapon differentClassWeapon
  ) {
    assertEquals(testWeapon1, testWeapon1);
    assertEquals(testWeapon1, expectedWeapon);
    /* equals */
    final var o = new Object();
    assertNotEquals(testWeapon1, o);
    assertNotEquals(testWeapon1, testWeapon2);
    assertNotEquals(testWeapon1, differentClassWeapon);
    /* hashCode */
    assertEquals(testWeapon1.hashCode(), expectedWeapon.hashCode());
  }

  /**
   * Test the Constructor of the respective
   *  Sub-WeaponType.
   */ @Test
  protected abstract void constructorTest();


  /**
   * Test the method {@code unEquippedBy(PlayableCharacter)}, which is used used for the double
   *  dispatch of {@code equip(IWeapon)}, but the method should not be called by any external
   *  object.
   * An example of an external object which could call this, would be the controller.
   *
   * @see AbstractWeapon#unEquippedBy(IPlayableCharacter)
   * @see AbstractPlayableCharacter#equip(IWeapon)
   */ @Test
  void misusedUnEquippedByTest() throws NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior {
    final Exception e1 = assertThrows(UnexpectedBehavior.class,
        () -> testWeapon1.unEquippedBy(sampleCharacter1));
    assertEquals("The suppose carrier is not the current carrier.", e1.getMessage());
    sampleCharacter1.equip(testWeapon1);
    assertEquals(sampleCharacter1, testWeapon1.getCurrentCarrier());
    assertEquals(testWeapon1, sampleCharacter1.getEquippedWeapon());
    final Exception e2 = assertThrows(UnexpectedBehavior.class,
        () -> testWeapon1.unEquippedBy(sampleCharacter2));
    assertEquals("The suppose carrier is not the current carrier.", e2.getMessage());
  }

    /* From now on the PlayableCharacters are needed */

  /**
   * Test the concept that a weapon can be held by only one Character a time.
   */ @Test
  void oneCarrierTest() throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior {
    sampleCharacter1.equip(testWeapon1);
    assertEquals(testWeapon1, sampleCharacter1.getEquippedWeapon());

    /* Equip the same Weapon again doesn't throws an Exception */
    sampleCharacter1.equip(testWeapon1);

    /* A weapon can be held by only one Character a time */
    assertThrows(NonAvailableWeapon.class,
        () -> sampleCharacter2.equip(testWeapon1));

    assertEquals(testWeapon1, sampleCharacter1.getEquippedWeapon());
    sampleCharacter1.equip(testWeapon2);
    assertEquals(testWeapon2, sampleCharacter1.getEquippedWeapon());

    sampleCharacter2.equip(testWeapon1);
    assertEquals(testWeapon1, sampleCharacter2.getEquippedWeapon());
  }

}
