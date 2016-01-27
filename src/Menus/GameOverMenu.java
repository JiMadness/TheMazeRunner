package Menus;

import controller.Game;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Main;
import model.Player;

/**
 * Created by ehab on 1/27/2016.
 */
public class GameOverMenu extends StackPane {
    private static GameOverMenu instance = new GameOverMenu();
    public static GameOverMenu getInstance() {
        return instance;
    }

    private GameOverMenu(){
        VBox menu0 = new VBox(10);

        menu0.setTranslateX(325);
        menu0.setTranslateY(400);


        MenuButton btnResume = new MenuButton("Play Again");
        btnResume.setOnMouseClicked(event -> {

        });



        MenuButton btnBack = new MenuButton("BACK TO MAIN MENU");
        btnBack.setOnMouseClicked(event -> {
            Game.getInstance().getLayers().getChildren().remove(getInstance());
            Game.getInstance().getLayers().getChildren().remove(Game.getInstance().gameOverImageView);
            Player.getInstance().setGameOver(false);
            main.Main.getInstance().getPrimaryStage().hide();
            main.Main.getInstance().getWelcomeStage().show();
        });

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });



        menu0.getChildren().addAll(btnResume, btnBack, btnExit);
        Rectangle bg = new Rectangle(900, 600);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.4);

        getChildren().addAll(bg, menu0);

    }
}
