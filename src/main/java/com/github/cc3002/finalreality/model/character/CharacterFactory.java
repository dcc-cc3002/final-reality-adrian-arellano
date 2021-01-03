package com.github.cc3002.finalreality.model.character;

import com.github.cc3002.finalreality.model.character.playable.Engineer;
import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

/**
 * This class is a factory of {@code ICharacter}s, the idea of been a factory is to create new
 *  characters, been sure that they are all different.
 * This factory implements a Flyweight Pattern.
 *
 * @author Adrian Arellano.
 */
public class CharacterFactory {

  private final BlockingQueue<ICharacter> turnsQueue;
  private final Map<IPlayableCharacter, Boolean> playableCharacters;
  private final Map<Enemy, Boolean> enemies;

  /**
   * Creates a new character's factory.
   *
   * @param turnsQueue         : the queue of turns needed to create each character.
   * @param playableCharacters : A map of playable characters, where the value associated to each
   *                          key is the result of {@code THIS_PLAYABLE_CHARACTER.isKo()}.
   * @param enemies            : A map of enemies, where the value associated to each key is the
   *                          result of {@code THIS_ENEMY.isKo()}.
   */
  public CharacterFactory(@NotNull final BlockingQueue<ICharacter> turnsQueue,
                          @NotNull final Map<IPlayableCharacter, Boolean> playableCharacters,
                          @NotNull final Map<Enemy, Boolean> enemies) {
    this.turnsQueue = turnsQueue;
    this.playableCharacters = playableCharacters;
    this.enemies = enemies;
  }

  /**
   * Adds the given playable character to the map {@code characters}, if and only if the character
   *  was not present before.
   *
   * @param newCharacter : the character to add.
   *
   * @return True if the character was not created before, an false if it was.
   */
  private boolean addPlayableCharacter(@NotNull final IPlayableCharacter newCharacter) {

    return playableCharacters.putIfAbsent(newCharacter, false) == null;
  }

  /**
   * Creates a new Engineer, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addPlayableCharacter(IPlayableCharacter)
   */
  public Engineer createAnEngineer(@NotNull final String name, final int maxHealthPoints,
                                   final int defense) {

    final Engineer newEngineer = new Engineer(name, maxHealthPoints, defense, turnsQueue);
    if (addPlayableCharacter(newEngineer)) {
      return newEngineer;
    }
    return null;
  }

  /**
   * Creates a new Knight, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addPlayableCharacter(IPlayableCharacter)
   */
  public Knight createAKnight(@NotNull final String name, final int maxHealthPoints,
                              final int defense) {

    final Knight newKnight = new Knight(name, maxHealthPoints, defense, turnsQueue);
    if (addPlayableCharacter(newKnight)) {
      return newKnight;
    }
    return null;
  }

  /**
   * Creates a new Thief, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addPlayableCharacter(IPlayableCharacter)
   */
  public Thief createAThief(@NotNull final String name, final int maxHealthPoints,
                            final int defense) {

    final Thief newThief = new Thief(name, maxHealthPoints, defense, turnsQueue);
    if (addPlayableCharacter(newThief)) {
      return newThief;
    }
    return null;
  }

  /**
   * Creates a new Black Wizard, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addPlayableCharacter(IPlayableCharacter)
   */
  public BlackWizard createABlackWizard(@NotNull final String name, final int maxHealthPoints,
                                        final int maxMana, final int defense) {

    final BlackWizard newBlackWizard =
        new BlackWizard(name, maxHealthPoints, maxMana, defense, turnsQueue);
    if (addPlayableCharacter(newBlackWizard)) {
      return newBlackWizard;
    }
    return null;
  }

  /**
   * Creates a new White Wizard, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addPlayableCharacter(IPlayableCharacter)
   */
  public WhiteWizard createAWhiteWizard(@NotNull final String name, final int maxHealthPoints,
                                        final int maxMana, final int defense) {

    final WhiteWizard newWhiteWizard =
        new WhiteWizard(name, maxHealthPoints, maxMana, defense, turnsQueue);
    if (addPlayableCharacter(newWhiteWizard)) {
      return newWhiteWizard;
    }
    return null;
  }

  /**
   * Creates a new Enemy, with the typical parameters, taking care that it was not created
   *  before.
   *
   * @see #addPlayableCharacter(IPlayableCharacter) : a part of the code is so similar.
   */
  public Enemy createAnEnemy(@NotNull final String name, final int maxHealthPoints,
                             final int defense, final int attack, final int weight) {

    final Enemy newEnemy = new Enemy(name, maxHealthPoints, defense, attack, weight, turnsQueue);
    if (enemies.putIfAbsent(newEnemy, false) == null) {
      return newEnemy;
    }
    return null;
  }

}
