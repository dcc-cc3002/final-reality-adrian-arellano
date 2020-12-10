package com.github.cc3002.finalreality.model.character.playable.wizard;

import com.github.cc3002.finalreality.model.character.playable.Engineer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a black wizard to execute the tests declared inside the
 *  super classes.
 *
 * @author Adrian Arellano.
 * @see BlackWizard
 */
class BlackWizardTest extends AbstractWizardTest {

  private static final String BLACK_WIZARD_NAME = "Vivi";
  private static final int BLACK_WIZARD_MAX_HP = 27;
  private static final int BLACK_WIZARD_MAX_MANA = 30;
  private static final int BLACK_WIZARD_DEF = 4;

  @Override
  protected void setUpCharacter() {
    testCharacter = new BlackWizard(BLACK_WIZARD_NAME, BLACK_WIZARD_MAX_HP, BLACK_WIZARD_MAX_MANA,
        BLACK_WIZARD_DEF, turns);
    attackedCharacter = new Engineer(DUMMY_NAME, DUMMY_HP, DUMMY_DEF, turns);
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
        new BlackWizard(BLACK_WIZARD_NAME, BLACK_WIZARD_MAX_HP, BLACK_WIZARD_MAX_MANA,
            BLACK_WIZARD_DEF, turns),
        new BlackWizard("Different Black Wizard", BLACK_WIZARD_MAX_HP, BLACK_WIZARD_MAX_MANA,
            BLACK_WIZARD_DEF, turns),
        new WhiteWizard(BLACK_WIZARD_NAME, BLACK_WIZARD_MAX_HP, BLACK_WIZARD_MAX_MANA,
            BLACK_WIZARD_DEF, turns)
    );
  }

}