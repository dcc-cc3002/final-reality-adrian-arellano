package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class which test the expected functionality of CharacterCodeFactory.
 *
 * @author Adrian Arellano.
 *
 * @see CharacterCodeFactory
 */
class CharacterCodeFactoryTest {

  private static CharacterCodeFactory codeFactory;
  private static CharacterCode code1, code2, code3;
  private static Set<CharacterCode> codeSet;

  /**
   * Initializes some necessary variable for some test.
   */ @BeforeEach
  void setUp() {
    codeFactory = new CharacterCodeFactory();
    codeSet = new HashSet<>();
  }

  /**
   * Tests explicitly the method {@code createPlayable()} of CharacterCodeFactory.
   */ @Test
  void createPlayableTest() {
    code1 = codeFactory.createPlayable();
    assertEquals(code1, new CharacterCode(1, true, false));
    code2 = codeFactory.createPlayable();
    assertNotEquals(code1, code2);
    code3 = codeFactory.createPlayable();
    assertNotEquals(code1, code3);
    assertNotEquals(code2, code3);
  }

  /**
   * Tests explicitly the method {@code createPlayable()} of CharacterCodeFactory.
   */ @Test
  void createWizardTest() {
    code1 = codeFactory.createWizard();
    assertEquals(code1, new CharacterCode(1, true, true));
    code2 = codeFactory.createWizard();
    assertNotEquals(code1, code2);
    code3 = codeFactory.createWizard();
    assertNotEquals(code1, code3);
    assertNotEquals(code2, code3);
  }

  /**
   * Tests explicitly the method {@code createEnemy()} of CharacterCodeFactory.
   */ @Test
  void createEnemyTest() {
    code1 = codeFactory.createEnemy();
    assertEquals(code1, new CharacterCode(1, false, false));
    code2 = codeFactory.createEnemy();
    assertNotEquals(code1, code2);
    code3 = codeFactory.createEnemy();
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
    final Set<CharacterCode> codeSetCopy = new HashSet<>(codeSet);

    for (CharacterCode code1 : codeSet) {
      codeSetCopy.remove(code1);
      for (CharacterCode code2 : codeSetCopy) {
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

    int times = randomizer.nextInt(15);
    while (0 != times--) {
      final int thisRandom = randomizer.nextInt(3);
      if (thisRandom == 0) {
        codeSet.add(codeFactory.createPlayable());
      } else if (thisRandom == 1) {
        codeSet.add(codeFactory.createWizard());
      }  else {
        codeSet.add(codeFactory.createEnemy());
      }
    }

    assertTrue(everyElementOfTheSetIsDifferent(),
        "createConsistencyTest failed with seed: " + seed);
  }

}