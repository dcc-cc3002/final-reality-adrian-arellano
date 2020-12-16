package com.github.cc3002.finalreality.controller;

/**
 * This class represents a Phase1 of the game.
 * To know more about the implemented phases, please read the {@code README.md} of this project, the
 *  section {@code Tackling the problem of the phases}, explains deeply how this phases it works and
 *  why.
 *
 * @author Adrian Arellano.
 */
public class Phase1 extends GamePhase {

  @Override
  boolean isPhase(final int phaseNumber) {
    return phaseNumber == 1;
  }

  @Override
  public void nextPhase() {
    super.changeGamePhase(new Phase2());
  }

}
