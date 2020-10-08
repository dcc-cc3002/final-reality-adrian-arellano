package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;

/**
 * Class which initialize the parameters of a black wizard
 *  to execute the tests declared inside the super classes.
 *
 * @author Adrian Arellano.
 * @see BlackWizard
 */
class BlackWizardTest extends AbstractPlayableCharacterTest {

  private static final String BLACK_WIZARD_NAME = "Vivi";

  @Override
  protected void setUpEquippableWeapons() {
    equippableWeapons.add("Knife");
    equippableWeapons.add("Staff");
  }

  @Override
  protected void setUpCharacter() {
    testCharacter = new BlackWizard(BLACK_WIZARD_NAME, turns);
  }

  @Override
  protected void constructorTest() {
    checkConstruction(
        new BlackWizard(BLACK_WIZARD_NAME, turns),
        new BlackWizard("Different Black Wizard", turns),
        new WhiteWizard(BLACK_WIZARD_NAME, turns)
    );
  }

}