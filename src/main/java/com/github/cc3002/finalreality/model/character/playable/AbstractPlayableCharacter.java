package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

import com.github.cc3002.finalreality.model.character.NonEquippedWeapon;
import com.github.cc3002.finalreality.model.weapon.*;
import org.jetbrains.annotations.NotNull;


/**
 * An abstract class which implements the basic behaviour
 *  of a playable character in the game.
 *
 * It's important to highlight that, this is an abstract class,
 *  so the whole behavior of a character is not here.
 *
 * @author Adrian Arellano
 */
public abstract class AbstractPlayableCharacter extends AbstractCharacter implements IPlayableCharacter {

  /* This variable could be null */
  protected IWeapon equippedWeapon;

  /**
   * Initializes some values of a generic playable character.
   *
   * @param name       : the PlayableCharacter's name.
   * @param turnsQueue : the queue of the game in which the character is.
   */
  protected AbstractPlayableCharacter(@NotNull final String name,
                                      @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }

  @Override
  public int getWeight() throws NonEquippedWeapon {
    if (equippedWeapon == null) {
      throw new NonEquippedWeapon();
    }
    return equippedWeapon.getWeight();
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * Method created to be overridden by the sub-classes of this
   *  abstract class.
   * The objective of this method, is let the weapon know the
   *  class of playable character who is trying to equip it.
   */
  protected abstract void equipAuxiliary(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior;

  @Override
  public void equip(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior {
    if (aWeapon.equals(this.equippedWeapon)) {
      /* Nothing to do, the weapon is already equipped. */
      return;
    }
    this.equipAuxiliary(aWeapon);
  }

  @Override
  public void actuallyEquip(@NotNull final IWeapon aWeapon) throws UnexpectedBehavior {
    if (equippedWeapon != null) {
      equippedWeapon.unEquippedBy(this);
    }
    this.equippedWeapon = aWeapon;
  }

  /*
   * Equals: If two IPlayableCharacters are the same,
   *  it does not depend on their equipped weapons.
   */

}
