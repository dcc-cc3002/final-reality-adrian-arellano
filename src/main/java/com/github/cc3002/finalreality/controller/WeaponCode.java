package com.github.cc3002.finalreality.controller;

import java.util.Objects;

/**
 * This class was made to give the view a way to handle the weapons without using the weapons
 *  themself.
 * This class cloud be replaced by simply using numbers as indexes, but the idea of this is to have
 *  a different class so as not to generate typing errors, like using any int as an index when it is
 *  not.
 *
 * @author Adrian Arellano.
 *
 * @see CharacterCode : this is a simplified copy of that.
 */
public class WeaponCode {

  private final int index;

  /**
   * Creates a new code of a weapon.
   *
   * @param index : a number to tell apart between different WeaponCodes.
   * */
  public WeaponCode(final int index) {
    this.index = index;
  }

  /** Returns the index. Method created only to be used to compare. */
  private int getIndex() {
    return index;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIndex(), WeaponCode.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof WeaponCode)) {
      return false;
    }
    final WeaponCode that = (WeaponCode) o;
    return getIndex() == that.getIndex();
  }

}
