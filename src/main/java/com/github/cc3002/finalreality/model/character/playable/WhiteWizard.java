package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * This class represents a white wizard character in the game.
 *
 * @author Adrian Arellano
 */
public class WhiteWizard extends AbstractPlayableCharacter {

  /**
   * Creates a new white wizard.
   *
   * @param name            : the PlayableCharacter's name.
   * @param maxHealthPoints : the maximum health points that this character can have.
   * @param defense         : the defense of this character.
   * @param turnsQueue      : the queue of the game in which the character is.
   */
  public WhiteWizard(@NotNull final String name,
                     final int maxHealthPoints, final int defense,
                     @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, maxHealthPoints, defense ,turnsQueue);
  }

  @Override
  protected void equipAuxiliary(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior {
    aWeapon.equippedByAWhiteWizard(this);
  }

}
