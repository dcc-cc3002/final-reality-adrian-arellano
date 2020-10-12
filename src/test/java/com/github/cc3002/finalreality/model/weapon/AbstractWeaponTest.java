package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests
 *  for all the types of weapons.
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
   * Creates two IWeapon's and two ICharacter's,
   *  which can equip those Sub-WeaponType.
   */ @BeforeEach
  protected abstract void setUp();

  /**
   * Auxiliary method created to test the constructor
   *  of all the classes which implement an IWeapon.
   *
   * @see com.github.cc3002.finalreality.model.character.AbstractCharacterTest
   */
  protected void checkConstruction(
      @NotNull final IWeapon expectedWeapon,
      @NotNull final IWeapon differentClassWeapon
  ) {
    assertEquals(expectedWeapon, testWeapon1);
    /* equals */
    final var o = new Object();
    assertNotEquals(o, testWeapon1);
    assertNotEquals(testWeapon2, testWeapon1);
    assertNotEquals(differentClassWeapon, testWeapon1);
    /* hashCode */
    assertEquals(expectedWeapon.hashCode(), testWeapon1.hashCode());
  }

  /**
   * Test the Constructor of the respective
   *  Sub-WeaponType.
   */ @Test
  protected abstract void constructorTest();


  /* From now on the PlayableCharacters are needed */


  /**
   * Auxiliary method created to be run by {@link #differentHolderTest()}
   *  inside all the classes which implement an IWeapon.
   */
  protected void checkHolder(@NotNull final IWeapon copyOfTestWeapon1) throws NonAvailableWeapon, UnsupportedWeapon {
    /* The weapons are equals, but they don't have the same memory location. */
    assertEquals(copyOfTestWeapon1, testWeapon1);
    assertNotSame(copyOfTestWeapon1, testWeapon1);

    sampleCharacter1.equip(testWeapon1);
    assertNotEquals(copyOfTestWeapon1, testWeapon1);
    sampleCharacter2.equip(copyOfTestWeapon1);
    assertNotEquals(copyOfTestWeapon1, testWeapon1);
  }

  /**
   * Test the concept that if two weapons looks the same,
   *  but they are held by different Character's, then they
   *  are not the same weapon.
   */ @Test
  protected abstract void differentHolderTest() throws NonAvailableWeapon, UnsupportedWeapon;

  /**
   * Test the concept that a weapon
   *  can be held by only one Character a time.
   */ @Test
  protected void oneCarrierTest() throws UnsupportedWeapon, NonAvailableWeapon {
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
