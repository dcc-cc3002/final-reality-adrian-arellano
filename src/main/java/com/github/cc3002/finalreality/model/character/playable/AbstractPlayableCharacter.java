package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.NonEquippedWeapon;
import com.github.cc3002.finalreality.model.weapon.IWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class which implements the basic behaviour of a playable character in the game.
 *
 * It's important to highlight that, this is an abstract class, so the whole behavior of a character
 *  is not necessarily here.
 *
 * @author Adrian Arellano.
 */
public abstract class AbstractPlayableCharacter extends AbstractCharacter implements
    IPlayableCharacter {

  /* This variable could be null! */
  protected IWeapon equippedWeapon;

  /**
   * Initializes the basic parameters for any playable character.
   *
   * @param name            : the playable character's name.
   * @param maxHealthPoints : the maximum health points that this character can have.
   * @param defense         : the defense of this character.
   * @param turnsQueue      : the queue of the game in which the character is.
   */
  protected AbstractPlayableCharacter(@NotNull final String name,
                                      final int maxHealthPoints, final int defense,
                                      @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, maxHealthPoints, defense ,turnsQueue);
  }

  /**
   * Returns the current equipped weapon, if and only if, the current equipped weapon is not
   *  {@code null}.
   *
   * @throws NonEquippedWeapon : exception thrown when the current weapon is null.
   *
   * @see #getAtk()
   * @see #getWeight()
   */
  private IWeapon advanceGetWeapon() throws NonEquippedWeapon {
    if (equippedWeapon == null) {
      throw new NonEquippedWeapon();
    }
    return equippedWeapon;
  }

  @Override
  public int getAtk() throws NonEquippedWeapon {
    return advanceGetWeapon().getDamage();
  }

  @Override
  public int getWeight() throws NonEquippedWeapon {
    return advanceGetWeapon().getWeight();
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   * This method was created to be overridden by the sub-classes of this abstract class.
   * The objective of this method, is to let the weapon know the class of this playable character
   *  which is trying to equip it.
   *
   * @see #equip(IWeapon)
   */
  protected abstract void equipAuxiliary(@NotNull final IWeapon aWeapon) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon;

  @Override
  public void equip(@NotNull final IWeapon aWeapon) throws NonAvailableWeapon, UnexpectedBehavior,
      UnsupportedWeapon {
    if (isKo()) {
      return;
    }
    /* It's not enough that the both weapons were equals, they has to be exactly the same weapon
     * (the same reference). */
    if (aWeapon == equippedWeapon) {
      return;
    }
    equipAuxiliary(aWeapon);
  }

  @Override
  public void actuallyEquip(@NotNull final IWeapon aWeapon) throws UnexpectedBehavior {
    if (equippedWeapon != null) {
      equippedWeapon.unEquippedBy(this);
    }
    equippedWeapon = aWeapon;
  }

  /* Equals: If two IPlayableCharacters are the same,
   *  it does not depend on their equipped weapons. */

}
