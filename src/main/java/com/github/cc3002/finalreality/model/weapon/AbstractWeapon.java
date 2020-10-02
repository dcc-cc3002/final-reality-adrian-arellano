package com.github.cc3002.finalreality.model.weapon;

import com.github.cc3002.finalreality.model.character.playable.*;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  private IPlayableCharacter currentCarrier = null;

  /**
   * The basic creator for a weapon, which initialize the object with:
   *    @param name: the name of the Weapon.
   *    @param damage: the damage that this Weapon deals.
   *    @param weight: the weight of the Weapon which affects to the speed of an attack.
   */
  public AbstractWeapon(final String name, final int damage, final int weight) {
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

  /* Double dispatch by PlayableCharacter.equip */

  /**
   * Checks if this weapon has carrier:
   *  throws an {@exception NonAvailableWeapon} if it has
   *  sets the {@param aPlayableCharacter} as the carrier if not.
   */
  protected void equippedBy(IPlayableCharacter aPlayableCharacter) throws NonAvailableWeapon {
    if (! (this.currentCarrier == null)) {
      throw new NonAvailableWeapon();
    } else {
      this.currentCarrier = aPlayableCharacter;
    }
  }

  /** Throws the NonEquippableWeapon Exception. */
  private void equippableError() throws NonEquippableWeapon {
    throw new NonEquippableWeapon();
  }

  @Override
  public void takenByKnight(Knight aKnight) throws NonEquippableWeapon, NonAvailableWeapon {
    equippableError();
  }

  @Override
  public void takenByEngineer(Engineer anEngineer) throws NonEquippableWeapon, NonAvailableWeapon {
    equippableError();
  }

  @Override
  public void takenByThief(Thief aThief) throws NonEquippableWeapon, NonAvailableWeapon {
    equippableError();
  }

  @Override
  public void takenByBlackWizard(BlackWizard aBlackWizard) throws NonEquippableWeapon, NonAvailableWeapon {
    equippableError();
  }

  @Override
  public void takenByWhiteWizard(WhiteWizard aWhiteWizard) throws NonEquippableWeapon, NonAvailableWeapon {
    equippableError();
  }

  @Override
  public void unEquippedBy(IPlayableCharacter supposedCarrier) {
    if (supposedCarrier.equals(currentCarrier))
     this.currentCarrier = null;
  }

  @Override
  public int hashCode() {
    /* I don't think that the carrier should affect this. */
    return Objects.hash(getName(), getDamage(), getWeight());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof IWeapon)) {
      return false;
    }
    final IWeapon aWeapon = (IWeapon) o;
    /* Compares carriers, which could be null,
     * so the matter is the memory allocated space. */
    return getDamage() == aWeapon.getDamage() &&
           getWeight() == aWeapon.getWeight() &&
           getName().equals(aWeapon.getName()) &&
           getCurrentCarrier() == aWeapon.getCurrentCarrier();
  }
}
