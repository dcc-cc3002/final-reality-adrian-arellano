package com.github.cc3002.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

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
  private final BlockingQueue<ICharacter> turnsQueue;
  private ScheduledExecutorService scheduledExecutor;

  /**
   * Initializes some values of a generic character.
   *
   * @param name       : the character's name.
   * @param turnsQueue : the queue of the game in which the character is.
   */
  protected AbstractCharacter(@NotNull final String name,
                              @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    this.name = name;
    this.turnsQueue = turnsQueue;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void waitTurn() throws NonEquippedWeapon {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor.schedule(this::addToQueue, this.getWeight() * 100, TimeUnit.MILLISECONDS);
  }

  /** Adds this character to the turns queue. */
  private void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, this.getClass());
  }

  /**
   * Method created to complete the method {@code equals}
   *  of each ICharacter's sub-class.
   *
   * @param aCharacter : an object which {@code equals}
   *                   said that is an instance of ICharacter.
   *
   * @see #equals(Object o)
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
        this.equalsAuxiliary(that);
  }

}
