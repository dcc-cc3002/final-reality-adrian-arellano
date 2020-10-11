package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.IPlayableCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Knife in the game.
 *
 * @author Adrian Arellano
 */
public class Knife extends AbstractWeapon {

  /**
   * Initializes the values of a Knife.
   *
   * @param name   : the name of the weapon.
   * @param damage : the damage that the weapon deals.
   * @param weight : the weight of the weapon (which affects to the speed of an attack).
   */
  public Knife(@NotNull final String name, final int damage,
                  final int weight) {
    super(name, damage, weight);
  }

  @Override
  protected boolean equalsAuxiliary(@NotNull final IWeapon aWeapon) {
    return aWeapon instanceof Knife;
  }

  @Override
  public void tryingToBeEquippedBy(@NotNull final IPlayableCharacter aPlayableCharacter) throws UnsupportedWeapon, NonAvailableWeapon {
    aPlayableCharacter.equipAKnife(this);
  }

}
