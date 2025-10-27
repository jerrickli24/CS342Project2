import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main entry point for the Keno game application
 */
public class KenoMain extends Application {
    private Stage mainStage;
    private SceneSwitch sceneSwitch;
    
    @Override
    public void start(Stage stage) {
        mainStage = stage;
        sceneSwitch = new SceneSwitch(mainStage);
        
        // TODO: Start with welcome screen
        // sceneSwitch.showHomeMain();
        
        mainStage.setTitle("Keno Game");
        mainStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}