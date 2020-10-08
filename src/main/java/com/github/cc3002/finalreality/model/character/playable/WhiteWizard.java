package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.Staff;
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
   * @param name       : the PlayableCharacter's name.
   * @param turnsQueue : the queue of the game in which the character is.
   */
  public WhiteWizard(@NotNull final String name,
                     @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }

  @Override
  public void equipAStaff(@NotNull final Staff aStaff) throws NonAvailableWeapon {
    super.actuallyEquip(aStaff);
  }

  @Override
  protected boolean equalsAuxiliary(@NotNull final ICharacter aCharacter) {
    return aCharacter instanceof WhiteWizard;
  }

}
