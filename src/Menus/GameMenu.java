package Menus;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.GameLoop;

/**
 * Created by ehab on 1/27/2016.
 */
public class GameMenu extends Parent{
    private static GameMenu instance = new GameMenu();

    private GameMenu() {
        VBox menu0 = new VBox(10);
        VBox menu1 = new VBox(10);

        menu0.setTranslateX(100);
        menu0.setTranslateY(200);

        menu1.setTranslateX(100);
        menu1.setTranslateY(200);

        final int offset = 400;

        menu1.setTranslateX(offset);

        MenuButton btnStart = new MenuButton("START GAME");
        btnStart.setOnMouseClicked(event -> {
            getChildren().add(menu1);
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
            tt.setToX(menu0.getTranslateX() - offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
            tt1.setToX(menu0.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu0);
            });
        });

        Rectangle bg = new Rectangle(900, 600);
        bg.setFill(Color.GREY);
        bg.setOpacity(0.4);
        getChildren().addAll(bg, menu0);

        MenuButton btnExit = new MenuButton("EXIT");
        btnExit.setOnMouseClicked(event -> {
            System.exit(0);
        });

        menu0.getChildren().addAll(btnStart, btnExit);

        MenuButton btnBack = new MenuButton("BACK");
        btnBack.setOnMouseClicked(event -> {
            getChildren().add(menu0);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
            tt.setToX(menu1.getTranslateX() + offset);

            TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
            tt1.setToX(menu1.getTranslateX());

            tt.play();
            tt1.play();

            tt.setOnFinished(evt -> {
                getChildren().remove(menu1);
            });
        });

        MenuButton btnLvl1 = new MenuButton("LEVEL 1");
        btnLvl1.setOnMouseClicked(event -> {
            GameLoop.getInstance().reviveMonsters();
            main.Main.getInstance().getWelcomeStage().hide();
            main.Main.getInstance().getPrimaryStage().show();
        });
        MenuButton btnLvl2 = new MenuButton("LEVEL 2");
        btnLvl2.setOnMouseClicked(event -> {
            GameLoop.getInstance().reviveMonsters();
            main.Main.getInstance().getWelcomeStage().hide();
            main.Main.getInstance().getPrimaryStage().show();
        });
        MenuButton btnLvl3 = new MenuButton("LEVEL 3");
        btnLvl3.setOnMouseClicked(event -> {
            GameLoop.getInstance().reviveMonsters();
            main.Main.getInstance().getWelcomeStage().hide();
            main.Main.getInstance().getPrimaryStage().show();
        });

        menu1.getChildren().addAll(btnBack, btnLvl1, btnLvl2, btnLvl3);
    }

    public static GameMenu getInstance() {
        return instance;
    }
}
