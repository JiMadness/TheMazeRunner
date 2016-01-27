package Menus;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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

        btnScore = new MenuButton("Your Score: "+Integer.toString(Player.getInstance().getScore()));

        MenuButton btnResume = new MenuButton("Play Again");
        btnResume.setOnMouseClicked(event -> {

        });



        MenuButton btnBack = new MenuButton("BACK TO MAIN MENU");
        btnBack.setOnMouseClicked(event -> {
            main.Main.getInstance().getPrimaryStage().hide();
            main.Main.getInstance().getWinningStage().hide();
            main.Main.getInstance().getWelcomeStage().show();
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
