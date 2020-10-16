package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a Sword
 *  to execute the tests declared inside the super class.
 *
 * @author Adrian Arellano.
 * @see Sword
 */
class SwordTest extends AbstractWeaponTest {

  private static final String SWORD_NAME = "Rapier";
  private static final int DAMAGE = 9;
  private static final int WEIGHT = 12;

  @Override @BeforeEach
  protected void setUp() {
    testWeapon1 = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    testWeapon2 = new Sword("Saber", 13, 6);

    sampleCharacter1 = new Knight("Albert", turns);
    sampleCharacter2 = new Thief("Zidane", turns);
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new Sword(SWORD_NAME, DAMAGE, WEIGHT),
        new Staff(SWORD_NAME, DAMAGE, WEIGHT)
    );
  }

  @Override @Test
  protected void differentHolderTest() throws NonAvailableWeapon, UnsupportedWeapon, UnexpectedBehavior {
    checkHolder(new Sword(SWORD_NAME, DAMAGE, WEIGHT));
  }

}