import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Welcome screen with menu and start button
 */
public class HomeMain {
    private SceneSwitch sceneSwitch;
    
    public HomeMain(SceneSwitch sceneSwitch) {
        this.sceneSwitch = sceneSwitch;
    }
    
    /**
     * Create the welcome scene
     */
    public Scene createScene() {
        // TODO: Create BorderPane with menu bar and start button
        return null;
    }
    
    public void onShowRules() {
        // TODO: Show rules dialog
    }
    
    public void onShowOdds() {
        // TODO: Show odds dialog
    }
    
    public void onExit() {
        // TODO: Exit with confirmation
    }
    
    public void onStartGame() {
        // TODO: Switch to game scene
    }
}