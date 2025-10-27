import javafx.application.Application;
import javafx.stage.Stage;

//Neha Kamat, nkama4, UIN:665218217
//main entry point for Keno Game
public class KenoMain extends Application {
   private Stage mainStage;
   private SceneSwitch sceneSwitch;

   public KenoMain() {
   }

   public void start(Stage stage) {
      this.mainStage = stage;
      this.sceneSwitch = new SceneSwitch(this.mainStage);
      this.sceneSwitch.showHomeMain();
      this.mainStage.setTitle("Keno Game");
      this.mainStage.setWidth(1100.0);
      this.mainStage.setHeight(700.0);
      this.mainStage.show();
   }

   public static void main(String[] args) {
      launch(args);
   }
}