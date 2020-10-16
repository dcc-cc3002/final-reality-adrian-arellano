package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.Engineer;
import com.github.cc3002.finalreality.model.character.playable.Knight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of an Axe
 *  to execute the tests declared inside the super class.
 *
 * @author Adrian Arellano.
 * @see Axe
 */
class AxeTest extends AbstractWeaponTest {

  private static final String AXE_NAME = "Battle Axe";
  private static final int DAMAGE = 16;
  private static final int WEIGHT = 14;

  @Override @BeforeEach
  protected void setUp() {
    testWeapon1 = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    testWeapon2 = new Axe("Great Axe", 22,  19);

    sampleCharacter1 = new Knight("Albert", turns);
    sampleCharacter2 = new Engineer("Cid", turns);
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new Axe(AXE_NAME, DAMAGE, WEIGHT),
        new Sword(AXE_NAME, DAMAGE, WEIGHT)
    );
  }

  @Override @Test
  protected void differentHolderTest() throws NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior {
    checkHolder(new Axe(AXE_NAME, DAMAGE, WEIGHT));
  }

}