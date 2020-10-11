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
 * Abstract class containing the common tests
 *  for all the types of playable characters.
 *
 * @author Adrian Arellano.
 * @see IPlayableCharacter
 */
public abstract class AbstractPlayableCharacterTest extends AbstractCharacterTest {

  protected static IPlayableCharacter testPlayableCharacter;
  protected static Set<String> equippableWeapons;

  private final static Map<String, IWeapon> weaponsSample = new HashMap<>();

  /**
   * Saves in the variable: {@param equippableWeapons},
   *  a Set with the name of the type of each kind of
   *  weapon which can be equipped by this
   *   IPlayableCharacter's sub-type.
   */
  protected abstract void setUpEquippableWeapons();

  /**
   * Creates every kind of IWeapon {@param weaponSample}
   *  and two different PlayableCharacter's of the
   *  current sub type.
   * Also initializes the {@param equippableWeapons} which
   *  has a String with the name of each equippable weapon
   *  for this sub-PlayableCharacterType.
   */
  @BeforeEach
  void setUp() {
    weaponsSample.put("Axe", new Axe("Battle Axe", 16, 14));
    new Axe("Great Axe", 22,  19);
    weaponsSample.put("Bow", new Bow("Ordinary Bow", 10, 15));
    new Bow("Falcon Bow", 15, 21);
    weaponsSample.put("Knife", new Knife("Small Knife", 5, 10));
    new Knife("Large Dagger", 7, 16);
    weaponsSample.put("Staff", new Staff("Wooden Staff", 6, 28));
    new Staff("Power Staff", 12, 11);
    weaponsSample.put("Sword", new Sword("Rapier", 9, 12));
    new Sword("Saber", 13, 6);
    /* Initialize a IPlayableCharacter as an ICharacter. */
    this.setUpCharacter();
    /* We are testing the IPlayableCharacter sub-classes, so we can do this cast. */
    testPlayableCharacter = (IPlayableCharacter) testCharacter;
    /* Creates from 0, a equippableWeapons Set. */
    equippableWeapons = new HashSet<>();
    this.setUpEquippableWeapons();
  }


  /* From now on the IWeapons are needed */
  /**
   * Test that this Sub-PlayableCharacterType can only equip
   *  the weapons pointed out inside {@param equippableWeapons}.
   *
   *    This method should not raise any exceptions.
   */
  @Test
  void equipWeaponTest() throws UnsupportedWeapon, NonAvailableWeapon {
    for (String weaponType : weaponsSample.keySet()) {
      IWeapon aWeapon = weaponsSample.get(weaponType);

      if (equippableWeapons.contains(weaponType)) {
        testPlayableCharacter.equip(aWeapon);
        assertEquals(aWeapon, testPlayableCharacter.getEquippedWeapon());
      }
      else {  /* The exception should occur. */
        assertThrows(UnsupportedWeapon.class,
            () -> {
              testPlayableCharacter.equip(aWeapon);
            });
      }
    }
  }

  @Override
  protected void getWeightTest() throws NonAvailableWeapon, UnsupportedWeapon {
    /* A exception should raise. */
    assertThrows(NonEquippedWeapon.class,
        () -> {
          testCharacter.getWeight();
        });
    /* We do this using testPlayableCharacter, because
     *  ICharacter does not have the equip() method */
    final IWeapon aWeapon = weaponsSample.get(equippableWeapons.iterator().next());
    testPlayableCharacter.equip(aWeapon);
    try {
      assertEquals(aWeapon.getWeight(), testCharacter.getWeight());
    } catch (NonEquippedWeapon e) {
      System.err.println(e.getClass().toString());
    }
  }

  @Override
  protected void getReadyToWaitTurn() throws NonAvailableWeapon, UnsupportedWeapon {
    final IWeapon aWeapon = weaponsSample.get(equippableWeapons.iterator().next());
    testPlayableCharacter.equip(aWeapon);
  }

}
