package com.github.cc3002.finalreality.controller;

import java.util.Objects;

/**
 * This class was made to give the view a way to handle the characters without using the characters
 *  themself.
 * This class cloud be replaced by simply using numbers as indexes, but the idea of this is to have
 *  a different class so as not to generate typing errors, like using any int as an index when it is
 *  not.
 *
 * @author Adrian Arellano.
 */
public class CharacterCode {

  private final int index;
  private final boolean isPlayable;
  private final boolean isWizard;

  /**
   * Creates a new code of a character.
   *
   * @param index      : a number to tell apart between different CharacterCodes.
   * @param isPlayable : a variable to know is the represented character is playable or not.
   * @param isWizard   : a variable to know is the represented character is wizard or not.
   *//* Package Private creator */
  CharacterCode(final int index, final boolean isPlayable, final boolean isWizard) {
    this.index = index;
    this.isPlayable = isPlayable;
    this.isWizard = isWizard;
  }

  /** Returns the index. Method created only to be used to compare. */
  private int getIndex() {
    return index;
  }

  /** Returns true if this code represents a playable character, and false if not. */
  public boolean isPlayable() {
    return isPlayable;
  }

  /** Returns true if this code represents a wizard character, and false if not. */
  public boolean isWizard() {
    return isWizard;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIndex(), isPlayable(), isWizard(), CharacterCode.class);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CharacterCode)) {
      return false;
    }
    final CharacterCode that = (CharacterCode) o;
    return getIndex() == that.getIndex()
        && isPlayable() == that.isPlayable()
        && isWizard() == that.isWizard();
  }

}
