package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * This class represents a white knight character in the game.
 *
 * @author Adrian Arellano
 */
public class Knight extends AbstractPlayableCharacter {

  /**
   * Creates a new knight.
   *
   * @param name       : the PlayableCharacter's name.
   * @param turnsQueue : the queue of the game in which the character is.
   */
  public Knight(@NotNull final String name,
                @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }

  @Override
  public void equipAnAxe(@NotNull final Axe anAxe) throws NonAvailableWeapon {
    super.actuallyEquip(anAxe);
  }

  @Override
  public void equipAKnife(@NotNull final Knife aKnife) throws NonAvailableWeapon {
    super.actuallyEquip(aKnife);
  }

  @Override
  public void equipASword(@NotNull final Sword aSword) throws NonAvailableWeapon {
    super.actuallyEquip(aSword);
  }

  @Override
  protected boolean equalsAuxiliary(@NotNull final ICharacter aCharacter) {
    return aCharacter instanceof Knight;
  }

}
