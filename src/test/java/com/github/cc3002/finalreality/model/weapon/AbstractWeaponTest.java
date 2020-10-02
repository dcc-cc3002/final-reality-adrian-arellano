package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The template for the testing of the IWeapon's subclasses.
 */
public abstract class AbstractWeaponTest {
  protected static String weaponName;
  protected static int weaponDamage;
  protected static int weaponWeight;

  protected static IWeapon testWeapon1;
  protected static IWeapon testWeapon2;

  protected static IPlayableCharacter sampleCharacter1;
  protected static IPlayableCharacter sampleCharacter2;

  /**
   * Creates two IWeapon's and two ICharacter's,
   *  which can equip those Sub-WeaponType.
   */
  @BeforeEach
  public abstract void setUp();

  /**
   * Test the Constructor of the respective
   *  Sub-WeaponType.
   */
  @Test
  public abstract void constructorTest();

  /** Test the equals method. */
  @Test
  public void equalsTest() {
    final var o = new Object();
    assertNotEquals(testWeapon1, o);

    assertEquals(testWeapon1, testWeapon1);
    assertNotEquals(testWeapon1, testWeapon2);
  }


  /* From now on the PlayableCharacters are needed */


  /**
   * Test the concept that if two weapons looks the same,
   *  but they are held by different Character's, then they
   *  are not the same weapon.
   */
  @Test
  public abstract void differentHolderTest();

  /**
   * Test the concept that a weapon
   *  can be held by only one Character a time.
   */
  @Test
  public void oneCarrierTest() throws NonEquippableWeapon, NonAvailableWeapon {
    sampleCharacter1.equip(testWeapon1);
    assertEquals(testWeapon1, sampleCharacter1.getEquippedWeapon());

    /* Equip the same Weapon again doesn't throws an Exception */
    sampleCharacter1.equip(testWeapon1);

    /* A weapon can be held by only one Character a time */
    assertThrows(NonAvailableWeapon.class,
        () -> {
          sampleCharacter2.equip(testWeapon1);
        });

    assertEquals(testWeapon1, sampleCharacter1.getEquippedWeapon());
    sampleCharacter1.equip(testWeapon2);
    assertEquals(testWeapon2, sampleCharacter1.getEquippedWeapon());

    sampleCharacter2.equip(testWeapon1);
    assertEquals(testWeapon1, sampleCharacter2.getEquippedWeapon());
  }

}
