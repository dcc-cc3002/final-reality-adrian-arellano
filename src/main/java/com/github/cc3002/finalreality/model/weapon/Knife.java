package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.Knight;
import com.github.cc3002.finalreality.model.character.playable.Thief;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Knife in the game.
 *
 * @author Adrian Arellano
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
  protected boolean equalsAuxiliary(@NotNull final IWeapon aWeapon) {
    return aWeapon instanceof Knife;
  }

}
