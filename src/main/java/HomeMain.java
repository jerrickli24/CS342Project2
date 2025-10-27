import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

//Neha Kamat, nkama4, UIN: 665218217
//Welcome screen w/ menu and start button
//This is the first screen the player sees when they launch the game

public class HomeMain {
    private SceneSwitch sceneSwitch;
    private DisplayOdds displayOdds;
    
    public HomeMain(SceneSwitch sceneSwitch) {
        this.sceneSwitch = sceneSwitch;
        this.displayOdds = new DisplayOdds();
    }
    
    //Create the welcome scene w/ title and start button
    public Scene createScene() {
        //Main container - dark blue background
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1a1a4d;");
        
        //add menu bar at top
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);
        
        //center content w/ title and button
        VBox centerContent = new VBox(30);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setPadding(new Insets(50));
        
        //main title text
        Text titleText = new Text("WELCOME TO KENO");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 48));
        titleText.setFill(Color.WHITE);
        
        //subtitle text
        Text subtitleText = new Text("TEST YOUR LUCK!");
        subtitleText.setFont(Font.font("Arial", FontWeight.NORMAL, 28));
        subtitleText.setFill(Color.WHITE);
        
        //start button w/ pink background
        Button startButton = new Button("START PLAYING >");
        startButton.setStyle("-fx-background-color: #ffb3d9; -fx-text-fill: black; " +
                           "-fx-font-size: 18px; -fx-font-weight: bold; " +
                           "-fx-padding: 15 40 15 40; -fx-background-radius: 20;");
        startButton.setOnAction(e -> onStartGame());
        
        //adds all elements to center
        centerContent.getChildren().addAll(titleText, subtitleText, startButton);
        root.setCenter(centerContent);
        
        Scene scene = new Scene(root, 1100, 700);
        return scene;
    }
    
    //create the menu bar w/ HOME dropdown
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #ffb3d9;");
        
        //HOME menu w/ dropdown arrow
        Menu homeMenu = new Menu("HOME ▼");
        homeMenu.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");
        
        //Display Rules option
        MenuItem displayRulesItem = new MenuItem("Display Rules");
        displayRulesItem.setOnAction(e -> onShowRules());
        
        //Display Odds option
        MenuItem displayOddsItem = new MenuItem("Display Odds");
        displayOddsItem.setOnAction(e -> onShowOdds());
        
        //Exit Game option
        MenuItem exitGameItem = new MenuItem("Exit Game");
        exitGameItem.setOnAction(e -> onExit());
        
        homeMenu.getItems().addAll(displayRulesItem, displayOddsItem, exitGameItem);
        menuBar.getMenus().add(homeMenu);
        
        return menuBar;
    }
    
    //show rules dialog when clicked on Display Rules
    public void onShowRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Keno Game Rules");
        alert.setHeaderText("How to Play Keno");
        
        // Rules text explaining how to play
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
        alert.getDialogPane().setMinWidth(500);
        alert.showAndWait();
    }
    
    //show odds table when user clicks Display Odds
    public void onShowOdds() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Keno Winnings");
        alert.setHeaderText("North Carolina Keno Odds");
        
        //get formatted odds table from DisplayOdds
        String oddsText = displayOdds.getOddsTable();
        
        //use TextArea so user can scroll if needed
        TextArea textArea = new TextArea(oddsText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        
        alert.getDialogPane().setContent(textArea);
        alert.getDialogPane().setMinWidth(450);
        alert.getDialogPane().setMinHeight(500);
        alert.showAndWait();
    }
    
    //exit game w/ confirma dialog
    public void onExit() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Exit Game");
        confirmAlert.setHeaderText("Are you sure you want to exit?");
        confirmAlert.setContentText("All progress will be lost.");
        
        // Wait for user response
        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.exit();
            }
        });
    }
    
    //switch to game screen when player clicks start
    public void onStartGame() {
        sceneSwitch.showGameMain();
    }
}