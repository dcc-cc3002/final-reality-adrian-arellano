package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;

/**
 * Class which initialize the parameters of a Knife
 *  to execute the tests declared inside the super class.
 *
 * @author Adrian Arellano.
 * @see Knife
 */
class KnifeTest extends AbstractWeaponTest {

  private static final String KNIFE_NAME = "Small Knife";
  private static final int DAMAGE = 5;
  private static final int WEIGHT = 10;

  @Override
  protected void setUp() {
    testWeapon1 = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
    testWeapon2 = new Knife("Large Dagger", 7, 16);

    sampleCharacter1 = new Knight("Albert", turns);
    sampleCharacter2 = new Thief("Zidane", turns);
  }

  @Override
  protected void constructorTest() {
    checkConstruction(
        new Knife(KNIFE_NAME, DAMAGE, WEIGHT),
        new Bow(KNIFE_NAME, DAMAGE, WEIGHT)
    );
  }

  @Override
  protected void differentHolderTest() throws NonAvailableWeapon, UnsupportedWeapon {
    checkHolder(new Knife(KNIFE_NAME, DAMAGE, WEIGHT));
  }

}