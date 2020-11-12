package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.*;
import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * An abstract class which implements the basic behaviour of a weapon in the game.
 *
 * It's important to highlight that, this is an abstract class, so the whole behavior of a weapon is
 *  not necessarily here.
 *
 * @author Adrian Arellano.
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  private IPlayableCharacter currentCarrier;

  /**
   * Initializes the basic parameters for any weapon.
   *
   * @param name   : the name of the weapon.
   * @param damage : the damage that the weapon deals.
   * @param weight : the weight of the weapon (which affects to the speed of an attack).
   */
  protected AbstractWeapon(@NotNull final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }

  @Override
  public IPlayableCharacter getCurrentCarrier() {
    return currentCarrier;
  }

  @Override
  public void unEquippedBy(@NotNull final IPlayableCharacter supposedCarrier) throws
      UnexpectedBehavior {
    /* It's not enough that the both characters were equals, they has to be exactly the same
     * character (the same reference). */
    if (supposedCarrier != currentCarrier) {
      throw new UnexpectedBehavior("The suppose carrier is not the current carrier.");
    }
    this.currentCarrier = null;
  }

  /**
   * Method created to be used only by the subclasses of this class.
   * Throws an {@exception  NonAvailableWeapon} if this weapon has a carrier, and if not, sets the
   *  given character as the new {@code currentCarrier} of this weapon, and after that, calls the
   *  carrier to actually equip this Weapon.
   *
   * @throws NonAvailableWeapon : when this weapon has a carrier, so it can't be carried by two
   *                              characters a time.
   * @throws UnexpectedBehavior : when something else went wrong.
   *
   * @see IPlayableCharacter#actuallyEquip(IWeapon)
   */
  protected void availableToBeEquippedBy(@NotNull final IPlayableCharacter newCarrier) throws
      NonAvailableWeapon, UnexpectedBehavior {
    if (this.currentCarrier != null) {
      throw new NonAvailableWeapon();
    }
    this.currentCarrier = newCarrier;
    newCarrier.actuallyEquip(this);
  }

  /**
   * Created to throw an {@exception UnsupportedWeapon} when a IPlayableCharacter's Sub-Type does
   *  not support this WeaponType.
   */
  private void error() throws UnsupportedWeapon {
    throw new UnsupportedWeapon();
  }

  @Override
  public void equippedByAnEngineer(@NotNull final Engineer anEngineer) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon {
    error();
  }

  @Override
  public void equippedByAKnight(@NotNull final Knight aKnight) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon {
    error();
  }

  @Override
  public void equippedByAThief(@NotNull final Thief aThief) throws NonAvailableWeapon,
      UnexpectedBehavior, UnsupportedWeapon {
    error();
  }

  @Override
  public void equippedByABlackWizard(@NotNull final BlackWizard aBlackWizard) throws
      NonAvailableWeapon, UnexpectedBehavior, UnsupportedWeapon {
    error();
  }

  @Override
  public void equippedByAWhiteWizard(@NotNull final WhiteWizard aWhiteWizard) throws
      NonAvailableWeapon, UnexpectedBehavior, UnsupportedWeapon {
    error();
  }

  @Override
  public int hashCode() {
    /* I think that the carrier must not affect this. */
    return Objects.hash(getName(), getDamage(), getWeight(), this.getClass());
  }

  /**
   * Method created to complete the method {@code equals} of each IWeapon's sub-class.
   *
   * @param aWeapon : a candidate to be equals to this weapon.
   *
   * @see #equals(Object o)
   */
  protected abstract boolean equalsAuxiliary(@NotNull final IWeapon aWeapon);

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IWeapon)) {
      return false;
    }
    final IWeapon that = (IWeapon) o;
    /* Compares carriers, which could be null,
     * so the matter is the memory allocated space. */
    return getDamage() == that.getDamage() &&
        getWeight() == that.getWeight() &&
        getName().equals(that.getName()) &&
        getCurrentCarrier() == that.getCurrentCarrier() &&
        this.equalsAuxiliary(that);
  }

}
