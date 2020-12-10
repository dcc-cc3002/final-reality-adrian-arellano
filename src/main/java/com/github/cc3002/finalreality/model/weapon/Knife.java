package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This class represents a Knife in the game.
 *
 * @author Adrian Arellano.
 */
public class Knife extends AbstractWeapon {

  /**
   * Creates a new Knife.
   *
   * @param name   : the name of the weapon.
   * @param damage : the damage that the weapon deals.
   * @param weight : the weight of the weapon (which affects to the speed of an attack).
   */
  public Knife(@NotNull final String name, final int damage, final int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByAKnight(@NotNull final Knight aKnight) throws NonAvailableWeapon,
      UnexpectedBehavior {
    super.availableToBeEquippedBy(aKnight);
  }

  @Override
  public void equippedByAThief(@NotNull final Thief aThief) throws NonAvailableWeapon,
      UnexpectedBehavior {
    super.availableToBeEquippedBy(aThief);
  }

  @Override
  public void equippedByABlackWizard(@NotNull final BlackWizard aBlackWizard) throws NonAvailableWeapon,
      UnexpectedBehavior {
    super.availableToBeEquippedBy(aBlackWizard);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight(), Knife.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Knife)) {
      return false;
    }
    final Knife that = (Knife) o;
    return compareAttributes(that);
  }

}
