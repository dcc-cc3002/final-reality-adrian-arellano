package com.github.cc3002.finalreality.model.character;

import org.jetbrains.annotations.NotNull;

/**
 * Exception which is thrown when the {@code GameController} tries to create a character which
 *  already exists; it was created before.
 *
 * @author Adrian Arellano.
 */
public class CharacterAlreadyCreated extends Exception {

  /**
   * Creates a CharacterAlreadyCreated exception, a message is needed to know the name of the
   *  duplicated character.
   *
   * @param message : The name of the rejected {@code ICharacter}.
   */
  public CharacterAlreadyCreated(@NotNull final String message) {
    super(message);
  }

}
