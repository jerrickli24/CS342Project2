import javafx.stage.Stage;

/**
 * Manages transitions between different scenes
 */
public class SceneSwitch {
    private Stage mainStage;
    private HomeMain homeMain;
    private GameMain gameMain;
    
    public SceneSwitch(Stage mainStage) {
        this.mainStage = mainStage;
    }
    
    /**
     * Show the home/welcome screen
     */
    public void showHomeMain() {
        // TODO: Implement
    }
    
    /**
     * Show the game play screen
     */
    public void showGameMain() {
        // TODO: Implement
    }
    
    public Stage getPrimaryStage() {
        return mainStage;
    }
}