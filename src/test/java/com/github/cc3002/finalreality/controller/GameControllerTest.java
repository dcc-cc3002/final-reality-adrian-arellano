package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.CharacterAlreadyCreated;
import com.github.cc3002.finalreality.model.character.NonEquippedWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import com.github.cc3002.finalreality.model.weapon.WeaponAlreadyCreated;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class which tests the well functionality of the GameController, an extension of it, like the
 *  PlayableKoHandler, and the EnemyKoHandler.
 * None of the next methods should throw an exception.
 *
 * @author Adrian Arellano.
 *
 * @see GameController
 * @see PlayableKoHandler
 * @see EnemyKoHandler
 */
class GameControllerTest {

  private static GameController controller;

  private static final int CHARACTER_MAX_HP = 20;
  private static final int CHARACTER_MAX_MANA = 15;
  private static final int CHARACTER_DEF = 3;
  private static final int CHARACTER_ATK = 7;
  private static final int CHARACTER_WEIGHT = 12;

  private static final int WEAPON_DAMAGE = 9;
  private static final int Weapon_WEIGHT = 10;

  private static CharacterCode engineer;
  private static CharacterCode knight;
  private static CharacterCode thief;
  private static CharacterCode blackWizard;
  private static CharacterCode whiteWizard;
  private static CharacterCode enemy1;
  private static CharacterCode enemy2;

  private static WeaponCode axe;
  private static WeaponCode bow;
  private static WeaponCode knife;
  private static WeaponCode staff;
  private static WeaponCode sword;

  /**
   * Initializes every variable to run properly the test suite of this class.
   */ @BeforeEach
  void setUp() throws CharacterAlreadyCreated, WeaponAlreadyCreated {
    controller = new GameController();

    engineer = controller.createAnEngineer("engineer", CHARACTER_MAX_HP, CHARACTER_DEF);
    knight = controller.createAKnight("knight", CHARACTER_MAX_HP, CHARACTER_DEF);
    thief = controller.createAThief("thief", CHARACTER_MAX_HP, CHARACTER_DEF);
    blackWizard = controller.createABlackWizard("blackWizard", CHARACTER_MAX_HP,
        CHARACTER_MAX_MANA, CHARACTER_DEF);
    whiteWizard = controller.createAWhiteWizard("whiteWizard", CHARACTER_MAX_HP,
        CHARACTER_MAX_MANA, CHARACTER_DEF);
    enemy1 = controller.createAnEnemy("enemy1", CHARACTER_MAX_HP, CHARACTER_DEF,
        CHARACTER_ATK, CHARACTER_WEIGHT);
    enemy2 = controller.createAnEnemy("enemy2", CHARACTER_MAX_HP, CHARACTER_DEF,
        CHARACTER_ATK, CHARACTER_WEIGHT);

    axe = controller.createAnAxe("axe", WEAPON_DAMAGE, Weapon_WEIGHT);
    bow = controller.createABow("bow", WEAPON_DAMAGE, Weapon_WEIGHT);
    knife = controller.createAKnife("knife", WEAPON_DAMAGE, Weapon_WEIGHT);
    staff = controller.createAStaff("staff", WEAPON_DAMAGE, Weapon_WEIGHT);
    sword = controller.createASword("sword", WEAPON_DAMAGE, Weapon_WEIGHT);
  }

  /**
   * Checks if the getters of a Character returns what we except to return after the creation of a
   *  Playable Character.
   */
  private boolean playableCharacterWellCreated(@NotNull final String name,
                                               @NotNull final CharacterCode cCode) {
    return controller.getNameOfCharacter(cCode).equals(name)
        && controller.getMaxHpOfCharacter(cCode) == CHARACTER_MAX_HP
        && controller.getCurrentHpOfCharacter(cCode) == CHARACTER_MAX_HP
        && controller.getDefOfCharacter(cCode) == CHARACTER_DEF
        && !controller.isKo(cCode);
  }

  /**
   * Checks if the getters of a Character returns what we except to return after the creation of a
   *  Wizard Character.
   */
  private boolean wizardCharacterWellCreated(@NotNull final String name,
                                             @NotNull final CharacterCode cCode) {
    return playableCharacterWellCreated(name, cCode)
        && controller.getMaxManaOfCharacter(cCode) == CHARACTER_MAX_MANA
        && controller.getCurrentManaOfCharacter(cCode) == CHARACTER_MAX_MANA;
  }

  /**
   * Checks if the getters of a Character returns what we except to return after the creation of a
   *  Enemy Character.
   */
  private boolean enemyCharacterWellCreated(@NotNull final String name,
                                            @NotNull final CharacterCode cCode)
      throws NonEquippedWeapon {
    return playableCharacterWellCreated(name, cCode)
        && controller.getAtkOfCharacter(cCode) == CHARACTER_ATK
        && controller.getWeightOfCharacter(cCode) == CHARACTER_WEIGHT;
  }

  /**
   * Tests if all the possible types of characters to be created were well created.
   */ @Test
  void characterCreationTest() throws NonEquippedWeapon {
    assertTrue(playableCharacterWellCreated("engineer", engineer));
    assertThrows(NonEquippedWeapon.class, () -> controller.getAtkOfCharacter(engineer));
    assertThrows(NonEquippedWeapon.class, () -> controller.getWeightOfCharacter(engineer));

    assertTrue(playableCharacterWellCreated("knight", knight));
    assertThrows(NonEquippedWeapon.class, () -> controller.getAtkOfCharacter(knight));
    assertThrows(NonEquippedWeapon.class, () -> controller.getWeightOfCharacter(knight));

    assertTrue(playableCharacterWellCreated("thief", thief));
    assertThrows(NonEquippedWeapon.class, () -> controller.getAtkOfCharacter(thief));
    assertThrows(NonEquippedWeapon.class, () -> controller.getWeightOfCharacter(thief));

    assertTrue(wizardCharacterWellCreated("whiteWizard", whiteWizard));
    assertThrows(NonEquippedWeapon.class, () -> controller.getAtkOfCharacter(whiteWizard));
    assertThrows(NonEquippedWeapon.class, () -> controller.getWeightOfCharacter(whiteWizard));

    assertTrue(wizardCharacterWellCreated("blackWizard", blackWizard));
    assertThrows(NonEquippedWeapon.class, () -> controller.getAtkOfCharacter(blackWizard));
    assertThrows(NonEquippedWeapon.class, () -> controller.getWeightOfCharacter(blackWizard));

    assertTrue(enemyCharacterWellCreated("enemy1", enemy1));
    assertTrue(enemyCharacterWellCreated("enemy2", enemy2));
  }

  /**
   * Checks if the getters of a Weapon returns what we except to return after the creation of a
   *  Weapon.
   */
  private boolean weaponWellCreated(@NotNull final String name,
                                    @NotNull final WeaponCode wCode) {
    return controller.getNameOfWeapon(wCode).equals(name)
        && controller.getDamageOfWeapon(wCode) == WEAPON_DAMAGE
        && controller.getWeightOfWeapon(wCode) == Weapon_WEIGHT
        && controller.getCurrentCarrierOfWeapon(wCode) == null;
  }

  /**
   * Tests if all the possible types of characters to be created were well created.
   */ @Test
  void weaponCreationTest() {
    assertTrue(weaponWellCreated("axe", axe));
    assertTrue(weaponWellCreated("bow", bow));
    assertTrue(weaponWellCreated("knife", knife));
    assertTrue(weaponWellCreated("staff", staff));
    assertTrue(weaponWellCreated("sword", sword));
  }

  /** Checks if the inventory contains all the created Weapons. */
  private boolean containsAllTheWeapons(@NotNull final Set<WeaponCode> inventory) {
    return inventory.contains(axe)
        && inventory.contains(bow)
        && inventory.contains(knife)
        && inventory.contains(staff)
        && inventory.contains(sword)
        && inventory.size() == 5;
  }

  /** Checks if the inventory contains all the members of the party. */
  private boolean containsAllThePlayable(@NotNull final Set<CharacterCode> party) {
    return party.contains(engineer)
        && party.contains(knight)
        && party.contains(thief)
        && party.contains(blackWizard)
        && party.contains(whiteWizard)
        && party.size() == 5;
  }

  /** Checks if the inventory contains all the members of the party. */
  private boolean containsAllTheEnemies(@NotNull final Set<CharacterCode> enemies) {
    return enemies.contains(enemy1)
        && enemies.contains(enemy2)
        && enemies.size() == 2;
  }

  /**
   * Tests the basic getters of the Game Controller class After start.
   */ @Test
  void startGettersTest() {
    assertFalse(controller.gameOver());
    assertFalse(controller.thePlayerWins());
    assertNull(controller.getTurnOwner());

    assertTrue(containsAllTheWeapons(controller.getInventory()));
    assertTrue(containsAllThePlayable(controller.getParty()));
    assertTrue(containsAllTheEnemies(controller.getEnemies()));
  }

  /**
   * Tests the method {@code .updateTurnOwner()}.
   */ @Test
  void updateTurnOwnerTest() throws InterruptedException {
    assertNull(controller.getTurnOwner());

    controller.updateTurnOwner();
    assertEquals(engineer, controller.getTurnOwner());
    controller.updateTurnOwner();
    assertEquals(knight, controller.getTurnOwner());
    controller.updateTurnOwner();
    assertEquals(thief, controller.getTurnOwner());
    controller.updateTurnOwner();
    assertEquals(blackWizard, controller.getTurnOwner());
    controller.updateTurnOwner();
    assertEquals(whiteWizard, controller.getTurnOwner());
    controller.updateTurnOwner();
    assertEquals(enemy1, controller.getTurnOwner());
    controller.updateTurnOwner();
    assertEquals(enemy2, controller.getTurnOwner());

    // Time Out
    controller.updateTurnOwner();
    assertNull(controller.getTurnOwner());
  }

  /**
   * Tests if the {@code .equipWeapon(WeaponCode)} method, works properly.
   */ @Test
  void equipWeaponTest() throws UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon, InterruptedException {
    controller.updateTurnOwner();
    controller.equipWeapon(axe);
    assertEquals(axe, controller.getEquippedWeaponOf(engineer));
    controller.equipWeapon(bow);
    assertEquals(bow, controller.getEquippedWeaponOf(engineer));
    assertThrows(UnsupportedWeapon.class, () -> controller.equipWeapon(knife));
    assertThrows(UnsupportedWeapon.class, () -> controller.equipWeapon(staff));
    assertThrows(UnsupportedWeapon.class, () -> controller.equipWeapon(sword));

    controller.updateTurnOwner();
    controller.updateTurnOwner();
    assertThrows(UnsupportedWeapon.class, () -> controller.equipWeapon(axe));
    assertThrows(NonAvailableWeapon.class, () -> controller.equipWeapon(bow));
    controller.equipWeapon(knife);
    assertEquals(knife, controller.getEquippedWeaponOf(thief));
    assertThrows(UnsupportedWeapon.class, () -> controller.equipWeapon(staff));
    controller.equipWeapon(sword);
    assertEquals(sword, controller.getEquippedWeaponOf(thief));
  }

  /**
   * Tests if the {@code .characterAttacksTo(CharacterCode)} method, works properly.
   * And this tests a pair of things more.
   */ @Test
  void characterAttacksToTest() throws NonEquippedWeapon, UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon, InterruptedException {
    int damageExpected = WEAPON_DAMAGE - CHARACTER_DEF; // = 6

    controller.updateTurnOwner();
    assertThrows(NonEquippedWeapon.class, () -> controller.characterAttacksTo(enemy1));
    assertEquals(CHARACTER_MAX_HP, controller.getCurrentHpOfCharacter(enemy1));
    controller.equipWeapon(axe);
    controller.characterAttacksTo(enemy1);
    assertEquals(CHARACTER_MAX_HP - damageExpected,
        controller.getCurrentHpOfCharacter(enemy1));
    assertNull(controller.getTurnOwner());

    controller.updateTurnOwner();
    controller.equipWeapon(sword);
    controller.characterAttacksTo(enemy1);
    assertEquals(CHARACTER_MAX_HP - 2 * damageExpected,
        controller.getCurrentHpOfCharacter(enemy1));
    assertNull(controller.getTurnOwner());

    controller.updateTurnOwner();
    controller.equipWeapon(bow);
    controller.characterAttacksTo(enemy1);
    assertEquals(CHARACTER_MAX_HP - 3 * damageExpected,
        controller.getCurrentHpOfCharacter(enemy1));
    assertNull(controller.getTurnOwner());

    controller.updateTurnOwner();
    controller.equipWeapon(knife);
    controller.characterAttacksTo(enemy1);
    assertEquals(0, controller.getCurrentHpOfCharacter(enemy1));
    assertTrue(controller.isKo(enemy1));
    assertNull(controller.getTurnOwner());

    // Last member of the party
    controller.updateTurnOwner();
    controller.updateTurnOwner();

    /* The enemy 1 is K.O. so there is no turn for it. */
    assertEquals(enemy2, controller.getTurnOwner());
  }

  /**
   * Returns true if the amount of the Playable Characters which still alive, is equals to the given
   *  amount, and false if not.
   */
  private boolean playableStillAlive(final int amount) {
     return controller.getParty().size() == amount;
  }

  /**
   * Tests if the {@code .enemyTurn(CharacterCode)} method, works properly. That means, the enemy
   *  attack one and only one member of the party.
   * This method test if the Game Is Over when all the playable characters are K.O. too.
   */ @Test
  void enemyTurnTest() throws CharacterAlreadyCreated, NonEquippedWeapon, InterruptedException {
    final CharacterCode onePunchMan = controller.createAnEnemy("One Punch Man",
        100, 100, 1000000, 0);
    while (! (onePunchMan.equals(controller.getTurnOwner()))) {
      controller.updateTurnOwner();
    }
    controller.enemyTurn();
    assertTrue(playableStillAlive(4));

    controller.updateTurnOwner();
    assertEquals(onePunchMan, controller.getTurnOwner());
    controller.enemyTurn();
    assertTrue(playableStillAlive(3));

    controller.updateTurnOwner();
    assertEquals(onePunchMan, controller.getTurnOwner());
    controller.enemyTurn();
    assertTrue(playableStillAlive(2));

    controller.updateTurnOwner();
    assertEquals(onePunchMan, controller.getTurnOwner());
    controller.enemyTurn();
    assertTrue(playableStillAlive(1));

    controller.updateTurnOwner();
    assertEquals(onePunchMan, controller.getTurnOwner());
    controller.enemyTurn();
    assertTrue(playableStillAlive(0));

    assertTrue(controller.gameOver());
    assertFalse(controller.thePlayerWins());
  }

  /**
   * Test if the gam recognize whe the player actually wins.
   */ @Test
  void playerWinsTest() throws WeaponAlreadyCreated, InterruptedException, UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon, NonEquippedWeapon {
    final WeaponCode infinityGauntlet1 = controller.createAnAxe("Infinity Gauntlet",
        1000000, 0);
    final WeaponCode infinityGauntlet2 = controller.createAnAxe("Infinity Gauntlet",
        1000000, 1);
    controller.updateTurnOwner();
    controller.equipWeapon(infinityGauntlet1);
    controller.characterAttacksTo(enemy1);

    assertTrue(controller.isKo(enemy1));
    controller.updateTurnOwner();
    controller.equipWeapon(infinityGauntlet2);
    controller.characterAttacksTo(enemy2);
    assertTrue(controller.isKo(enemy2));

    assertTrue(controller.gameOver());
    assertTrue(controller.thePlayerWins());
  }

}