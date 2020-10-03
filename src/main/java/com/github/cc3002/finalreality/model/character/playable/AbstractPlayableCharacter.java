package com.github.cc3002.finalreality.model.character.playable;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;

import java.util.concurrent.BlockingQueue;

import com.github.cc3002.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;


/**
 * A class that holds all the information of a single character of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author <Your name>
 */
public abstract class AbstractPlayableCharacter extends AbstractCharacter implements IPlayableCharacter {

  protected IWeapon equippedWeapon = null;

  /**
   * Creates a new character.
   *    @param name: the PlayableCharacter's name
   *    @param turnsQueue: the queue with the characters waiting for their turn
   */
  public AbstractPlayableCharacter(@NotNull String name,
                                   @NotNull BlockingQueue<ICharacter> turnsQueue) {
    super(name, turnsQueue);
  }

  @Override
  public IWeapon getEquippedWeapon() {
    return equippedWeapon;
  }

  /**
   *  This method is supposed to be called, only by an IWeapon sub-class.
   *   UnEquips the Weapon held and sets the weapon of this
   *   PlayableCharacter as {@param aWeapon}.
   */
  public void holdWeapon(IWeapon aWeapon) {
    equippedWeapon.unEquippedBy(this);
    this.equippedWeapon = aWeapon;
  }

  @Override
  public int getWeight() {
    if (!(equippedWeapon == null)) {
      return equippedWeapon.getWeight();
    }
    return 0;
  }

  /*
   * Equals does not depend on the equippedWeapon.
   * [Creat that dependency will make a infinity loop!]
   */

}
