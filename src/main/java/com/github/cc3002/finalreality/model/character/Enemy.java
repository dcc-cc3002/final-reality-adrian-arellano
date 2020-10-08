package com.github.cc3002.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import org.jetbrains.annotations.NotNull;

/**
 * A class that represents a non-playable character of the game,
 *  an enemy.
 *
 * @author Adrian Arellano.
 */
public class Enemy extends AbstractCharacter {

  private final int weight;

  /**
   * Creates a new character.
   *
   * @param name       : the Enemy's name.
   * @param turnsQueue : The queue of the game in which the character is.
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
  public int hashCode() {
    return Objects.hash(getName(), getWeight(), this.getClass());
  }

  @Override
  protected boolean equalsAuxiliary(@NotNull final ICharacter aCharacter) {
    if (!(aCharacter instanceof Enemy)) {
      return false;
    }
    Enemy that = (Enemy) aCharacter;
    return getWeight() == that.getWeight();
  }


}
