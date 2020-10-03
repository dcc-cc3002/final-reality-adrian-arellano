package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playable.AbstractPlayableCharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonEquippableWeapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The template for the testing of the PlayableCharacter's subclasses.
 */
public abstract class AbstractPlayableCharacterTest {

  protected static AbstractPlayableCharacter testCharacter1;
  protected static AbstractPlayableCharacter testCharacter2;
  protected static Set<String> equippableWeapons;

  private static Map<String, IWeapon> weaponsSample;


  /**
   * Creates every kind of IWeapon {@param weaponSample}
   *  and two different PlayableCharacter's of the
   *  current sub type.
   * Also initializes the {@param equippableWeapons} which
   *  has a String with the name of each equippable weapon
   *  for this sub-PlayableCharacterType.
   */
  @BeforeEach
  public abstract void setUp();

  /**
   * Test the Constructor of the respective
   *  Sub-PlayableCharacterType.
   */
  @Test
  public abstract void constructorTest();

  /** Test the equals method. */
  @Test
  public void equalsTest() {
    final var o = new Object();
    assertNotEquals(testCharacter1, o);

    assertEquals(testCharacter1, testCharacter1);
    assertNotEquals(testCharacter1, testCharacter2);
  }

  /* From now on the IWeapons are needed */

  /**
   * Test that this Sub-PlayableCharacterType can only equip
   *  the weapons pointed out inside {@param equippableWeapons}.
   */
  @Test
  public void equipWeaponTest() {
    BiConsumer<String, IWeapon> weaponTest = new TryEquipWeapons();
    weaponsSample.forEach(weaponTest);
  }

  /**
   * Inner class which works as an complement to
   * compute the {equipWeaponTest} objective.
   */
  private static class TryEquipWeapons implements BiConsumer<String, IWeapon> {
    public void accept(String weaponName, IWeapon aWeapon) {
      if (equippableWeapons.contains(weaponName)) {
        testCharacter1.equip(aWeapon);
        assertEquals(aWeapon, testCharacter1.getEquippedWeapon());
      }
      /* Try to equip the weapon and catch the Exception */
      assertThrows(NonEquippableWeapon.class,
          () -> {
            testCharacter1.equip(aWeapon);
          });
    }
  }

}
