package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * This class represents an engineer character in the game.
 *
 * @author Adrian Arellano
 */
public class Engineer extends AbstractPlayableCharacter {

  /**
   * Creates a new engineer.
   *
   * @param name       : the PlayableCharacter's name.
   * @param turnsQueue : the queue of the game in which the character is.
   */
  public Engineer(@NotNull final String name,
                  @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }

  @Override
  public void equipAnAxe(@NotNull final Axe anAxe) throws NonAvailableWeapon {
    super.actuallyEquip(anAxe);
  }

  @Override
  public void equipABow(@NotNull final Bow aBow) throws NonAvailableWeapon {
    super.actuallyEquip(aBow);
  }

  @Override
  protected boolean equalsAuxiliary(@NotNull final ICharacter aCharacter) {
    return aCharacter instanceof Engineer;
  }

}

