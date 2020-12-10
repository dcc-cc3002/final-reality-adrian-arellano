package com.github.cc3002.finalreality.model.weapon;

import org.jetbrains.annotations.NotNull;

/**
 * Exception which is thrown when the {@code GameController} tries to create a weapon which
 *  already exists; it was created before.
 *
 * @author Adrian Arellano.
 *
 * @see com.github.cc3002.finalreality.model.character.CharacterAlreadyCreated : similar to this.
 */
public class WeaponAlreadyCreated extends Exception {

  /**
   * Creates a WeaponAlreadyCreated exception, a message is needed to know the name of the
   *  duplicated weapon.
   *
   * @param message : The name of the rejected {@code IWeapon}.
   */
  public WeaponAlreadyCreated(@NotNull final String message) {
    super(message);
  }

}
