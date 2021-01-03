package com.github.cc3002.finalreality.controller;

import com.github.cc3002.finalreality.model.character.*;
import com.github.cc3002.finalreality.model.character.playable.wizard.IWizard;
import com.github.cc3002.finalreality.model.weapon.*;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.github.cc3002.finalreality.model.character.playable.Engineer;
import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class works as the controller of the game: Final Reality.
 *
 * @author Adrian Arellano.
 */
public class GameController {

  private static final int TIME_OUT_IN_SECONDS = 5;
  
  /** The character which has to play. */
  private ICharacter turnOwner = null;
  private CharacterCode turnOwnerCode = null;

  private final BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();
  /* The format of this Maps is the next: <ICharacter, ICharacter.isKo()> */
  private final Map<IPlayableCharacter, Boolean> party = new HashMap<>();
  private final Map<Enemy, Boolean> enemies = new HashMap<>();


  /* Factories */
  private final CharacterFactory characterFactory = new CharacterFactory(turnsQueue, party,
      enemies);
  private final CharacterCodeFactory characterCodeFactory = new CharacterCodeFactory();

  private final WeaponFactory weaponFactory = new WeaponFactory();
  private final WeaponCodeFactory weaponCodeFactory = new WeaponCodeFactory();


  /* Codification for the view.
   * Is a BiMap because we need to access from each index to each other. */
  private final BiMap<CharacterCode, ICharacter> charactersCode = HashBiMap.create();
  private final BiMap<CharacterCode, IPlayableCharacter> playableCharactersCode
      = HashBiMap.create();
  private final BiMap<CharacterCode, IWizard> wizardsCode = HashBiMap.create();

  private final BiMap<WeaponCode, IWeapon> weaponsCode = HashBiMap.create();


  /* Observer Pattern */
  private final EnemyKoHandler enemyKoHandler = new EnemyKoHandler(this);
  private final PlayableKoHandler playableKoHandler = new PlayableKoHandler(this);


  /* State Pattern */
  private GamePhase currentPhase = new Phase0(this);


  private static final byte THE_PLAYER_WINS = 1;
  private static final byte THE_PLAYER_LOSES = 2;

  private byte gameOver = 0;


  /* This class Getters : BEGIN */

  /** Returns true if the game has ended, anf false if not. */
  public boolean gameOver() {
    return gameOver != 0;
  }

  /** Returns true if the player wins, and false if not. */
  public boolean thePlayerWins() {
    return gameOver == THE_PLAYER_WINS;
  }

  /** Returns the code of the turnOwner, returns null if there is none. */
  public CharacterCode getTurnOwner() {
    return turnOwnerCode;
  }

  /** Returns the inventory of the player, which only has available weapons. */
  public Set<WeaponCode> getInventory() {
    final Set<WeaponCode> inventory = new HashSet<>();
    weaponsCode.forEach((wCode, weapon) -> {
      if (weapon.getCurrentCarrier() == null)
        inventory.add(wCode);
    } );
    return inventory;
  }

  /** Returns the enemies which are not K.O. in the game. */
  public Set<CharacterCode> getEnemies() {
    final Set<CharacterCode> listOfEnemies = new HashSet<>();
    charactersCode.forEach((cCode, character) -> {
      if (!cCode.isPlayable() && !character.isKo())
        listOfEnemies.add(cCode);
    } );
    return listOfEnemies;
  }

  /** Returns the members of the party which are not K.O. in the game. */
  public Set<CharacterCode> getParty() {
    final Set<CharacterCode> listOfPlayable = new HashSet<>();
    charactersCode.forEach((cCode, character) -> {
      if (cCode.isPlayable() && !character.isKo())
        listOfPlayable.add(cCode);
    } );
    return listOfPlayable;
  }

  /* This class Getters : END */


  /* Hard Methods : BEGIN */

  /**
   * Ends the turn of the current {@code turnOwner}, resetting each associated variable.
   *//* package private : to be used on the test */
  void endTurn() {
    turnOwner = null;
    turnOwnerCode = null;
    currentPhase.nextPhase();
  }

  /**
   * Updates the {@code turnOwner}, assuming that the turn of the {@code turnOwner} has ended.
   * This method takes characters out from the Queue, until draw one which is not K.O., so this
   *  could be the new {@code turnOwner}. If it takes out a null, there are no characters left and
   *  the game should be is over a long time ago.
   * It really important to highlight that the method {@code BlockingQueue.poll()} waits until the
   *  next element with timer is available.
   */
  public void updateTurnOwner() throws InterruptedException {
    currentPhase.nextPlayer();
    do {
      turnOwner = turnsQueue.poll(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS);
      if (turnOwner == null) return;
    } while (turnOwner.isKo());
    turnOwnerCode = charactersCode.inverse().get(turnOwner);
    currentPhase.nextPhase();
  }

  /**
   * Equips the given weapon (the weapon associated to the given code), to the {@code turnOwner} in
   *  the current state of the game; if the {@code turnOwner} is not a playable character (<=>
   *  {@code playableTurnOwner == null}), nothing happens.
   * This method does the thing said before only is it can be done.
   *
   * @param weaponCode : the code of the weapon to be equipped by the {@code turnOwner}.
   */
  public void equipWeapon(@NotNull final WeaponCode weaponCode)
      throws UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon {
    currentPhase.tryToEquipWeapon(weaponCode);
  }

  /* package private */
  void tryToEquipWeapon(@NotNull final WeaponCode weaponCode)
      throws UnexpectedBehavior, NonAvailableWeapon, UnsupportedWeapon {
    final IWeapon aWeapon = weaponsCode.get(weaponCode);
    final IPlayableCharacter playableTurnOwner = playableCharactersCode.get(turnOwnerCode);
    playableTurnOwner.equip(aWeapon);
  }

  /**
   * The {@code turnOwner} attacks to the the given characters (the character associated to the
   *  given code).
   * This method does the thing said before only is it can be done.
   * If this method end successfully, the turn of the {@code turnOwner} has ended, and the character
   *  goes into the {@code turnsQueue} again.
   *
   * @param attackedCode : the code of the character who receives the attack.
   */
  public void characterAttacksTo(@NotNull final CharacterCode attackedCode)
      throws NonEquippedWeapon {
    currentPhase.tryToAttackTo(attackedCode);
  }

  /* package private */
  void tryToAttackTo(@NotNull final CharacterCode attackedCode) throws NonEquippedWeapon {
    final ICharacter attacked = charactersCode.get(attackedCode);
    turnOwner.attack(attacked);
    turnOwner.waitTurn();
    endTurn();
  }

  /**
   * Does an Automatic Turn for the Enemy, assuming that the {@code turnOwner} is an Enemy.
   * The Enemy who is the current {@code turnOwner} any random playable character which is still
   *  alive.
   * If all goes good, the turn ends and we are going to be ready to the next turn.
   */
  public CharacterCode enemyTurn() throws NonEquippedWeapon {
    final Set<CharacterCode> available = getParty();
    CharacterCode target = null;
    int randomIndex = new Random().nextInt(available.size());
    for (CharacterCode playable : available) {
      if (randomIndex == 0) {
        target = playable;
        break;
      }
      randomIndex--;
    }
    if (target != null) {
      characterAttacksTo(target);
    }
    return  target;
  }

  /* Hard Methods : END */


  /* State Pattern : BEGIN */

  /**
   * Updates the current phase of the game.
   *//* package private */
  void setCurrentPhase(@NotNull final GamePhase newPhase) {
    currentPhase = newPhase;
    currentPhase.setGameController(this);
  }

  /* package private */
  GamePhase getCurrentPhase() {
    return currentPhase;
  }

  /* State Pattern : END */


  /*##########################*/
  /* Observer Pattern : BEGIN */
  /*##########################*/

  /**
   * Returns true if all the characters listed in the given map are knockout, and false if not.
   * The way by which we know if they are K.O. is seen the value associated to every key which
   *  suppose to be a boolean meaning the result of {@code .isKo()} method.
   *
   * @param mapOfCharacters : A map which contains characters as keys associated to its result of
   *                          {@code .isKo()} method.
   *
   * @see #updateEnemyKo(Enemy)
   * @see #updatePlayableKo(IPlayableCharacter)
   */
  private boolean charactersAreAllKo(@NotNull final Map<?, Boolean> mapOfCharacters) {
    AtomicBoolean allAreKo = new AtomicBoolean(true);
    mapOfCharacters.values().forEach(isKo -> allAreKo.set(allAreKo.get() && isKo));
    return allAreKo.get();
  }

  /**
   * This method is called by the {@code EnemyKoHandler} when an Enemy is defeated.
   * Also checks if the player has won (<=> all the enemies are K.O.), and updates the state of the
   *  game ({@code private gameOver}) if the player actually won.
   *
   * @param anEnemy : the defeated Enemy.
   *
   * @see EnemyKoHandler#propertyChange(PropertyChangeEvent)
   * @see #charactersAreAllKo(Map)
   */
  public void updateEnemyKo(@NotNull final Enemy anEnemy) {
    enemies.replace(anEnemy, false, true);
    if (charactersAreAllKo(enemies)) {
      gameOver = THE_PLAYER_WINS;
    }
  }

  /**
   * This method is called by the {@code PlayableKoHandler} when a playable character is defeated.
   * Also checks if the player has lost (<=> all the playable character are K.O.), and updates the
   *  state of the game ({@code private gameOver}) if the player actually lost.
   *
   * @param aPlayableCharacter : the defeated IPlayableCharacter.
   *
   * @see PlayableKoHandler#propertyChange(PropertyChangeEvent)
   */
  public void updatePlayableKo(@NotNull final IPlayableCharacter aPlayableCharacter) {
    party.replace(aPlayableCharacter, false, true);
    if (charactersAreAllKo(party)) {
      gameOver = THE_PLAYER_LOSES;
    }
  }

  /*########################*/
  /* Observer Pattern : END */
  /*########################*/


  /* Character Creation : BEGIN */

  /**
   * Adds a the given Character to the Turns Queue, this method is only used to initialize the
   *  turns, when the gama has not started yet.
   * The Turns Queue is a FIFO Queue, so the first created Character, the Character who has the
   *  first turn.
   *
   * @param aCharacter : the given character to add to the Queue.
   *//* I'm not sure if this is what you want me to do. */
  private void addTurn(@NotNull final ICharacter aCharacter) {
    turnsQueue.add(aCharacter);
  }

  /**
   * Associates the given generic Character (ICharacter) to the given Character Code, inside the
   *  correspondent BiMap.
   *
   * @param aCode      : the given Character Code to associate.
   * @param aCharacter : the given Character to associate.
   */
  private void addGenericCodification(@NotNull final CharacterCode aCode,
                                      @NotNull final ICharacter aCharacter) {
    charactersCode.putIfAbsent(aCode, aCharacter);
  }

  /**
   * Associates the given Playable Character to the given Character Code, inside the correspondent
   *  BiMaps.
   *
   * @param aCode     : the given Character Code to associate.
   * @param aPlayable : the given Playable Character to associate.
   */
  private void addPlayableCodification(@NotNull final CharacterCode aCode,
                                       @NotNull final IPlayableCharacter aPlayable) {
    playableCharactersCode.putIfAbsent(aCode, aPlayable);
    addGenericCodification(aCode, aPlayable);
  }

  /**
   * Associates the given Wizard Character to the given Character Code, inside the correspondent
   *  BiMaps.
   *
   * @param aCode   : the given Character Code to associate.
   * @param aWizard : the given Wizard Character to associate.
   */
  private void addWizardCodification(@NotNull final CharacterCode aCode,
                                     @NotNull final IWizard aWizard) {
    wizardsCode.putIfAbsent(aCode, aWizard);
    addPlayableCodification(aCode, aWizard);
  }

  /**
   * Creates a CharacterCode for the given Playable Character, and associates it to the every
   *  codification of the controller, also adds the correspondent Handler as a subscriber of the
   *  given character.
   * In added to that, this method puts the character inside the Turns Queue.
   *
   * @param aPlayable : the given Playable Character.
   *
   * @return the code associated to the given Wizard.
   */
  private CharacterCode initializePlayable(@NotNull final IPlayableCharacter aPlayable) {
    final CharacterCode newCode = characterCodeFactory.createPlayable();
    addPlayableCodification(newCode, aPlayable);
    aPlayable.addPlayableKoListener(playableKoHandler);
    addTurn(aPlayable);
    return newCode;
  }

  /**
   * Creates a CharacterCode for the given Wizard, and associates it to the every codification of
   *  the controller, also adds the correspondent Handler as a subscriber of the given character.
   * In added to that, this method puts the character inside the Turns Queue.
   *
   * @param aWizard : the given Wizard.
   *
   * @return the code associated to the given Wizard.
   */
  private CharacterCode initializeWizard(@NotNull final IWizard aWizard) {
    final CharacterCode newCode = characterCodeFactory.createWizard();
    addWizardCodification(newCode, aWizard);
    aWizard.addPlayableKoListener(playableKoHandler);
    addTurn(aWizard);
    return newCode;
  }

  /**
   * Creates a CharacterCode for the given Enemy Character, and associates it to the every
   *  codification of the controller, also adds the correspondent Handler as a subscriber of the
   *  given character.
   * In added to that, this method puts the character inside the Turns Queue.
   *
   * @param anEnemy : the given Enemy.
   *
   * @return the code associated to the given Wizard.
   */
  private CharacterCode initializeEnemy(@NotNull final Enemy anEnemy) {
    final CharacterCode newCode = characterCodeFactory.createEnemy();
    addGenericCodification(newCode, anEnemy);
    anEnemy.addEnemyKoListener(enemyKoHandler);
    addTurn(anEnemy);
    return newCode;
  }

  /**
   * Creates a new Engineer, with the typical parameters, taking care that it was not created
   *  before.
   * The Playable Characters can only be created before start the game ({@code Phase0}).
   *
   * @see #initializePlayable(IPlayableCharacter)
   */
  public CharacterCode createAnEngineer(@NotNull final String name, final int maxHealthPoints,
                                        final int defense) {
    if (! (currentPhase.isPhase(0))) {
      return null;
    }
    final Engineer aCharacter = characterFactory.createAnEngineer(name, maxHealthPoints, defense);
    return initializePlayable(aCharacter);
  }

  /**
   * Creates a new Knight, with the typical parameters, taking care that it was not created
   *  before.
   * The Playable Characters can only be created before start the game ({@code Phase0}).
   *
   * @see #initializePlayable(IPlayableCharacter)
   */
  public CharacterCode createAKnight(@NotNull final String name, final int maxHealthPoints,
                                     final int defense) {
    if (! (currentPhase.isPhase(0))) {
      return null;
    }
    final Knight aCharacter = characterFactory.createAKnight(name, maxHealthPoints, defense);
    return initializePlayable(aCharacter);
  }

  /**
   * Creates a new Thief, with the typical parameters, taking care that it was not created
   *  before.
   * The Playable Characters can only be created before start the game ({@code Phase0}).
   *
   * @see #initializePlayable(IPlayableCharacter)
   */
  public CharacterCode createAThief(@NotNull final String name, final int maxHealthPoints,
                                    final int defense) {
    if (! (currentPhase.isPhase(0))) {
      return null;
    }
    final Thief aCharacter = characterFactory.createAThief(name, maxHealthPoints, defense);
    return initializePlayable(aCharacter);
  }

  /**
   * Creates a new Black Wizard, with the typical parameters, taking care that it was not created
   *  before.
   * The Playable Characters can only be created before start the game ({@code Phase0}).
   *
   * @see #initializeWizard(IWizard)
   */
  public CharacterCode createABlackWizard(@NotNull final String name, final int maxHealthPoints,
                                          final int maxMana, final int defense) {
    if (! (currentPhase.isPhase(0))) {
      return null;
    }
    final BlackWizard aCharacter =
        characterFactory.createABlackWizard(name, maxHealthPoints, maxMana, defense);
    return initializeWizard(aCharacter);
  }

  /**
   * Creates a new White Wizard, with the typical parameters, taking care that it was not created
   *  before.
   * The Playable Characters can only be created before start the game ({@code Phase0}).
   *
   * @see #initializeWizard(IWizard)
   */
  public CharacterCode createAWhiteWizard(@NotNull final String name, final int maxHealthPoints,
                                          final int maxMana, final int defense) {
    if (! (currentPhase.isPhase(0))) {
      return null;
    }
    final WhiteWizard aCharacter =
        characterFactory.createAWhiteWizard(name, maxHealthPoints, maxMana, defense);
    return initializeWizard(aCharacter);
  }

  /**
   * Creates a new Enemy, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #initializeEnemy(Enemy)
   */
  public CharacterCode createAnEnemy(@NotNull final String name, final int maxHealthPoints,
                                     final int defense, final int attack, final int weight) {

    final Enemy aCharacter =
        characterFactory.createAnEnemy(name, maxHealthPoints, defense, attack, weight);
    return initializeEnemy(aCharacter);
  }

  /* Character Creation : END */


  /* Character Getters : BEGIN */

  /** Returns the name of the character associated to the given {@code CharacterCode}. */
  public String getNameOfCharacter(@NotNull final CharacterCode code) {
    return charactersCode.get(code).getName();
  }

  /** Returns the maximum HP of the character associated to the given {@code CharacterCode}. */
  public int getMaxHpOfCharacter(@NotNull final CharacterCode code) {
    return charactersCode.get(code).getMaxHp();
  }

  /** Returns the current HP of the character associated to the given {@code CharacterCode}. */
  public int getCurrentHpOfCharacter(@NotNull final CharacterCode code) {
    return charactersCode.get(code).getCurrentHp();
  }

  /** Returns the maximum HP of the character associated to the given {@code CharacterCode}. */
  public int getMaxManaOfCharacter(@NotNull final CharacterCode code) {
    return wizardsCode.get(code).getMaxMana();
  }

  /** Returns the current HP of the character associated to the given {@code CharacterCode}. */
  public int getCurrentManaOfCharacter(@NotNull final CharacterCode code) {
    return wizardsCode.get(code).getCurrentMana();
  }

  /** Returns the defense of the character associated to the given {@code CharacterCode}. */
  public int getDefOfCharacter(@NotNull final CharacterCode code) {
    return charactersCode.get(code).getDef();
  }

  /** Returns the attack power of the character associated to the given {@code CharacterCode}. */
  public int getAtkOfCharacter(@NotNull final CharacterCode code) throws NonEquippedWeapon {
    return charactersCode.get(code).getAtk();
  }

  /** Returns the weight of the character associated to the given {@code CharacterCode}. */
  public int getWeightOfCharacter(@NotNull final CharacterCode code) throws NonEquippedWeapon {
    return charactersCode.get(code).getWeight();
  }

  /** Returns true is the character associated to the given code is out of combat. */
  public boolean isKo(@NotNull final CharacterCode code) {
    return charactersCode.get(code).isKo();
  }

  /**
   * Returns the code associated to the weapon which the playable character (associated to the
   *  given code) has equipped.
   * It could be null, when the character has no weapon equipped. And, if the given code is not
   *  associated to a playable character, the returned parameter will be null too.
   */
  public WeaponCode getEquippedWeaponOf(@NotNull final CharacterCode code) {
    final IWeapon weapon = playableCharactersCode.get(code).getEquippedWeapon();
    return weaponsCode.inverse().get(weapon);
  }

  /* Character Getters : END */


  /* Weapon Creation : BEGIN */

  /**
   * Creates a new weapon code, and associates the given weapon to that code using the BiMap
   *  {@code weaponCode}.
   *
   * @param aWeapon : the given character to add to the maps.
   *
   * @return the WeaponCode which represents the Weapon.
   */
  private WeaponCode addWeaponCodification(@NotNull final IWeapon aWeapon) {
    final WeaponCode code = weaponCodeFactory.create();
    weaponsCode.putIfAbsent(code, aWeapon);
    return code;
  }

  /**
   * Creates a new Axe, with the typical parameters, taking care that it was not created before.
   *
   * @see #addWeaponCodification(IWeapon)
   */
  public WeaponCode createAnAxe(@NotNull final String name, final int damage, final int weight) {
    final Axe aWeapon = weaponFactory.createAnAxe(name, damage, weight);
    return addWeaponCodification(aWeapon);
  }

  /**
   * Creates a new Bow, with the typical parameters, taking care that it was not created before.
   *
   * @see #addWeaponCodification(IWeapon)
   */
  public WeaponCode createABow(@NotNull final String name, final int damage, final int weight) {
    final Bow aWeapon = weaponFactory.createABow(name, damage, weight);
    return addWeaponCodification(aWeapon);
  }

  /**
   * Creates a new Knife, with the typical parameters, taking care that it was not created before.
   *
   * @see #addWeaponCodification(IWeapon)
   */
  public WeaponCode createAKnife(@NotNull final String name, final int damage, final int weight) {
    final Knife aWeapon = weaponFactory.createAKnife(name, damage, weight);
    return addWeaponCodification(aWeapon);
  }

  /**
   * Creates a new Staff, with the typical parameters, taking care that it was not created before.
   *
   * @see #addWeaponCodification(IWeapon)
   */
  public WeaponCode createAStaff(@NotNull final String name, final int damage, final int weight) {
    final Staff aWeapon = weaponFactory.createAStaff(name, damage, weight);
    return addWeaponCodification(aWeapon);
  }

  /**
   * Creates a new Sword, with the typical parameters, taking care that it was not created before.
   *
   * @see #addWeaponCodification(IWeapon)
   */
  public WeaponCode createASword(@NotNull final String name, final int damage, final int weight) {
    final Sword aWeapon = weaponFactory.createASword(name, damage, weight);
    return addWeaponCodification(aWeapon);
  }

  /* Weapon Creation : END */


  /* Weapon Getters : BEGIN */

  /** Returns the name of the Weapon associated to the given WeaponCode. */
  public String getNameOfWeapon(@NotNull final WeaponCode code) {
    return weaponsCode.get(code).getName();
  }

  /** Returns the damage of the Weapon associated to the given WeaponCode. */
  public int getDamageOfWeapon(@NotNull final WeaponCode code) {
    return weaponsCode.get(code).getDamage();
  }

  /** Returns the weight of the Weapon associated to the given WeaponCode. */
  public int getWeightOfWeapon(@NotNull final WeaponCode code) {
    return weaponsCode.get(code).getWeight();
  }

  /**
   * Returns the code associated to the currentCarrier of the Weapon associated to the given
   *  WeaponCode.
   */
  public CharacterCode getCurrentCarrierOfWeapon(@NotNull final WeaponCode code) {
    final IPlayableCharacter carrier = weaponsCode.get(code).getCurrentCarrier();
    return playableCharactersCode.inverse().get(carrier);
  }

  /* Weapon Getters : END */

}
