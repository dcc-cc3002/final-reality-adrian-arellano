package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playable.Engineer;
import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class created to test the well working of a Character Factory.
 *
 * @author Adrian Arellano.
 *
 * @see CharacterFactory
 */
class CharacterFactoryTest {

  private static BlockingQueue<ICharacter> turnsQueue;
  private static Map<IPlayableCharacter, Boolean> characters;
  private static Map<Enemy, Boolean> enemies;

  private static CharacterFactory testedFactory;

  private static final String CHARACTER_NAME = "Dummy";
  private static final int CHARACTER_MAX_HP = 20;
  private static final int CHARACTER_MAX_MANA = 15;
  private static final int CHARACTER_DEF = 3;
  private static final int CHARACTER_ATK = 7;
  private static final int CHARACTER_WEIGHT = 12;

  private static Engineer engineer;
  private static Knight knight;
  private static Thief thief;
  private static BlackWizard blackWizard;
  private static WhiteWizard whiteWizard;
  private static Enemy enemy;

  /**
   * Initializes every variable to run properly the test suite of this class.
   * This should not throw an exception.
   */ @BeforeEach
  void setUp() {
    turnsQueue = new LinkedBlockingQueue<>();
    characters = new HashMap<>();
    enemies = new HashMap<>();

    testedFactory = new CharacterFactory(turnsQueue, characters, enemies);

    engineer = testedFactory.createAnEngineer(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF);
    knight = testedFactory.createAKnight(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF);
    thief = testedFactory.createAThief(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF);
    blackWizard = testedFactory.createABlackWizard(CHARACTER_NAME, CHARACTER_MAX_HP,
        CHARACTER_MAX_MANA, CHARACTER_DEF);
    whiteWizard = testedFactory.createAWhiteWizard(CHARACTER_NAME, CHARACTER_MAX_HP,
        CHARACTER_MAX_MANA, CHARACTER_DEF);
    enemy = testedFactory.createAnEnemy(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_ATK,
        CHARACTER_DEF, CHARACTER_WEIGHT);
  }

  /**
   * Tests if the factory actually creates the character we want to create.
   */ @Test
  void createBasicTest() {
    assertEquals(engineer, new Engineer(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF,
        turnsQueue));
    assertEquals(knight, new Knight(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF, turnsQueue));
    assertEquals(thief, new Thief(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF, turnsQueue));
    assertEquals(blackWizard, new BlackWizard(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_MAX_MANA,
        CHARACTER_DEF, turnsQueue));
    assertEquals(whiteWizard, new WhiteWizard(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_MAX_MANA,
        CHARACTER_DEF, turnsQueue));
    assertEquals(enemy, new Enemy(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_ATK, CHARACTER_DEF,
        CHARACTER_WEIGHT, turnsQueue));
  }

  /**
   * Tests if the factory throws an exception when we try to create a character we already create.
   */ @Test
  void createNullTest() {
    assertNull(testedFactory.createAnEngineer(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF));
    assertNull(testedFactory.createAKnight(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF));
    assertNull(testedFactory.createAThief(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_DEF));
    assertNull(testedFactory.createABlackWizard(CHARACTER_NAME, CHARACTER_MAX_HP,
        CHARACTER_MAX_MANA, CHARACTER_DEF));
    assertNull(testedFactory.createAWhiteWizard(CHARACTER_NAME, CHARACTER_MAX_HP,
        CHARACTER_MAX_MANA, CHARACTER_DEF));
    assertNull(testedFactory.createAnEnemy(CHARACTER_NAME, CHARACTER_MAX_HP, CHARACTER_ATK,
            CHARACTER_DEF, CHARACTER_WEIGHT));
   }

  /**
   * Tests if the input maps were actually modifies, and contains only the added characters, nothing
   *  more.
   */ @Test
  void wellCreatedMaps() {
    assertTrue(characters.containsKey(engineer) && !characters.get(engineer));
    assertTrue(characters.containsKey(knight) && !characters.get(knight));
    assertTrue(characters.containsKey(thief) && !characters.get(thief));
    assertTrue(characters.containsKey(blackWizard) && !characters.get(blackWizard));
    assertTrue(characters.containsKey(whiteWizard) && !characters.get(whiteWizard));
    assertEquals(5, characters.size());

    assertTrue(enemies.containsKey(enemy) && !enemies.get(enemy));
    assertEquals(1, enemies.size());
  }

}