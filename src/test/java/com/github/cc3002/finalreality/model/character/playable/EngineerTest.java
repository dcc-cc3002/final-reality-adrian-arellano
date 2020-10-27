package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;
import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a engineer
 *  to execute the tests declared inside the super classes.
 *
 * @author Adrian Arellano.
 * @see Engineer
 */
class EngineerTest extends AbstractPlayableCharacterTest {

  private static final String ENGINEER_NAME = "Cid";
  private static final int ENGINEER_MAX_HP = 15;
  private static final int ENGINEER_DEF = 5;

  @Override
  protected void setUpCharacter() {
    testCharacter = new Engineer(ENGINEER_NAME, ENGINEER_MAX_HP, ENGINEER_DEF, turns);
    attackedCharacter = new Enemy(DUMMY_NAME, DUMMY_HP, DUMMY_DEF, 0, 0, turns);
  }

  @Override
  protected void setUpEquippableWeapons() {
    equippableWeapons.add("Axe");
    equippableWeapons.add("Bow");
  }

  @Override @BeforeEach
  protected void setUp() {
    super.setUp();
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new Engineer(ENGINEER_NAME, ENGINEER_MAX_HP, ENGINEER_DEF, turns),
        new Engineer("Different Engineer", ENGINEER_MAX_HP, ENGINEER_DEF, turns),
        new BlackWizard(ENGINEER_NAME, ENGINEER_MAX_HP, ENGINEER_DEF, turns)
    );
  }

}