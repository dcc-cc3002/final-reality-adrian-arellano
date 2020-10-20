package com.github.cc3002.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * An abstract class which implements the basic behaviour of a character in the game.
 *
 * It's important to highlight that, this is an abstract class,
 *  so the whole behavior of a character is not here.
 *
 * @author Ignacio Slater Muñoz.
 * @author Adrian Arellano.
 */
public abstract class AbstractCharacter implements ICharacter {

  private final String name;
  private final int maxHealthPoints;
  private final int defense;
  private final BlockingQueue<ICharacter> turnsQueue;

  private int currentHealthPoints;
  private ScheduledExecutorService scheduledExecutor;
  /**
   * The generic creator of a new character.
   *
   * @param name            : the character's name.
   * @param maxHealthPoints : the maximum health points that this character can have.
   * @param defense         : the defense of this character.
   * @param turnsQueue      : the queue of the game in which the character is.
   */
  protected AbstractCharacter(@NotNull final String name,
                              final int maxHealthPoints, final int defense,
                              @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    this.name = name;
    this.maxHealthPoints = maxHealthPoints;
    currentHealthPoints = maxHealthPoints;
    this.defense = defense;
    this.turnsQueue = turnsQueue;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getMaxHp() {
    return maxHealthPoints;
  }

  @Override
  public int getCurrentHp() {
    return currentHealthPoints;
  }

  @Override
  public int getDef() {
    return defense;
  }

  /** Returns true is this player is K.O. */
  protected boolean isKo() {
    return currentHealthPoints <= 0;
  }

  /** Sets the current health points of this character. */
  private void setHp(final int newHp) {
    currentHealthPoints = min(maxHealthPoints, max(0, newHp));
  }

  @Override
  public void attack(@NotNull final ICharacter opponent) throws NonEquippedWeapon {
    if (!isKo()) {
      opponent.receiveAtk(getAtk());
    }
  }

  @Override
  public void receiveAtk(int damage) {
    damage = max(0, damage - getDef());
    setHp(getCurrentHp() - damage);
  }

  /** Adds this character to the turns queue. */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public void waitTurn() throws NonEquippedWeapon {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, getWeight() * 100, TimeUnit.MILLISECONDS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxHp(), getDef(), getClass());
  }

  /**
   * Method created to complete the method {@code equals}
   *  of each {@code ICharacter}'s sub-class.
   *
   * @param aCharacter : a candidate to be equals to this character.
   *
   * @see #equals(Object)
   */
  protected abstract boolean equalsAuxiliary(@NotNull final ICharacter aCharacter);

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ICharacter)) {
      return false;
    }
    final ICharacter that = (ICharacter) o;
    return getName().equals(that.getName()) &&
        getMaxHp() == that.getMaxHp() &&
        getDef() == getDef() &&
        this.equalsAuxiliary(that);
  }

}
