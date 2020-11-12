package com.github.cc3002.finalreality.model.character.playable.wizard;

import com.github.cc3002.finalreality.model.character.AbstractCharacter;
import com.github.cc3002.finalreality.model.character.ICharacter;
import com.github.cc3002.finalreality.model.character.playable.AbstractPlayableCharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

/**
 * An abstract class which implements the basic behaviour of a wizard, which is a kind of playable
 *  character in the game.
 *
 * It's important to highlight that, this is an abstract class, so the whole behavior of a wizard
 *  is not necessarily here.
 *
 * @author Adrian Arellano.
 */
public abstract class AbstractWizard extends AbstractPlayableCharacter implements IWizard {

  private final int maxMana;

  private int currentMana;

  /**
   * Initializes the basic parameters for any wizard.
   *
   * @param name            : the playable character's name.
   * @param maxHealthPoints : the maximum health points that this character can have.
   * @param maxMana         : the maximum mana that this character can have.
   * @param defense         : the defense of this character.
   * @param turnsQueue      : the queue of the game in which the character is.
   */
  protected AbstractWizard(@NotNull final String name, final int maxHealthPoints, final int maxMana,
                        final int defense, @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(name, maxHealthPoints, defense, turnsQueue);
    this.maxMana = maxMana;
    currentMana = maxMana;
  }

  @Override
  public int getMaxMana() {
    return maxMana;
  }

  @Override
  public int getCurrentMana() {
    return currentMana;
  }

  /**
   * Compares the attributes of the given wizard with the attributes of this character, to know
   *  if they are equals or not.
   *
   * @param that : the given character to be compared.
   *
   * @see AbstractCharacter#compareAttributes(ICharacter)
   * @see #equals(Object)
   */
  protected boolean compareAttributes(@NotNull final IWizard that) {
    return super.compareAttributes(that) &&
        getMaxMana() == that.getMaxMana();
  }

}
