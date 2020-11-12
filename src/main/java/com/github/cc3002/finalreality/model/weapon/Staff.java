package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Staff in the game.
 *
 * @author Adrian Arellano
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
  protected boolean equalsAuxiliary(@NotNull final IWeapon aWeapon) {
    return aWeapon instanceof Staff;
  }

}
