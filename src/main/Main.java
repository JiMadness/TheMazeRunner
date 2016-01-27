package main;

import Menus.GameMenu;
import Menus.YouWinMenu;
import controller.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    private static Main instance;
    private Stage primaryStage;
    private Stage welcomeStage;
    private Stage winningStage;
    private Scene mainScene;
    @Override
    public void start(Stage primaryStage) throws Exception{
        instance=this;
        this.primaryStage=primaryStage;

        initWelcome();
        initWinningStage();

        Parent root = FXMLLoader.load(getClass().getResource("../view/game.fxml"));

        mainScene = new Scene(root,900,600);

        primaryStage.setTitle("The Maze Runner");
        primaryStage.setScene(mainScene);
        primaryStage.hide();
        winningStage.hide();
        getWelcomeStage().show();
        Game.getInstance().initControls();
    }

    public Stage getPrimaryStage(){return primaryStage;}
    public static Main getInstance(){return instance;}
    public Scene getMainScene(){return mainScene;}
    public static void main(String[] args) {
        launch(args);
    }

    private void initWelcome(){
        welcomeStage = new Stage();
        Pane root = new Pane();

        root.setPrefSize(900, 600);

        getWelcomeStage().setTitle("The Maze Runner");
        Image backGround = new Image ("/backgrounds/background.jpg");
        ImageView backGroundView = new ImageView(backGround);
        backGroundView.setFitWidth(900);
        backGroundView.setFitHeight(600);
        Scene scene = new Scene(root);
        root.getChildren().addAll(backGroundView,GameMenu.getInstance());
        getWelcomeStage().setScene(scene);
    }

    public void initWinningStage() {
        this.winningStage = new Stage();
        Pane root = new Pane();

        root.setPrefSize(900, 600);

        getWelcomeStage().setTitle("The Maze Runner");
        Image backGround = new Image ("/backgrounds/You-Win.png");
        ImageView backGroundView = new ImageView(backGround);
        backGroundView.setFitWidth(900);
        backGroundView.setFitHeight(600);
        Scene scene = new Scene(root);
        root.getChildren().addAll(backGroundView, YouWinMenu.getInstance());
        winningStage.setScene(scene);

    }

    public void goToWinningStage(){
        this.primaryStage.hide();
        this.welcomeStage.hide();
        YouWinMenu.getInstance().resetScoreText();
        this.getWinningStage().show();
    }

    public Stage getWelcomeStage() {
        return welcomeStage;
    }

    public Stage getWinningStage() {
        return winningStage;
    }
}
