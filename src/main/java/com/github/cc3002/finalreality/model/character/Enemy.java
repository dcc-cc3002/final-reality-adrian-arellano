package com.github.cc3002.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that represents a non-playable character of the game,
 *  an enemy.
 *
 * @author Adrian Arellano.
 */
public class Enemy extends AbstractCharacter {

  private final int attack;
  private final int weight;

  /**
   * Creates a new enemy.
   *
   * @param name            : the Enemy's name.
   * @param maxHealthPoints : the maximum health points that this character can have.
   * @param defense         : the defense of this character.
   * @param attack          : the attack of this character for the rest of the game.
   * @param weight          : the weight of this character for the rest of the game.
   * @param turnsQueue      : the queue of the game in which the character is.
   */
  public Enemy(@NotNull final String name, final int maxHealthPoints,
               final int defense, final int attack, final int weight,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, maxHealthPoints, defense ,turnsQueue);
    this.attack = attack;
    this.weight = weight;
  }

  @Override
  public int getAtk() {
    return attack;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxHp(), getDef(), getAtk(), getWeight(), Enemy.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy that = (Enemy) o;
    return compareAttributes(that) &&
        getAtk() == that.getAtk() &&
        getWeight() == that.getWeight();
  }

}
