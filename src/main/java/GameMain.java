import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Main game play screen
 */
public class GameMain {
    private SceneSwitch sceneSwitch;
    private BetCard betCard;
    private GamePlay game;
    private Player player;
    private int electedDrawings;
    
    public GameMain(SceneSwitch sceneSwitch) {
        this.sceneSwitch = sceneSwitch;
        // TODO: Initialize other components
    }
    
    /**
     * Create the game scene
     */
    public Scene createScene() {
        // TODO: Create BorderPane with GridPane (8x10) and controls
        return null;
    }
    
    public void setSpots(int sc) {
        // TODO: Create BetCard with spot count
    }
    
    public void setDrawings(int n) {
        // TODO: Set number of drawings
    }
    
    public void onChosen(int n) {
        // TODO: Handle number selection on bet card
    }
    
    public void autoPick() {
        // TODO: Auto-select numbers
    }
    
    public void startDraw() {
        // TODO: Start the drawing animation
    }
    
    public void onContinue() {
        // TODO: Continue to next drawing
    }
    
    public void onNewLook() {
        // TODO: Change theme/colors
    }
    
    public void resetForNewGame() {
        // TODO: Reset for new game
    }
    
    public boolean isReadyToDraw() {
        // TODO: Check if bet card is complete
        return false;
    }
}