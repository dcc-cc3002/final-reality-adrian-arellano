package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class which test the well functionality of the CharacterCode elements.
 *
 * @author Adrian Arellano.
 *
 * @see CharacterCodeFactory
 */
class CharacterCodeTest {

  private static final CharacterCode testCode = new CharacterCode(1, true, true);

  @Test
  void constructorTest() {
    assertEquals(testCode, testCode);
    assertEquals(testCode, new CharacterCode(1, true, true));
    /* equals */
    final var o = new Object();
    assertNotEquals(testCode, o);
    assertEquals(   testCode, new CharacterCode(1, true,  true));
    assertNotEquals(testCode, new CharacterCode(1, true,  false));
    assertNotEquals(testCode, new CharacterCode(1, false, true));
    assertNotEquals(testCode, new CharacterCode(1, false, false));
    assertNotEquals(testCode, new CharacterCode(2, true,  true));
    assertNotEquals(testCode, new CharacterCode(2, true,  false));
    assertNotEquals(testCode, new CharacterCode(2, false, true));
    assertNotEquals(testCode, new CharacterCode(2, false, false));

    /* hashCode */
    assertEquals(testCode.hashCode(), new CharacterCode(1, true, true).hashCode());
  }

}