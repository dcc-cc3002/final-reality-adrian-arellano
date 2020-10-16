package com.github.cc3002.finalreality.model.weapon;

import org.jetbrains.annotations.NotNull;

/**
 * Exception which is thrown when any part of the program
 *  does not expected to be used as it is been used.
 * In other words: when some method is misused.
 */
public class UnexpectedBehavior extends Exception {

  /**
   * Creates a UnexpectedBehavior exception, and given that this
   *  kind of exception is so general, a message is needed to
   *  explain what happened.
   *
   * @param message : The reason which the exception was thrown.
   */
  public UnexpectedBehavior(@NotNull final String message) {
    super(message);
  }

}
