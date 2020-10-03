package com.github.cc3002.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game.
 *
 * @author Ignacio Slater Muñoz
 * @author <Your name>
 */
public class Enemy extends AbstractCharacter {

  private final int weight;

  /**
   * Creates a new character.
   *    @param name: the PlayableCharacter's name
   *
   *    @param turnsQueue: the queue with the characters waiting for their turn
   */
  public Enemy(@NotNull final String name, final int weight,
               @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
    this.weight = weight;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public boolean equals(final Object o) {
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    if (!(getWeight() == enemy.getWeight())) {
      return false;
    }
    return super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }
}
