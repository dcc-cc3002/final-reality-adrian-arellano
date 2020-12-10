package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This class represents a Staff in the game.
 *
 * @author Adrian Arellano.
 */
public class Staff extends AbstractWeapon {

  /**
   * Creates a new Staff.
   *
   * @param name   : the name of the weapon.
   * @param damage : the damage that the weapon deals.
   * @param weight : the weight of the weapon (which affects to the speed of an attack).
   */
  public Staff(@NotNull final String name, final int damage, final int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByABlackWizard(@NotNull final BlackWizard aBlackWizard) throws
      NonAvailableWeapon, UnexpectedBehavior {
    super.availableToBeEquippedBy(aBlackWizard);
  }

  @Override
  public void equippedByAWhiteWizard(@NotNull final WhiteWizard aWhiteWizard) throws
      NonAvailableWeapon, UnexpectedBehavior {
    super.availableToBeEquippedBy(aWhiteWizard);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight(), Staff.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Staff)) {
      return false;
    }
    final Staff that = (Staff) o;
    return compareAttributes(that);
  }

}
