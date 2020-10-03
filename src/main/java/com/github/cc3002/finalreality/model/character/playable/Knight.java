package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.NonEquippableWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class Knight extends AbstractPlayableCharacter {

  /**
   * Creates a new character.
   *
   * @param name       : the PlayableCharacter's name
   * @param turnsQueue : the queue with the characters waiting for their turn
   */
  public Knight(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }

  @Override
  public void equip(IWeapon aWeapon) throws NonEquippableWeapon, NonAvailableWeapon {
    if (!(aWeapon.equals(this.equippedWeapon))) {
      aWeapon.takenByKnight(this);
      super.holdWeapon(aWeapon);
    }
  }

}
