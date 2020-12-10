package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.Engineer;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This class represents a Bow in the game.
 *
 * @author Adrian Arellano.
 */
public class Bow extends AbstractWeapon {

  /**
   * Creates a new Bow.
   *
   * @param name   : the name of the weapon.
   * @param damage : the damage that the weapon deals.
   * @param weight : the weight of the weapon (which affects to the speed of an attack).
   */
  public Bow(@NotNull final String name, final int damage, final int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByAnEngineer(@NotNull final Engineer anEngineer) throws NonAvailableWeapon,
      UnexpectedBehavior {
    super.availableToBeEquippedBy(anEngineer);
  }

  @Override
  public void equippedByAThief(@NotNull final Thief aThief) throws NonAvailableWeapon,
      UnexpectedBehavior {
    super.availableToBeEquippedBy(aThief);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight(), Bow.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Bow)) {
      return false;
    }
    final Bow that = (Bow) o;
    return compareAttributes(that);
  }

}
