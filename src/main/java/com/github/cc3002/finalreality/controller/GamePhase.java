package com.github.cc3002.finalreality.controller;

import org.jetbrains.annotations.NotNull;

/**
 * This class represents a generic phase of the game with their own methods. Is the base for a
 *  specialized phase.
 * To know more about the implemented phases, please read the {@code README.md} of this project, the
 *  section {@code Tackling the problem of the phases}, explains deeply how this phases it works and
 *  why.
 *
 * @author Adrian Arellano.
 */
public class GamePhase {

  private GameController controller;

  /**
   * Sets the controller for the current GamePhase.
   *//* package private */
  void setGameController(@NotNull final GameController theController) {
    controller = theController;
  }

  /** Changes the GamePhase associated to the controller. */
  protected void changeGamePhase(@NotNull final GamePhase aPhase) {
    controller.setCurrentPhase(aPhase);
  }

  /**
   * Returns true if the current Phase is the phase which match the given number.
   *//* package private */
  boolean isPhase(final int phaseNumber) {
    return false;
  }

  /** Throws an error. */
  private void error() {
    throw new RuntimeException();
  }

  /**
   * When it's possible: changes the GamePhase of the GameController to the next numerical Phase.
   */
  public void nextPhase() {
    error();
  }

  /**
   * When it's possible: changes the GamePhase of the GameController to the first numerical Phase
   *  (Phase1).
   */
  public void nextPlayer() {
    error();
  }

}
