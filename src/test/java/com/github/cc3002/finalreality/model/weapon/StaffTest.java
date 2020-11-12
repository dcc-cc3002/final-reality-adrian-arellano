package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a Staff
 *  to execute the tests declared inside the super class.
 *
 * @author Adrian Arellano.
 * @see Staff
 */
class StaffTest extends AbstractWeaponTest {

  private static final String STAFF_NAME = "Wooden Staff";
  private static final int DAMAGE = 6;
  private static final int WEIGHT = 28;

  @Override @BeforeEach
  protected void setUp() {
    testWeapon1 = new Staff(STAFF_NAME, DAMAGE, WEIGHT);
    testWeapon2 = new Staff("Power Staff", 12, 11);

    sampleCharacter1 = new BlackWizard("Vivi", 27, 30,4, turns);
    sampleCharacter2 = new WhiteWizard("Eiko", 16, 25,3, turns);
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new Staff(STAFF_NAME, DAMAGE, WEIGHT),
        new Knife(STAFF_NAME, DAMAGE, WEIGHT)
    );
  }

  @Override @Test
  protected void differentHolderTest() throws NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior {
    checkHolder(new Staff(STAFF_NAME, DAMAGE, WEIGHT));
  }

}