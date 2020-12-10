package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a knight to execute the tests declared inside the super
 *  classes.
 *
 * @author Adrian Arellano.
 * @see Knight
 */
class KnightTest extends AbstractPlayableCharacterTest {

  private static final String KNIGHT_NAME = "Adelbert";
  private static final int KNIGHT_MAX_HP = 20;
  private static final int KNIGHT_DEF = 6;

  @Override
  protected void setUpCharacter() {
    testCharacter = new Knight(KNIGHT_NAME, KNIGHT_MAX_HP, KNIGHT_DEF, turns);
    attackedCharacter = new Thief(DUMMY_NAME, DUMMY_HP, DUMMY_DEF, turns);
  }

  @Override
  protected void setUpEquippableWeapons() {
    equippableWeapons.add("Axe");
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
        new Knight(KNIGHT_NAME, KNIGHT_MAX_HP, KNIGHT_DEF, turns),
        new Knight("Different Knight", KNIGHT_MAX_HP, KNIGHT_DEF, turns),
        new Engineer(KNIGHT_NAME, KNIGHT_MAX_HP, KNIGHT_DEF, turns)
    );
  }

}