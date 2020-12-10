package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import com.github.cc3002.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of playable characters.
 *
 * @author Adrian Arellano.
 * @see IPlayableCharacter
 */
public abstract class AbstractPlayableCharacterTest extends AbstractCharacterTest {

  protected static IPlayableCharacter testPlayableCharacter;
  protected static Set<String> equippableWeapons;

  private static final Map<String, IWeapon> weaponsSample = new HashMap<>();

  /**
   * Saves in the variable: {@param equippableWeapons}, a Set with the name of the type of each kind
   *  of weapon which can be equipped by this IPlayableCharacter's sub-type.
   */
  protected abstract void setUpEquippableWeapons();

  /**
   * Creates every kind of IWeapon {@param weaponSample} and two different PlayableCharacter's of
   *  the current sub type.
   * Also initializes the {@param equippableWeapons} which has a String with the name of each
   *  equippable weapon for this sub-PlayableCharacterType.
   */ @BeforeEach
  protected void setUp() {
    super.setUp();
    weaponsSample.put("Axe", new Axe("Battle Axe", 16, 14));
    weaponsSample.put("Bow", new Bow("Ordinary Bow", 10, 15));
    weaponsSample.put("Knife", new Knife("Small Knife", 5, 10));
    weaponsSample.put("Staff", new Staff("Wooden Staff", 6, 28));
    weaponsSample.put("Sword", new Sword("Rapier", 9, 12));
    /* Initialize a IPlayableCharacter as an ICharacter. */
    this.setUpCharacter();
    /* We are testing the IPlayableCharacter sub-classes, so we can do this cast. */
    testPlayableCharacter = (IPlayableCharacter) testCharacter;
    /* Creates from 0, a equippableWeapons Set. */
    equippableWeapons = new HashSet<>();
    this.setUpEquippableWeapons();
  }

  /**
   * Test that this Sub-PlayableCharacterType can only equip the weapons pointed out inside
   *  {@code equippableWeapons}.
   *
   * This method should not raise any exceptions.
   */ @Test
  void equipWeaponTest1() throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior {
    for (String weaponType : weaponsSample.keySet()) {
      final IWeapon aWeapon = weaponsSample.get(weaponType);

      if (equippableWeapons.contains(weaponType)) {
        testPlayableCharacter.equip(aWeapon);
        assertEquals(aWeapon, testPlayableCharacter.getEquippedWeapon());
      }
      else {  /* The exception should occur. */
        assertThrows(UnsupportedWeapon.class,
            () -> testPlayableCharacter.equip(aWeapon), "the exception was not thrown.");
      }
    }
  }

  /**
   * Test the concept that a character cannot equip a weapon if is K.O.
   */ @Test
  void equipWeaponTest2() throws UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon {
    final IWeapon aWeapon = weaponsSample.get(equippableWeapons.iterator().next());
    assertNotEquals(aWeapon, testPlayableCharacter.getEquippedWeapon());
    /* the character is K.O. and tries to equip another weapon */
    defeatCharacter();
    testPlayableCharacter.equip(aWeapon);
    /* weapon was not equipped */
    assertNotEquals(aWeapon, testPlayableCharacter.getEquippedWeapon());
  }

  @Override @Test
  protected void getAtkAndWeightTest()
      throws NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior, NonEquippedWeapon {
    assertThrows(NonEquippedWeapon.class,
        () -> testCharacter.getAtk());
    assertThrows(NonEquippedWeapon.class,
        () -> testCharacter.getWeight());
    assertThrows(NonEquippedWeapon.class,
        () -> testCharacter.waitTurn());
    assertThrows(NonEquippedWeapon.class,
        () -> testCharacter.attack(attackedCharacter));


    final IWeapon aWeapon = weaponsSample.get(equippableWeapons.iterator().next());
    testPlayableCharacter.equip(aWeapon);
    assertEquals(aWeapon, testPlayableCharacter.getEquippedWeapon(),
        "The weapon was not equipped. The current weapon is: " +
            testPlayableCharacter.getEquippedWeapon());

    assertEquals(aWeapon.getDamage(), testCharacter.getAtk());
    assertEquals(aWeapon.getWeight(), testCharacter.getWeight());
  }

  @Override @Test
  protected void isPlayableTest() {
    assertTrue(testCharacter.isPlayable());
  }

  @Override
  protected void getReadyToPlay() throws NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior {
    final IWeapon aWeapon = weaponsSample.get(equippableWeapons.iterator().next());
    testPlayableCharacter.equip(aWeapon);
  }

}
