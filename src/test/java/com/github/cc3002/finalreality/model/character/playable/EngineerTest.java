package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;

/**
 * Class which initialize the parameters of a engineer
 *  to execute the tests declared inside the super classes.
 *
 * @author Adrian Arellano.
 * @see Engineer
 */
class EngineerTest extends AbstractPlayableCharacterTest {

  private static final String ENGINEER_NAME = "Cid";

  @Override
  protected void setUpEquippableWeapons() {
    equippableWeapons.add("Axe");
    equippableWeapons.add("Bow");
  }

  @Override
  protected void setUpCharacter() {
    testCharacter = new Engineer(ENGINEER_NAME, turns);
  }

  @Override
  protected void constructorTest() {
    checkConstruction(
        new Engineer(ENGINEER_NAME, turns),
        new Engineer("Different Engineer", turns),
        new BlackWizard(ENGINEER_NAME, turns)
    );
  }

}