package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * This class represents a black wizard character in the game.
 *
 * @author Adrian Arellano
 */
public class BlackWizard extends AbstractPlayableCharacter {

  /**
   * Creates a new black wizard.
   *
   * @param name       : the PlayableCharacter's name.
   * @param turnsQueue : the queue of the game in which the character is.
   */
  public BlackWizard(@NotNull final String name,
                     @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }

  @Override
  public void equipAKnife(@NotNull final Knife aKnife) throws NonAvailableWeapon {
    super.actuallyEquip(aKnife);
  }

  @Override
  public void equipAStaff(@NotNull Staff aStaff) throws NonAvailableWeapon {
    super.actuallyEquip(aStaff);
  }

  @Override
  protected boolean equalsAuxiliary(@NotNull final ICharacter aCharacter) {
    return aCharacter instanceof BlackWizard;
  }

}
