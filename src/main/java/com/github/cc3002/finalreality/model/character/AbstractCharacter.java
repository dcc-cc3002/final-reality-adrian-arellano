package com.github.cc3002.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

/**
 * An abstract class which implements the basic behaviour of a character in the game.
 *
 * It's important to highlight that, this is an abstract class so, the whole behavior of a character
 *  is not necessarily here.
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

  /* Observer Pattern : to subscribe as a listener the sub-Classes have their own methods. */
  protected final PropertyChangeSupport koNotification = new PropertyChangeSupport(this);

  /**
   * Initializes the basic parameters for any character.
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

  @Override
  public boolean isKo() {
    return currentHealthPoints <= 0;
  }

  @Override
  public boolean isPlayable() {
    return false;
  }

  /** Sets the current health points of this character. */
  private void setHp(final int newHp) {
    currentHealthPoints = min(getMaxHp(), max(0, newHp));
  }

  /**
   * Reduces the current health points of this character by the given amount, if that amount is
   *  negative, then nothing happens.
   * Every time that the HP is reduced, there is the possibility to knock out this character, in
   *  that case, this method report a property change (using {@code koNotification}).
   *
   * @param reducePoints : the amount of points which will be discounted.
   *
   * @see com.github.cc3002.finalreality.controller.PlayableKoHandler for playable characters.
   * @see com.github.cc3002.finalreality.controller.EnemyKoHandler for non playable characters
   *                                                               (enemies).
   */
  private void reduceHp(final int reducePoints) {
    setHp(getCurrentHp() - max(0, reducePoints));
    if (isKo()) {
      koNotification.firePropertyChange(
          "A_CHARACTER_HAS_BEEN_DEFEATED", null, this
      );
    }
  }

  @Override
  public void attack(@NotNull final ICharacter opponent) throws NonEquippedWeapon {
    if (isKo()) {
      return;
    }
    opponent.receiveAtk(getAtk());
  }

  @Override
  public void receiveAtk(int atkPower) {
    if (isKo()){
      return;
    }
    /* if the value is negative, .reduceHp() solve that problem. */
    reduceHp(atkPower - getDef());
  }

  /** Adds this character to the turns queue. */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public void waitTurn() throws NonEquippedWeapon {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, (long) getWeight() * 100, TimeUnit.MILLISECONDS);
  }

  /**
   * Compares the attributes of the given character with the attributes of this character, to know
   *  if they are equals or not.
   *
   * @param that : the given character to be compared.
   *
   * @see #equals(Object)
   */
  protected boolean compareAttributes(@NotNull final ICharacter that) {
    return getName().equals(that.getName())
        && getMaxHp() == that.getMaxHp()
        && getDef() == that.getDef();
  }

}
