package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
  protected void setUpCharacter() {
    testCharacter = new BlackWizard(BLACK_WIZARD_NAME, turns);
  }

  @Override
  protected void setUpEquippableWeapons() {
    equippableWeapons.add("Knife");
    equippableWeapons.add("Staff");
  }

  @Override @BeforeEach
  protected void setUp() {
    super.setUp();
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new BlackWizard(BLACK_WIZARD_NAME, turns),
        new BlackWizard("Different Black Wizard", turns),
        new WhiteWizard(BLACK_WIZARD_NAME, turns)
    );
  }

}