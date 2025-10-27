import javafx.stage.Stage;

//Neha Kamat, nkama4, UIN: 665218217
//Manages transitions between different scenes
public class SceneSwitch {
   private Stage mainStage;
   private HomeMain homeMain;
   private GameMain gameMain;

   public SceneSwitch(Stage mainStage) {
      this.mainStage = mainStage;
   }
   
   //show home/welcome screen
   public void showHomeMain() {
      if (this.homeMain == null) {
         this.homeMain = new HomeMain(this);
      }

      this.mainStage.setScene(this.homeMain.createScene());
   }

    //show game play screen
   public void showGameMain() {
      if (this.gameMain == null) {
         this.gameMain = new GameMain(this);
      } else {
         this.gameMain.resetForNewGame();
      }

      this.mainStage.setScene(this.gameMain.createScene());
   }

   //getter for primary stage
   public Stage getPrimaryStage() {
      return this.mainStage;
   }
}
