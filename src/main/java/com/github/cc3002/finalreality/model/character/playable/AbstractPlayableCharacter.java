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
   * The generic creator of a new playable character.
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
   * Returns the current equipped weapon, if and only if,
   *  the current equipped weapon is not {@code null}.
   * If the current weapon is null, this method throws an exception.
   *
   * @throws NonEquippedWeapon : the exception thrown.
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
   * Method created to be overridden by the sub-classes of this
   *  abstract class.
   * The objective of this method, is let the weapon know the
   *  class of playable character who is trying to equip it.
   */
  protected abstract void equipAuxiliary(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior;

  @Override
  public void equip(@NotNull final IWeapon aWeapon) throws UnsupportedWeapon, NonAvailableWeapon, UnexpectedBehavior {
    if (isKo()) {
      return;
    }
    if (aWeapon.equals(equippedWeapon)) {
      /* Nothing to do, the weapon is already equipped. */
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
  @Override
  protected boolean equalsAuxiliary(@NotNull final ICharacter aCharacter) {
    return getClass().equals(aCharacter.getClass());
  }

}
