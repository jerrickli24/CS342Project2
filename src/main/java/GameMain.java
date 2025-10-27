import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

//Jerrick Li, jli326, UIN: 653147894
//Neha Kamat, nkama4, UIN: 665218217

//main game screen: UI + round flow
public class GameMain {
    //core game refs
    private BetCard betCard;
    private GamePlay gamePlay;
    private Player player;
    private DisplayOdds displayOdds;
    private Winnings winnings;

    //simple state flags/counters
    private int selectedSpots;
    private int selectedDrawings;
    private boolean isDrawingInProgress;
    private boolean hasStartedDrawing;

    //UI nodes
    private BorderPane root;
    private GridPane betCardGrid;
    private Button[] numberButtons;
    private Label spotsLabel;
    private Label drawingsLabel;
    private Button quickPickButton;
    private Button startDrawButton;
    private ComboBox<Integer> spotsCombo;
    private ComboBox<Integer> drawingsCombo;
    private Button continueButton;
    private Button playAgainButton;
    private TextArea drawingResultsArea;
    private Label matchedLabel;
    private Label thisDrawLabel;
    private Label totalLabel;
    private boolean isAlternateTheme;

    public GameMain(SceneSwitch sceneSwitch) {
        this.displayOdds = new DisplayOdds();
        this.winnings = new Winnings();
        this.player = new Player();
        this.selectedSpots = 0;
        this.selectedDrawings = 0;
        this.isDrawingInProgress = false;
        this.hasStartedDrawing = false;
        this.isAlternateTheme = false;
    }

    //build scene
    public Scene createScene() {
        this.root = new BorderPane();
        this.applyTheme();

        MenuBar menuBar = this.createMenuBar();
        this.root.setTop(menuBar);

        HBox mainContent = new HBox(20.0);
        mainContent.setPadding(new Insets(20.0));
        mainContent.setAlignment(Pos.TOP_CENTER);

        VBox leftPanel = this.createLeftPanel();   // inputs + bet card
        VBox rightPanel = this.createRightPanel(); // results + controls
        mainContent.getChildren().addAll(leftPanel, rightPanel);

        this.root.setCenter(mainContent);

        return new Scene(this.root, 1100.0, 700.0);
    }

    //theme color
    private void applyTheme() {
        if (this.isAlternateTheme) {
            this.root.setStyle("-fx-background-color: #2a1a4d;");
        } else {
            this.root.setStyle("-fx-background-color: #1a1a4d;");
        }
    }

    //top menu
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #ffb3d9;");

        Menu homeMenu = new Menu("HOME ▼");
        homeMenu.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

        MenuItem displayRulesItem = new MenuItem("Display Rules");
        displayRulesItem.setOnAction(e -> showRules());

        MenuItem displayOddsItem = new MenuItem("Display Odds");
        displayOddsItem.setOnAction(e -> showOdds());

        MenuItem exitGameItem = new MenuItem("Exit Game");
        exitGameItem.setOnAction(e -> exitGame());

        homeMenu.getItems().addAll(displayRulesItem, displayOddsItem, exitGameItem);
        menuBar.getMenus().add(homeMenu);
        return menuBar;
    }

    //left: pick spots/draws and numbers
    private VBox createLeftPanel() {
        VBox leftPanel = new VBox(15.0);
        leftPanel.setPadding(new Insets(10.0));
        leftPanel.setAlignment(Pos.TOP_CENTER);

        Text headerText = new Text("Select Spots & Drawings");
        headerText.setFont(Font.font("Arial", FontWeight.BOLD, 24.0));
        headerText.setFill(Color.WHITE);

        HBox controlsBox = new HBox(15.0);
        controlsBox.setAlignment(Pos.CENTER);

        //spots count
        this.spotsCombo = new ComboBox<>();
        this.spotsCombo.getItems().addAll(1, 4, 8, 10);
        this.spotsCombo.setPromptText("Spots");
        this.spotsCombo.setOnAction(e -> setSpots(this.spotsCombo.getValue()));
        this.spotsCombo.setStyle("-fx-background-color: #b3ffb3; -fx-font-size: 14px;");

        //drawings count
        this.drawingsCombo = new ComboBox<>();
        this.drawingsCombo.getItems().addAll(1, 2, 3, 4);
        this.drawingsCombo.setPromptText("Draws");
        this.drawingsCombo.setOnAction(e -> setDrawings(this.drawingsCombo.getValue()));
        this.drawingsCombo.setStyle("-fx-background-color: #b3ffb3; -fx-font-size: 14px;");

        //random pick
        this.quickPickButton = new Button("Quick Pick");
        this.quickPickButton.setStyle(
                "-fx-background-color: #b3ffb3; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 8 20 8 20; -fx-background-radius: 15;");
        this.quickPickButton.setOnAction(e -> autoPick());
        this.quickPickButton.setDisable(true);

        controlsBox.getChildren().addAll(this.spotsCombo, this.drawingsCombo, this.quickPickButton);

        //summary labels
        this.spotsLabel = new Label("Spots: --");
        this.spotsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16.0));
        this.spotsLabel.setTextFill(Color.web("#b3ffb3"));

        this.drawingsLabel = new Label("Draws: --");
        this.drawingsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16.0));
        this.drawingsLabel.setTextFill(Color.web("#b3ffb3"));

        HBox labelsBox = new HBox(20.0);
        labelsBox.setAlignment(Pos.CENTER);
        labelsBox.getChildren().addAll(this.spotsLabel, this.drawingsLabel);

        Text betCardTitle = new Text("Bet Card");
        betCardTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20.0));
        betCardTitle.setFill(Color.WHITE);

        this.betCardGrid = this.createBetCardGrid();

        //start button (enabled when ready)
        this.startDrawButton = new Button("Start Drawing");
        this.startDrawButton.setStyle(
                "-fx-background-color: #ffb3d9; -fx-text-fill: black; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 30 10 30; -fx-background-radius: 15;");
        this.startDrawButton.setDisable(true);
        this.startDrawButton.setOnAction(e -> startDraw());

        leftPanel.getChildren().addAll(headerText, controlsBox, labelsBox, betCardTitle, this.betCardGrid,
                this.startDrawButton);
        return leftPanel;
    }

    //80 buttons grid
    private GridPane createBetCardGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(5.0);
        grid.setVgap(5.0);
        grid.setAlignment(Pos.CENTER);
        grid.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-padding: 10;");

        this.numberButtons = new Button[80];

        for (int i = 0; i < 80; ++i) {
            final int number = i + 1;
            Button btn = new Button(String.valueOf(number));
            btn.setMinSize(50.0, 35.0);
            btn.setMaxSize(50.0, 35.0);
            btn.setStyle(
                    "-fx-background-color: #1a1a6d; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
            btn.setDisable(true);
            btn.setOnAction(e -> onNumberClicked(number));
            this.numberButtons[i] = btn;

            int row = i / 8;
            int col = i % 8;
            grid.add(btn, col, row);
        }

        return grid;
    }

    //right: results and next actions
    private VBox createRightPanel() {
        VBox rightPanel = new VBox(20.0);
        rightPanel.setPadding(new Insets(10.0));
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.setMinWidth(400.0);

        Text resultsTitle = new Text("Drawing Results");
        resultsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22.0));
        resultsTitle.setFill(Color.WHITE);

        this.drawingResultsArea = new TextArea("Drawn: ");
        this.drawingResultsArea.setEditable(false);
        this.drawingResultsArea.setPrefHeight(150.0);
        this.drawingResultsArea.setStyle(
                "-fx-control-inner-background: #1a1a6d; -fx-text-fill: white; -fx-font-size: 14px; -fx-border-color: white; -fx-border-width: 2;");

        Text winningsTitle = new Text("Winnings");
        winningsTitle.setFont(Font.font("Arial", FontWeight.BOLD, 22.0));
        winningsTitle.setFill(Color.web("#ffd700"));

        VBox winningsBox = new VBox(10.0);
        winningsBox.setStyle("-fx-border-color: white; -fx-border-width: 2; -fx-padding: 15;");
        winningsBox.setAlignment(Pos.CENTER_LEFT);

        this.matchedLabel = new Label("Matched: 0");
        this.matchedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18.0));
        this.matchedLabel.setTextFill(Color.WHITE);

        this.thisDrawLabel = new Label("This Draw: $0");
        this.thisDrawLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18.0));
        this.thisDrawLabel.setTextFill(Color.WHITE);

        this.totalLabel = new Label("Total: $0");
        this.totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18.0));
        this.totalLabel.setTextFill(Color.WHITE);

        winningsBox.getChildren().addAll(this.matchedLabel, this.thisDrawLabel, this.totalLabel);

        // next draw
        this.continueButton = new Button("Continue to Next Drawing");
        this.continueButton.setStyle(
                "-fx-background-color: #b3ffb3; -fx-text-fill: black; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; -fx-background-radius: 15;");
        this.continueButton.setDisable(true);
        this.continueButton.setOnAction(e -> onContinue());

        // play again (enabled after last draw)
        this.playAgainButton = new Button("Play Again");
        this.playAgainButton.setStyle(
                "-fx-background-color: #ffb3d9; -fx-text-fill: black; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 10 20 10 20; -fx-background-radius: 15;");
        this.playAgainButton.setDisable(true);
        this.playAgainButton.setOnAction(e -> {
            resetForNewGame();
        });

        rightPanel.getChildren().addAll(resultsTitle, this.drawingResultsArea, winningsTitle, winningsBox,
                this.continueButton, this.playAgainButton);

        return rightPanel;
    }

    //set chosen spots
    public void setSpots(int spotCount) {
        if (this.hasStartedDrawing) {
            this.showAlert("Cannot change spots after starting drawings!");
        } else {
            if (spotCount == 1 || spotCount == 4 || spotCount == 8 || spotCount == 10) {
                this.selectedSpots = spotCount;
                this.spotsLabel.setText("Spots: " + spotCount);
                this.betCard = new BetCard(spotCount);

                // enable all numbers to click
                for (Button btn : this.numberButtons) {
                    btn.setDisable(false);
                    btn.setStyle(
                            "-fx-background-color: #1a1a6d; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
                }

                this.quickPickButton.setDisable(false);
                this.updateStartButton();
            }
        }
    }

    //set number of drawings
    public void setDrawings(int numDrawings) {
        if (this.hasStartedDrawing) {
            this.showAlert("Cannot change drawings after starting!");
        } else {
            if (numDrawings >= 1 && numDrawings <= 4) {
                this.selectedDrawings = numDrawings;
                this.drawingsLabel.setText("Draws: " + numDrawings);
                this.updateStartButton();
            }
        }
    }

    //toggle number on bet card
    private void onNumberClicked(int number) {
        if (!this.hasStartedDrawing && this.betCard != null) {
            if (this.betCard.getSelected() != null && this.betCard.getSelected().contains(number)) {
                this.betCard.unselect(number);
                this.numberButtons[number - 1].setStyle(
                        "-fx-background-color: #1a1a6d; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
            } else if (this.betCard.select(number)) {
                this.numberButtons[number - 1].setStyle(
                        "-fx-background-color: #ffd700; -fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
            } else {
                this.showAlert("Cannot select more than " + this.selectedSpots + " numbers!");
            }
            this.updateStartButton();
        }
    }

    //auto-pick exactly spot count
    public void autoPick() {
        if (this.betCard != null && !this.hasStartedDrawing) {
            this.betCard.clear();

            for (Button btn : this.numberButtons) {
                btn.setStyle(
                        "-fx-background-color: #1a1a6d; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
            }

            Random rng = new Random();
            this.betCard.autoPicking(rng); // uses BetCard to fill

            Set<Integer> selected = this.betCard.getSelected();
            for (Integer num : selected) {
                this.numberButtons[num - 1].setStyle(
                        "-fx-background-color: #ffd700; -fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
            }

            this.updateStartButton();
        }
    }

    //gate for enabling start
    private void updateStartButton() {
        this.startDrawButton.setDisable(!isReadyToDraw());
    }

    //ready when card filled + draws picked + not animating
    public boolean isReadyToDraw() {
        return this.betCard != null && this.betCard.complete() && this.selectedDrawings > 0
                && !this.isDrawingInProgress;
    }

    //begin first draw
    public void startDraw() {
        if (this.isReadyToDraw()) {
            this.hasStartedDrawing = true;
            this.isDrawingInProgress = true;
            this.startDrawButton.setDisable(true);
            this.quickPickButton.setDisable(true);
            this.spotsCombo.setDisable(true);
            this.drawingsCombo.setDisable(true);

            for (Button btn : this.numberButtons) {
                btn.setDisable(true);
            }

            if (this.gamePlay == null) {
                this.gamePlay = new GamePlay(this.displayOdds, this.winnings, this.selectedDrawings);
            }

            this.performDrawing();
        }
    }

    //do one draw with simple animation
    private void performDrawing() {
        RoundResult result = this.gamePlay.nextDraw(this.betCard);
        Set<Integer> drawnNumbers = result.getNumbersDrawn();
        List<Integer> drawnList = new ArrayList<>(drawnNumbers);
        Collections.sort(drawnList);

        this.drawingResultsArea.clear();
        this.drawingResultsArea.appendText("Drawn: ");

        SequentialTransition animation = new SequentialTransition();

        for (int i = 0; i < drawnList.size(); ++i) {
            final int n = drawnList.get(i); // capture for lambda
            PauseTransition pause = new PauseTransition(Duration.millis(150.0));
            pause.setOnFinished(e -> {
                this.drawingResultsArea.appendText("[" + n + "] ");
                if (this.betCard.getSelected().contains(n)) {
                    this.numberButtons[n - 1].setStyle(
                            "-fx-background-color: #00ff00; -fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 2;");
                }
            });
            animation.getChildren().add(pause);
        }

        //end-of-draw updates
        animation.setOnFinished(e -> {
            this.displayResults(result);
            this.isDrawingInProgress = false;

            if (this.gamePlay.hasMoreDraws()) {
                this.continueButton.setDisable(false); // next round
            } else {
                this.playAgainButton.setDisable(false); // allow reset
            }
        });

        animation.play();
    }

    //update labels + total
    private void displayResults(RoundResult result) {
        this.player.addResult(result);
        this.matchedLabel.setText("Matched: " + result.getMatchCounter());
        this.thisDrawLabel.setText("This Draw: $" + result.getRoundWinnings());
        this.totalLabel.setText("Total: $" + this.player.getTotalWinnings());
    }

    //go to next drawing
    public void onContinue() {
        this.continueButton.setDisable(true);

        // restore bet card colors for next draw
        for (Button btn : this.numberButtons) {
            int num = Integer.parseInt(btn.getText());
            if (this.betCard.getSelected().contains(num)) {
                btn.setStyle(
                        "-fx-background-color: #ffd700; -fx-text-fill: black; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
            } else {
                btn.setStyle(
                        "-fx-background-color: #1a1a6d; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
            }
        }

        this.isDrawingInProgress = true;
        this.performDrawing();
    }


    //reset everything to start fresh
    public void resetForNewGame() {
        this.betCard = null;
        this.gamePlay = null;
        this.player = new Player();
        this.selectedSpots = 0;
        this.selectedDrawings = 0;
        this.isDrawingInProgress = false;
        this.hasStartedDrawing = false;

        this.spotsLabel.setText("Spots: --");
        this.drawingsLabel.setText("Draws: --");
        this.matchedLabel.setText("Matched: 0");
        this.thisDrawLabel.setText("This Draw: $0");
        this.totalLabel.setText("Total: $0");

        this.drawingResultsArea.setText("Drawn: ");

        this.spotsCombo.setValue(null);
        this.drawingsCombo.setValue(null);
        this.spotsCombo.setDisable(false);
        this.drawingsCombo.setDisable(false);

        this.quickPickButton.setDisable(true);
        this.startDrawButton.setDisable(true);
        this.continueButton.setDisable(true);
        this.playAgainButton.setDisable(true);

        //lock number buttons until user sets spots again
        for (Button btn : this.numberButtons) {
            btn.setDisable(true);
            btn.setStyle(
                    "-fx-background-color: #1a1a6d; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 1;");
        }
    }

    //rules popup
    private void showRules() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Keno Game Rules");
        alert.setHeaderText("How to Play Keno");

        String rules = "KENO RULES:\n\n" +
                "1. Choose how many spots to play (1, 4, 8, or 10 numbers)\n\n" +
                "2. Select your numbers from 1 to 80 on the bet card\n" +
                "   OR use Quick Pick to auto-select random numbers\n\n" +
                "3. Choose how many drawings to play (1 to 4)\n\n" +
                "4. Twenty numbers will be randomly drawn\n\n" +
                "5. Match your selected numbers to the drawn numbers to win!\n\n" +
                "6. Winnings depend on how many spots you play and\n" +
                "   how many numbers you match\n\n" +
                "Good Luck!";

        alert.setContentText(rules);
        alert.getDialogPane().setMinWidth(500.0);
        alert.showAndWait();
    }

    //odds popup
    private void showOdds() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Keno Winnings");
        alert.setHeaderText("North Carolina Keno Odds");

        String oddsText = this.displayOdds.getOddsTable(); // odds string from DisplayOdds
        TextArea textArea = new TextArea(oddsText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        alert.getDialogPane().setContent(textArea);
        alert.getDialogPane().setMinWidth(450.0);
        alert.getDialogPane().setMinHeight(500.0);
        alert.showAndWait();
    }

    //exit confirm
    private void exitGame() {
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Exit Game");
        confirmAlert.setHeaderText("Are you sure you want to exit?");
        confirmAlert.setContentText("All progress will be lost.");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }

    //simple warning box
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
