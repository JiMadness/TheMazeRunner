package main;

import controller.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {
    private static Main instance;
    private Stage primaryStage;
    private Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        instance=this;
        this.primaryStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/game.fxml"));
        mainScene = new Scene(root,900,600);
        primaryStage.setTitle("The Maze Runner");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        Game.getInstance().initControls();
    }

    public Stage getPrimaryStage(){return primaryStage;}
    public static Main getInstance(){return instance;}
    public Scene getMainScene(){return mainScene;}
    public static void main(String[] args) {
        launch(args);
    }
}
