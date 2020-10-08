package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playable.Thief;

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
  private static final int ENEMY_WEIGHT = 10;

  @Override
  protected void setUpCharacter() {
    testCharacter = new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns);
  }

  @Override
  protected void getWeightTest() throws NonEquippedWeapon {
    assertEquals(ENEMY_WEIGHT, testCharacter.getWeight());
  }

  @Override
  protected void getReadyToWaitTurn() {
    /* After the initialization, an enemy is ready to use getWight */
  }

  @Override
  protected void constructorTest() {
    checkConstruction(
        new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns),
        new Enemy(ENEMY_NAME, 11, turns),
        new Thief(ENEMY_NAME, turns));
  }

}