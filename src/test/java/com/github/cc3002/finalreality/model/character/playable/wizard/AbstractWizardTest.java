package com.github.cc3002.finalreality.model.character.playable.wizard;

import com.github.cc3002.finalreality.model.character.AbstractPlayableCharacterTest;
import com.github.cc3002.finalreality.model.character.playable.wizard.IWizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Abstract class containing the common tests for all the types of wizard characters.
 *
 * @author Adrian Arellano.
 * @see IWizard
 */
public abstract class AbstractWizardTest extends AbstractPlayableCharacterTest {

  private static IWizard testWizard;


  @Override @BeforeEach
  protected void setUp() {
    super.setUp();
    testWizard = (IWizard) testPlayableCharacter;
  }

  /**
   * Tests if the variable Current Mana was well initialized.
   */ @Test
  void currentManaTest() {
     assertEquals(testWizard.getMaxMana(), testWizard.getCurrentMana());
  }

}
