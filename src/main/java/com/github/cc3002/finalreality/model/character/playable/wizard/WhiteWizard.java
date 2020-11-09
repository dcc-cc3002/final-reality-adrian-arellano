package com.github.cc3002.finalreality.model.character.playable.wizard;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * This class represents a white wizard character in the game.
 *
 * @author Adrian Arellano.
 */
public class WhiteWizard extends AbstractWizard {

  /**
   * Creates a new white wizard.
   *
   * @param name            : the PlayableCharacter's name.
   * @param maxHealthPoints : the maximum health points that this character can have.
   * @param maxMana         : the maximum mana that this character can have.
   * @param defense         : the defense of this character.
   * @param turnsQueue      : the queue of the game in which the character is.
   */
  public WhiteWizard(@NotNull final String name, final int maxHealthPoints, final int maxMana,
                     final int defense, @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, maxHealthPoints, maxMana, defense ,turnsQueue);
  }

  @Override
  protected void equipAuxiliary(@NotNull final IWeapon aWeapon) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon {
    aWeapon.equippedByAWhiteWizard(this);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaxHp(), getMaxMana(), getDef(), WhiteWizard.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WhiteWizard)) {
      return false;
    }
    final WhiteWizard that = (WhiteWizard) o;
    return compareAttributes(that);
  }

}
