package com.github.cc3002.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
// import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class created to test the well working of a Character Factory.
 *
 * @author Adrian Arellano.
 *
 * @see WeaponFactory
 */
class WeaponFactoryTest {

  // private Set<IWeapon> inventory;

  private WeaponFactory testedFactory;

  private static final String WEAPON_NAME = "Dummy";
  private static final int WEAPON_DAMAGE = 4;
  private static final int WEAPON_WEIGHT = 15;

  private static Axe axe;
  private static Bow bow;
  private static Knife knife;
  private static Staff staff;
  private static Sword sword;

  /**
   * Initializes every variable to run properly the test suite of this class.
   * This should not throw an exception.
   */ @BeforeEach
  void setUp() throws WeaponAlreadyCreated {
    // inventory = new HashSet<>();

    testedFactory = new WeaponFactory();

    axe = testedFactory.createAnAxe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT);
    bow = testedFactory.createABow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT);
    knife = testedFactory.createAKnife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT);
    staff = testedFactory.createAStaff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT);
    sword = testedFactory.createASword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT);
    }

  /**
   * Tests if the factory actually creates the weapon we want to create.
   */ @Test
  void createBasicTest() {
    assertEquals(axe, new Axe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(bow, new Bow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(knife, new Knife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(staff, new Staff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(sword, new Sword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
  }

  /**
   * Tests if the factory throws an exception when we try to create a character we already create.
   */ @Test
  void createThrowExceptionTest() {
    Exception e;
    e = assertThrows(WeaponAlreadyCreated.class,
        () -> testedFactory.createAnAxe(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(WEAPON_NAME, e.getMessage());
    e = assertThrows(WeaponAlreadyCreated.class,
        () -> testedFactory.createABow(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(WEAPON_NAME, e.getMessage());
    e = assertThrows(WeaponAlreadyCreated.class,
        () -> testedFactory.createAKnife(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(WEAPON_NAME, e.getMessage());
    e = assertThrows(WeaponAlreadyCreated.class,
        () -> testedFactory.createAStaff(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(WEAPON_NAME, e.getMessage());
    e = assertThrows(WeaponAlreadyCreated.class,
        () -> testedFactory.createASword(WEAPON_NAME, WEAPON_DAMAGE, WEAPON_WEIGHT));
    assertEquals(WEAPON_NAME, e.getMessage());
   }

  /*/**
   * Tests if the input maps were actually modifies, and contains only the added characters, nothing
   *  more.
   * @Test
  void wellCreatedMaps() {
    assertTrue(inventory.contains(axe));
    assertTrue(inventory.contains(bow));
    assertTrue(inventory.contains(knife));
    assertTrue(inventory.contains(staff));
    assertTrue(inventory.contains(sword));
    assertEquals(5, inventory.size());
  }*/

}