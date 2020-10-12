package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a thief
 *  to execute the tests declared inside the super classes.
 *
 * @author Adrian Arellano.
 * @see Thief
 */
class ThiefTest extends AbstractPlayableCharacterTest {

  private static final String THIEF_NAME = "Zidane";

  @Override
  protected void setUpCharacter() {
    testCharacter = new Thief(THIEF_NAME, turns);
  }

  @Override
  protected void setUpEquippableWeapons() {
    equippableWeapons.add("Bow");
    equippableWeapons.add("Knife");
    equippableWeapons.add("Sword");
  }

  @Override @BeforeEach
  protected void setUp() {
    super.setUp();
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new Thief(THIEF_NAME, turns),
        new Thief("Different Thief", turns),
        new Knight(THIEF_NAME, turns)
    );
  }

}