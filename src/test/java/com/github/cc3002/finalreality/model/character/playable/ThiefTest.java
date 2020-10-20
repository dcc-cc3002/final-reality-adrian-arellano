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
  private static final int THIEF_MAX_HP = 21;
  private static final int THIEF_DEF = 0;

  @Override
  protected void setUpCharacter() {
    testCharacter = new Thief(THIEF_NAME, THIEF_MAX_HP, THIEF_DEF, turns);
    attackedCharacter = new WhiteWizard(DUMMY_NAME, DUMMY_HP, DUMMY_DEF, turns);
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
        new Thief(THIEF_NAME, THIEF_MAX_HP, THIEF_DEF, turns),
        new Thief("Different Thief",  THIEF_MAX_HP, THIEF_DEF, turns),
        new Knight(THIEF_NAME, THIEF_MAX_HP, THIEF_DEF, turns)
    );
  }

}