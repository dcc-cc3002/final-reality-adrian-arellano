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

  protected IWeapon equippedWeapon = null;

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

  @Override
  public void equip(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon {
    if (aWeapon.equals(this.equippedWeapon)) {
      /* Nothing to do, the weapon is already equipped. */
      return;
    }
    aWeapon.tryingToBeEquippedBy(this);
  }

  /**
   * Created to be called, only by its own sub-classes.
   * Tries to equip the {@param aWeapon}, if that doesn't throw a error, 
   * then unEquips the {@param equippedWeapon} and sets {@param aWeapon}
   * as the current equipped weapon of this PlayableCharacter.
   */
  protected void actuallyEquip(@NotNull final IWeapon aWeapon) throws NonAvailableWeapon {
    aWeapon.equippedBy(this);

    equippedWeapon.unEquippedBy(this);
    this.equippedWeapon = aWeapon;
  }

  /**
   * Created to throw a {@exception UnsupportedWeapon}
   *  when a WeaponType is unsupported by a
   *  IPlayableCharacter's Sub-Type.
   */
  private void error() throws UnsupportedWeapon {
    throw new UnsupportedWeapon();
  }

  @Override
  public void equipAnAxe(@NotNull final Axe anAxe) throws UnsupportedWeapon, NonAvailableWeapon {
    error();
  }

  @Override
  public void equipABow(@NotNull final Bow aBow) throws UnsupportedWeapon, NonAvailableWeapon {
    error();
  }

  @Override
  public void equipAKnife(@NotNull final Knife aKnife) throws UnsupportedWeapon, NonAvailableWeapon {
    error();
  }

  @Override
  public void equipAStaff(@NotNull final Staff aStaff) throws UnsupportedWeapon, NonAvailableWeapon {
    error();
  }

  @Override
  public void equipASword(@NotNull final Sword aSword) throws UnsupportedWeapon, NonAvailableWeapon {
    error();
  }

  /*
   * Equals: If two IPlayableCharacters are the same,
   *  it does not depend on their equipped weapons.
   */

}
