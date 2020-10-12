package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.Engineer;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class which initialize the parameters of a Bow
 *  to execute the tests declared inside the super class.
 *
 * @author Adrian Arellano.
 * @see Bow
 */
class BowTest extends AbstractWeaponTest {

  private static final String BOW_NAME = "Ordinary Bow";
  private static final int DAMAGE = 10;
  private static final int WEIGHT = 15;

  @Override @BeforeEach
  protected void setUp() {
    testWeapon1 = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    testWeapon2 = new Bow("Falcon Bow", 15, 21);

    sampleCharacter1 = new Engineer("Cid", turns);
    sampleCharacter2 = new Thief("Zidane", turns);
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new Bow(BOW_NAME, DAMAGE, WEIGHT),
        new Axe(BOW_NAME, DAMAGE, WEIGHT)
    );
  }

  @Override @Test
  protected void differentHolderTest() throws NonAvailableWeapon, UnsupportedWeapon {
    checkHolder(new Bow(BOW_NAME, DAMAGE, WEIGHT));
  }

  @Override @Test
  protected void oneCarrierTest() throws UnsupportedWeapon, NonAvailableWeapon {
    super.oneCarrierTest();
  }

}