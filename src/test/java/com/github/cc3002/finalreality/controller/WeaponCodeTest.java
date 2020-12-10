package com.github.cc3002.finalreality.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class which tests the well functionality of the WeaponCode elements.
 *
 * @author Adrian Arellano.
 *
 * @see WeaponCode
 * @see CharacterCodeTest : this is a simplified copy of that.
 */
class WeaponCodeTest {

  private static final WeaponCode testCode = new WeaponCode(1);

  @Test
  void constructorTest() {
    assertEquals(testCode, testCode);
    assertEquals(testCode, new WeaponCode(1));
    /* equals */
    final var o = new Object();
    assertNotEquals(testCode, o);
    assertNotEquals(testCode, new WeaponCode(2));
    assertNotEquals(testCode, new WeaponCode(3));
    /* hashCode */
    assertEquals(testCode.hashCode(), new WeaponCode(1).hashCode());
  }

}