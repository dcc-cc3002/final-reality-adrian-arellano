package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class which initialize the parameters of a enemy
 *  to execute the tests declared inside the super class.
 *
 * @author Adrian Arellano.
 * @see Enemy
 */
class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private static final int ENEMY_MAX_HP = 27;
  private static final int ENEMY_DEF = 0;
  private static final int ENEMY_ATK = 1;
  private static final int ENEMY_WEIGHT = 10;

  @Override
  protected void setUpCharacter() {
    testCharacter = new Enemy(ENEMY_NAME, ENEMY_MAX_HP, ENEMY_DEF, ENEMY_ATK, ENEMY_WEIGHT, turns);
    attackedCharacter = new Knight(DUMMY_NAME, DUMMY_HP, DUMMY_DEF, turns);
  }

  @Override @BeforeEach
  void setUp() {
    super.setUp();
  }

  @Override @Test
  protected void constructorTest() {
    checkConstruction(
        new Enemy(ENEMY_NAME, ENEMY_MAX_HP, ENEMY_DEF, ENEMY_ATK, ENEMY_WEIGHT, turns),
        new Enemy(ENEMY_NAME, ENEMY_MAX_HP, ENEMY_DEF, ENEMY_ATK,11, turns),
        new Thief(ENEMY_NAME, ENEMY_MAX_HP, ENEMY_DEF, turns));
  }

  @Override @Test
  protected void getAtkAndWeightTest() throws NonEquippedWeapon {
    assertEquals(ENEMY_ATK,    testCharacter.getAtk());
    assertEquals(ENEMY_WEIGHT, testCharacter.getWeight());
  }

  @Override
  protected void getReadyToPlay() {
    /* After the initialization, an enemy is ready to use getWight */
  }

}