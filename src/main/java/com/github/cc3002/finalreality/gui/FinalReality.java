package com.github.cc3002.finalreality.gui;

import com.github.cc3002.finalreality.controller.CharacterCode;
import com.github.cc3002.finalreality.controller.GameController;
import com.github.cc3002.finalreality.controller.WeaponCode;
import com.github.cc3002.finalreality.model.character.NonEquippedWeapon;
import com.github.cc3002.finalreality.model.weapon.NonAvailableWeapon;
import com.github.cc3002.finalreality.model.weapon.UnexpectedBehavior;
import com.github.cc3002.finalreality.model.weapon.UnsupportedWeapon;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * This Class is the gui of the game Final Reality.
 *
 * @author Adrian Arellano.
 * @version 1.0-RELEASE
 */
public class FinalReality extends Application {
  private static final String RESOURCE_PATH = "src/main/resources/";

  private static final int SQUARE_LENGTH = 20;
  private static final int WIDTH  = 23 * SQUARE_LENGTH;
  private static final int HEIGHT = 35 * SQUARE_LENGTH;

  private static final Font SUPER_TITLE_FONT = new Font("Verdana", 27);
  private static final Font TITLE_FONT = new Font("Verdana", 23);
  private static final Font ATTRIBUTES_FONT = new Font("Verdana", 15);
  private static final Font INFORMATION_FONT = new Font("Verdana", 12);
  private static final Font EXCEPTION_FONT = new Font("Verdana", 10);

  private static final GameController controller = new GameController();
  private static final Group root = new Group();

  private static final Map<String, Text> turnOwnerAttributesLabel = new HashMap<>();
  private static final Map<String, Text> weaponAttributesLabel = new HashMap<>();
  private static final Map<String, Text> targetAttributesLabel = new HashMap<>();
  private static final Map<String, Text> exceptionMessage = new HashMap<>();
  private static final Text information = new Text();

  private static final Map<String, Button> equipButtons = new HashMap<>();
  private static final Map<String, Button> attackButtons = new HashMap<>();
  private static Button startButton;

  private static CharacterCode turnOwner;
  private static WeaponCode weapon;
  private static CharacterCode target;

  private static List<WeaponCode> inventory;
  private static int inventoryIndex;
  private static List<CharacterCode> opponents;
  private static int opponentIndex;

  @Override
  public void start(@NotNull Stage stage) throws FileNotFoundException {
    stage.setTitle("Final Reality");
    Scene scene = new Scene(root, WIDTH, HEIGHT);
    var background =
        new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "background.jpg")));
    root.getChildren().add(background);

    setUpGameElements();
    initializeLabels();
    initializeButtons();

    stage.setScene(scene);
    stage.show();
  }

  private void setUpGameElements() {
    /* Playable Characters */
    controller.createABlackWizard("Zara (B-Wizard)", 13, 2, 4);
    controller.createAKnight("Geralt (Knight)", 25, 8);
    controller.createAnEngineer("Elon Musk (Engineer)", 10, 4);
    /* Weapons */
    controller.createAnAxe("Useless Axe", 0, 12);
    controller.createAnAxe("Harsh Axe", 20, 100);
    controller.createABow("Super Duper Bow", 15, 32);
    controller.createABow("Broken thread Bow", 0, 30);
    controller.createAKnife("Sharp Knife", 7, 3);
    controller.createAKnife("Dull Knife", 2, 15);
    controller.createAStaff("Simple Staff", 10, 9);
    controller.createAStaff("Iridescent Staff", 20, 200);
    controller.createAStaff("Tree Branch (staff)", 2, 20);
    controller.createAStaff("Broken tree Branch (staff)", 0, 40);
    controller.createASword("Tizona (Mio Cid's sword)", 25, 30);
    controller.createASword("Training Sword", 2, 12);
    controller.createASword("Umbrella (sword)", 3, 13);
    /* Enemies */
    controller.createAnEnemy("Slime", 20, 1, 3, 7);
    controller.createAnEnemy("Hydra", 200, 0, 7, 70);
    controller.createAnEnemy("Little Ant", 1, 0, 2, 1);
    controller.createAnEnemy("Leshen", 10, 15, 50, 150);
  }

  private void formatTextUnmodifiable(@NotNull final Text text, @NotNull final Font font,
                                      final double x, final double y) {
    text.setFont(font);
    text.setX(x * SQUARE_LENGTH);
    text.setY(y * SQUARE_LENGTH);
    root.getChildren().add(text);
  }

  private void formatText(@NotNull final Font font, final double x, final double y,
                          @NotNull final Map<String, Text> map, @NotNull final String str) {
    Text text = new Text();
    formatTextUnmodifiable(text, font, x, y);
    map.put(str, text);
  }

  private void initializeLabels() {
    /* Unmodifiable. */
    formatTextUnmodifiable(new Text("INVENTORY"), SUPER_TITLE_FONT, 7, 12);
    formatTextUnmodifiable(new Text("TARGET"),    SUPER_TITLE_FONT, 9, 22);
    /* Modifiable */
    // TurnOwner
    formatText(     TITLE_FONT, 4,  2, turnOwnerAttributesLabel, "name");
    formatText(ATTRIBUTES_FONT, 4,  4, turnOwnerAttributesLabel, "hp");
    formatText(ATTRIBUTES_FONT, 12, 4, turnOwnerAttributesLabel, "def");
    formatText(ATTRIBUTES_FONT, 4,  6, turnOwnerAttributesLabel, "atk");
    formatText(ATTRIBUTES_FONT, 12, 6, turnOwnerAttributesLabel, "weight");
    formatText(ATTRIBUTES_FONT, 4, 8, turnOwnerAttributesLabel, "weapon");
    // Weapon
    formatText(     TITLE_FONT, 4,  14, weaponAttributesLabel, "name");
    formatText(ATTRIBUTES_FONT, 4,  16, weaponAttributesLabel, "damage");
    formatText(ATTRIBUTES_FONT, 12, 16, weaponAttributesLabel, "weight");
    // Target
    formatText(     TITLE_FONT, 4,  24, targetAttributesLabel, "name");
    formatText(ATTRIBUTES_FONT, 4,  26, targetAttributesLabel, "hp");
    formatText(ATTRIBUTES_FONT, 12, 26, targetAttributesLabel, "def");
    formatText(ATTRIBUTES_FONT, 4,  28, targetAttributesLabel, "atk");
    formatText(ATTRIBUTES_FONT, 12, 28, targetAttributesLabel, "weight");
    // Exception
    formatText(EXCEPTION_FONT, 11, 17.5, exceptionMessage, "equip");
    formatText(EXCEPTION_FONT, 11, 29.5, exceptionMessage, "attack");
    formatText(EXCEPTION_FONT, 12, 34, exceptionMessage, "error");
    exceptionMessage.get("error").setFill(Color.RED);
    // Information
    formatTextUnmodifiable(information, INFORMATION_FONT, 11, 32);
  }

  private static void cleanNearlyAllLabels() {
    cleanExceptionMessage();
    turnOwnerAttributesLabel.forEach((str, text) -> text.setText(""));
    weaponAttributesLabel.forEach((str, text) -> text.setText(""));
    targetAttributesLabel.forEach((str, text) -> text.setText(""));
  }

  private void initializeButtons() {
    /* EQUIP */
    setUpEquipButton();
    setUpEquipLeftButton();
    setUpEquipRightButton();
    /* ATTACK */
    setUpAttackButton();
    setUpAttackLeftButton();
    setUpAttackRightButton();
    /* START */
    setUpStartButton();
    disableButtons();
  }

  private static void updateLabels() {
    updateTurnOwnerAttributes();
    updateWeaponAttributes();
    updateTargetAttributes();
  }

  private static void startANewTurn() {
    information.setText("");
    nextTurn();
    if (turnOwner.isPlayable()) {
      updateInventory();
      updateOpponents();
      updateLabels();
      enableButtons();
      startButton.setDisable(true);
    } else {
      playTurnIfEnemy();
    }
  }

  private static void playTurnIfEnemy() {
    cleanNearlyAllLabels();
    String attacked, attacker = controller.getNameOfCharacter(turnOwner);
    try {
      attacked = controller.getNameOfCharacter(controller.enemyTurn());
      String message = attacker + " attacked " + attacked + "\n";
      message += controller.getParty().size() + " allies alive\n";
      message += controller.getEnemies().size() + " enemies left";
      information.setText(message);
    } catch (NonEquippedWeapon e) {
      showErrorMessage("An enemy tried to attack using a weapon,\n please report it with the author");
    }
  }

  private static void enableButtons() {
    equipButtons.forEach(((s, button) -> button.setDisable(false)));
    attackButtons.forEach(((s, button) -> button.setDisable(false)));
    /* Enabling the other button */
    startButton.setDisable(true);
  }

  private static void disableButtons() {
    equipButtons.forEach(((s, button) -> button.setDisable(true)));
    attackButtons.forEach(((s, button) -> button.setDisable(true)));
    /* Disabling the other button */
    startButton.setDisable(false);
  }

  private static void nextTurn() {
    try {
      controller.updateTurnOwner();
    } catch (InterruptedException e) {
      showErrorMessage("The program was interrupted while it was waiting for the next character");
    }
    turnOwner = controller.getTurnOwner();
  }

  private static void updateInventory() {
    inventory = new ArrayList<>(controller.getInventory());

    WeaponCode carriedWeapon = controller.getEquippedWeaponOf(turnOwner);
    if (carriedWeapon != null) {
      inventory.add(carriedWeapon);
    }
    inventoryIndex = inventory.size() - 1;
    weapon = inventory.get(inventoryIndex);
  }

  private static void updateOpponents() {
    opponents = new ArrayList<>(controller.getEnemies());

    opponentIndex = opponents.size() - 1;
    target = opponents.get(opponentIndex);
  }

  private static void showErrorMessage(@NotNull final String message) {
    exceptionMessage.get("error").setText(message);
  }

  private static void showEquipMessage(@NotNull final String message) {
    exceptionMessage.get("equip").setText(message);
  }

  private static void showAttackMessage(@NotNull final String message) {
    exceptionMessage.get("attack").setText(message);
  }

  private static void cleanExceptionMessage() {
    showEquipMessage("");
    showAttackMessage("");
  }

  private static void updateCharacterAttributes(@NotNull final CharacterCode code,
                                                @NotNull final Map<String, Text> map) {
    map.get("name").setText("Turn of: " + controller.getNameOfCharacter(code));
    map.get("hp").setText("HP: " + controller.getCurrentHpOfCharacter(code) + "/" +
        controller.getMaxHpOfCharacter(code));
    map.get("def").setText("DEF: " + controller.getDefOfCharacter(code));
    String atk, weight;
    try {
      atk = Integer.toString(controller.getAtkOfCharacter(code));
      weight = Integer.toString(controller.getWeightOfCharacter(code));
    } catch (NonEquippedWeapon e) {
      atk = weight = "???";
    }
    map.get("atk").setText("ATK: " + atk);
    map.get("weight").setText("Weight: " + weight);
  }

  private static void updateTurnOwnerAttributes() {
    updateCharacterAttributes(turnOwner, turnOwnerAttributesLabel);
    final WeaponCode weapon = controller.getEquippedWeaponOf(turnOwner);
    String weaponName;
    if (weapon == null) {
      weaponName = "None";
    } else {
      weaponName = controller.getNameOfWeapon(weapon);
    }
    turnOwnerAttributesLabel.get("weapon").setText("Equipped Weapon: " + weaponName);
  }

  private static void updateTargetAttributes() {
    updateCharacterAttributes(target, targetAttributesLabel);
  }

  private static void updateWeaponAttributes() {
    final Map<String, Text> map = weaponAttributesLabel;
    map.get("name").setText(controller.getNameOfWeapon(weapon));
    map.get("damage").setText("Damage: " + controller.getDamageOfWeapon(weapon));
    map.get("weight").setText("Weight: " + controller.getWeightOfWeapon(weapon));
  }

  private Button setUpGenericButton(@NotNull final String str, final int x, final int y) {
    Button button = new Button(str);
    button.setLayoutX(x * SQUARE_LENGTH);
    button.setLayoutY(y * SQUARE_LENGTH);
    button.setFocusTraversable(false);
    button.setFont(ATTRIBUTES_FONT);
    root.getChildren().add(button);
    return button;
  }

  private void setUpEquipButton() {
    Button button = setUpGenericButton("EQUIP", 5, 17);
    button.setOnAction(FinalReality::equipAction);
    equipButtons.put("central", button);
  }

  /**
   * Tries to equip the showed weapon (showed in the gui), to the current turn owner.
   */
  public static void equipAction(ActionEvent actionEvent) {
    try {
      controller.equipWeapon(weapon);
      updateTurnOwnerAttributes();
      cleanExceptionMessage();
    } catch (UnexpectedBehavior e) {
      showErrorMessage("An unexpected behavior has occur,\n please report it with the author");
    } catch (UnsupportedWeapon e) {
      showEquipMessage("This character cannot port\n this weapon");
    } catch (NonAvailableWeapon e) {
      showEquipMessage("This weapon is been carried\n by another character");
    }
  }

  private void setUpEquipLeftButton() {
    Button button = setUpGenericButton("<", 1, 13);
    button.setOnAction(FinalReality::equipLeftAction);
    equipButtons.put("left", button);
  }

  /**
   * Changes the weapon which the gui is showing, the next showed character will be the previous in
   *  the inner list of possible targets {@code opponents}.
   */
  public static void equipLeftAction(ActionEvent actionEvent) {
    inventoryIndex = (inventory.size() + inventoryIndex - 1) % inventory.size();
    weapon = inventory.get(inventoryIndex);
    updateWeaponAttributes();
    showEquipMessage("");
  }

  private void setUpEquipRightButton() {
    Button button = setUpGenericButton(">", 20, 13);
    button.setOnAction(FinalReality::equipRightAction);
    equipButtons.put("right", button);
  }

  /**
   * Changes the weapon which the gui is showing, the next showed character will be the next in the
   *  inner list of possible targets {@code opponents}.
   */
  public static void equipRightAction(ActionEvent actionEvent) {
    inventoryIndex = (inventoryIndex + 1) % inventory.size();
    weapon = inventory.get(inventoryIndex);
    updateWeaponAttributes();
    showEquipMessage("");
  }

  private void setUpAttackButton() {
    Button button = setUpGenericButton("Attack", 5, 29);
    button.setOnAction(FinalReality::attackAction);
    attackButtons.put("central", button);
  }

  /**
   * Tries to attack the showed character (showed in the gui).
   * The current turn owner tries to attack the showed character.
   */
  public static void attackAction(ActionEvent actionEvent) {
    try {
      controller.characterAttacksTo(target);
      updateTargetAttributes();
      cleanExceptionMessage();
      disableButtons();
    } catch (NonEquippedWeapon e) {
      showAttackMessage("You cannot attack without a\n weapon");
    }
  }

  private void setUpAttackLeftButton() {
    Button button = setUpGenericButton("<", 1, 23);
    button.setOnAction(FinalReality::attackLeftAction);
    attackButtons.put("left", button);
  }

  /**
   * Changes the target which the gui is showing, the next showed character will be the previous in
   *  the inner list of possible targets {@code opponents}.
   */
  public static void attackLeftAction(ActionEvent actionEvent) {
    opponentIndex = (opponents.size() + opponentIndex - 1) % opponents.size();
    target = opponents.get(opponentIndex);
    updateTargetAttributes();
    showAttackMessage("");
  }

  private void setUpAttackRightButton() {
    Button button = setUpGenericButton(">", 20, 23);
    button.setOnAction(FinalReality::attackRightAction);
    attackButtons.put("right", button);
  }

  /**
   * Changes the target which the gui is showing, the next showed character will be the next in the
   *  inner list of possible targets {@code opponents}.
   */
  public static void attackRightAction(ActionEvent actionEvent) {
    opponentIndex = (opponentIndex + 1) % opponents.size();
    target = opponents.get(opponentIndex);
    updateTargetAttributes();
    showAttackMessage("");
  }

  private void setUpStartButton() {
    startButton = setUpGenericButton("START", 4, 32);
    startButton.setOnAction(FinalReality::startButtonAction);
  }

  /**
   * This method is invoked when the start button is pressed.
   * At the start of the game, the method initialize a new turn, updating all the parameters and
   *  showing all the important information for the player {@link #startANewTurn()}.
   *
   * Apart of those cases, this button changes its text to let the state of the game after finishing
   *  a turn, that means that after a turn, if the game has ended, then the button will show if the
   *  player wins or loses, by showing some text in the button.
   *
   * After ending the game, and this button said "You Win!!!" or "Game Over", if you press the
   *  button again the gui will close.
   */
  public static void startButtonAction(ActionEvent actionEvent) {
    if (startButton.getText().equals("START")) {
      startButton.setText("Next Turn");
    }
    if (controller.gameOver()) {
      if (startButton.getText().equals("Next Turn")) {
        if (controller.thePlayerWins()) {
          startButton.setText("You Win!!!");
        } else {
          startButton.setText("Game Over");
        }
      } else {
        System.exit(0);
      }
      return;
    }
    startANewTurn();
  }

}
