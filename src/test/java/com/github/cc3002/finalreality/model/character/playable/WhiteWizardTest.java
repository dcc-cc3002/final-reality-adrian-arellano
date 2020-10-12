package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;
import com.github.cc3002.finalreality.model.character.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a white wizard
 *  to execute the tests declared inside the super classes.
 *
 * @author Adrian Arellano.
 * @see WhiteWizard
 */
class WhiteWizardTest extends AbstractPlayableCharacterTest {

  private static final String WHITE_WIZARD_NAME = "Eiko";

  @Override
  protected void setUpCharacter() {
    testCharacter = new WhiteWizard(WHITE_WIZARD_NAME, turns);
  }

  @Override
  protected void setUpEquippableWeapons() {
    equippableWeapons.add("Staff");
  }

  @Override @BeforeEach
  protected void setUp() {
    super.setUp();
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new WhiteWizard(WHITE_WIZARD_NAME, turns),
        new WhiteWizard("Different White Wizard", turns),
        new Enemy(WHITE_WIZARD_NAME, 10, turns)
    );
  }

}