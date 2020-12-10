package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class which test the expected functionality of WeaponCodeFactory.
 *
 * @author Adrian Arellano.
 *
 * @see WeaponCodeFactory
 * @see CharacterCodeFactoryTest : this is a simplified copy of that.
 */
class WeaponCodeFactoryTest {

  private static WeaponCodeFactory codeFactory;
  private static Set<WeaponCode> codeSet;

  /**
   * Initializes some necessary variable for some test.
   */ @BeforeEach
  void setUp() {
    codeFactory = new WeaponCodeFactory();
    codeSet = new HashSet<>();
  }

  /**
   * Tests the method {@code create()} of WeaponCodeFactory.
   */ @Test
  void createTest() {
    WeaponCode code1 = codeFactory.create();
    assertEquals(code1, new WeaponCode(1));
    WeaponCode code2 = codeFactory.create();
    assertNotEquals(code1, code2);
    WeaponCode code3 = codeFactory.create();
    assertNotEquals(code1, code3);
    assertNotEquals(code2, code3);
  }

  /**
   * Compares each element of the {@code codeSet} with each other, if there are two arguments equals
   *  returns false, and true if not, meaning that al the elements inside the set are different from
   *  each other.
   *
   * @see #createConsistencyTest()
   */
  private boolean everyElementOfTheSetIsDifferent() {
    final Set<WeaponCode> codeSetCopy = new HashSet<>(codeSet);

    for (WeaponCode code1 : codeSet) {
      codeSetCopy.remove(code1);
      for (WeaponCode code2 : codeSetCopy) {
        if (code1.equals(code2)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Tests the consistency of both creates methods, and test if actually every element created by
   *  the factory is different to each other.
   *
   * @see #everyElementOfTheSetIsDifferent()
   */ @RepeatedTest(100)
  void createConsistencyTest() {
    final long seed = new Random().nextLong();
    final Random randomizer = new Random(seed);

    int times = randomizer.nextInt(30);
    while (0 != times--) {
      codeSet.add(codeFactory.create());
    }

    assertTrue(everyElementOfTheSetIsDifferent(),
        "createConsistencyTest failed with seed: " + seed);
  }

}