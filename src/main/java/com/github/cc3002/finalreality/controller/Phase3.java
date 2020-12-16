package com.github.cc3002.finalreality.controller;

/**
 * This class represents a Phase3 of the game.
 * To know more about the implemented phases, please read the {@code README.md} of this project, the
 *  section {@code Tackling the problem of the phases}, explains deeply how this phases it works and
 *  why.
 *
 * @author Adrian Arellano.
 */
public class Phase3 extends GamePhase {

  @Override
  boolean isPhase(final int phaseNumber) {
    return phaseNumber == 3;
  }

  @Override
  public void nextPlayer() {
    super.changeGamePhase(new Phase1());
  }

}
