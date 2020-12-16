package com.github.cc3002.finalreality.controller;

import org.jetbrains.annotations.NotNull;

/**
 * This class represents a Phase0 of the game. The stating state of the game.
 * To know more about the implemented phases, please read the {@code README.md} of this project, the
 *  section {@code Tackling the problem of the phases}, explains deeply how this phases it works and
 *  why.
 *
 * @author Adrian Arellano.
 */
public class Phase0 extends GamePhase {

  /**
   * Creates a new Phase0 needed to start a game.
   * This is the only legal way to initialize a Phase outside the Phase complex.
   *
   * @param controller : the controller of the phase.
   */
  public Phase0(@NotNull final GameController controller) {
    super.setGameController(controller);
  }

  @Override
  boolean isPhase(final int phaseNumber) {
    return phaseNumber == 0;
  }

  @Override
  public void nextPlayer() {
    super.changeGamePhase(new Phase1());
  }

}
