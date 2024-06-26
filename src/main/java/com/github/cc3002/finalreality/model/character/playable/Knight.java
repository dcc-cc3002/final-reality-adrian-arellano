package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * This class represents a white knight character in the game.
 *
 * @author Adrian Arellano.
 */
public class Knight extends AbstractPlayableCharacter {

  /**
   * Creates a new knight.
   *
   * @param name            : the knight's name.
   * @param maxHealthPoints : the maximum health points that this character can have.
   * @param defense         : the defense of this character.
   * @param turnsQueue      : the queue of the game in which the character is.
   */
  public Knight(@NotNull final String name, final int maxHealthPoints, final int defense,
                @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, maxHealthPoints, defense ,turnsQueue);
  }

  @Override
  protected void equipAuxiliary(@NotNull final IWeapon aWeapon) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon {
    aWeapon.equippedByAKnight(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxHp(), getDef(), Knight.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Knight)) {
      return false;
    }
    final Knight that = (Knight) o;
    return compareAttributes(that);
  }

}
