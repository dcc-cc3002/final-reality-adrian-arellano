package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
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
  protected void equipAuxiliary(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon {
    aWeapon.equippedByAKnight(this);
  }

  @Override
  protected boolean equalsAuxiliary(@NotNull final ICharacter aCharacter) {
    return aCharacter instanceof Knight;
  }

}
