package main;

import Monsters.Monster;
import Monsters.MonsterType;
import controller.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
    private static Main instance;
    private Stage primaryStage;
    public static ArrayList<Monster> monsters;
    private Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        instance=this;
        this.primaryStage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../view/game.fxml"));
        mainScene = new Scene(root,900,600);
        monsters=new ArrayList<>();
        monsters.add(new Monster(MonsterType.fireFox));
        monsters.add(new Monster(MonsterType.chrome));
        monsters.add(new Monster(MonsterType.IE));
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
