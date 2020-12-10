package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.*;
import com.github.cc3002.finalreality.model.character.playable.wizard.BlackWizard;
import com.github.cc3002.finalreality.model.character.playable.wizard.WhiteWizard;
import org.jetbrains.annotations.NotNull;

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

  private void setCurrentCarrier(final IPlayableCharacter newCarrier) {
    currentCarrier = newCarrier;
  }

  @Override
  public void unEquippedBy(@NotNull final IPlayableCharacter supposedCarrier) throws
      UnexpectedBehavior {
    if (! supposedCarrier.equals(getCurrentCarrier())) {
      throw new UnexpectedBehavior("The suppose carrier is not the current carrier.");
    }
    setCurrentCarrier(null);
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
    if (getCurrentCarrier() != null) {
      throw new NonAvailableWeapon();
    }
    setCurrentCarrier(newCarrier);
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

  /**
   * Compares the attributes of the given wizard with the attributes of this character, to know
   *  if they are equals or not.
   *
   * @param that : the given character to be compared.
   *
   * @see #equals(Object)
   */
  protected boolean compareAttributes(@NotNull final IWeapon that) {
    return getDamage() == that.getDamage() &&
        getWeight() == that.getWeight() &&
        getName().equals(that.getName());
  }

}
