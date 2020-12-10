package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.Engineer;
import com.github.cc3002.finalreality.model.character.playable.Knight;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * This class represents an Axe in the game.
 *
 * @author Adrian Arellano.
 */
public class Axe extends AbstractWeapon {

  /**
   * Creates a new Axe.
   *
   * @param name   : the name of the weapon.
   * @param damage : the damage that the weapon deals.
   * @param weight : the weight of the weapon (which affects to the speed of an attack).
   */
  public Axe(@NotNull final String name, final int damage, final int weight) {
    super(name, damage, weight);
  }

  @Override
  public void equippedByAnEngineer(@NotNull final Engineer anEngineer) throws NonAvailableWeapon,
      UnexpectedBehavior {
    super.availableToBeEquippedBy(anEngineer);
  }

  @Override
  public void equippedByAKnight(@NotNull final Knight aKnight) throws NonAvailableWeapon,
      UnexpectedBehavior {
    super.availableToBeEquippedBy(aKnight);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getDamage(), getWeight(), Axe.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Axe)) {
      return false;
    }
    final Axe that = (Axe) o;
    return compareAttributes(that);
  }

}
