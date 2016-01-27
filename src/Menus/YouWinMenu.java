package Menus;

import controller.Game;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.GameLoop;
import model.Maze;
import model.Player;

/**
 * Created by ehab on 1/27/2016.
 */
public class YouWinMenu extends StackPane {
    private static YouWinMenu instance = new YouWinMenu();
    private MenuButton btnScore;
    public static YouWinMenu getInstance() {
        return instance;
    }

    private YouWinMenu() {
        VBox menu0 = new VBox(10);

        menu0.setTranslateX(325);
        menu0.setTranslateY(400);

        btnScore = new MenuButton("Your Score: ");

        MenuButton btnResume = new MenuButton("Play Again");
        btnResume.setOnMouseClicked(event -> {
            Player.getInstance().setPosX(Maze.getXPath());
            Player.getInstance().setPosY(Maze.getYPath());

            Game.getInstance().getLayers().getChildren().remove(getInstance());
            Game.getInstance().getLayers().getChildren().remove(Game.getInstance().gameOverImageView);
            Player.getInstance().setGameOver(false);
            main.Main.getInstance().getWinningStage().hide();
            main.Main.getInstance().getPrimaryStage().show();
            main.Main.getInstance().getWelcomeStage().hide();
            GameLoop.getInstance().resetMaze();
        });



        MenuButton btnBack = new MenuButton("BACK TO MAIN MENU");
        btnBack.setOnMouseClicked(event -> {
            Player.getInstance().setPosX(Maze.getXPath());
            Player.getInstance().setPosY(Maze.getYPath());

            Game.getInstance().getLayers().getChildren().remove(getInstance());
            Game.getInstance().getLayers().getChildren().remove(Game.getInstance().gameOverImageView);
            Player.getInstance().setGameOver(false);
            main.Main.getInstance().getPrimaryStage().hide();
            main.Main.getInstance().getWinningStage().hide();
            main.Main.getInstance().getWelcomeStage().show();
            GameLoop.getInstance().resetMaze();
        });

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });



        menu0.getChildren().addAll(btnScore, btnResume, btnBack, btnExit);
        Rectangle bg = new Rectangle(900, 600);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.4);

        getChildren().addAll(bg, menu0);

    }
    public void resetScoreText(){
        this.btnScore.setText("Your Score: "+Integer.toString(Player.getInstance().getScore()));
    }


}
