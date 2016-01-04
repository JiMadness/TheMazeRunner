package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import main.Main;
import model.*;


public class Game {
    private static Game instance;
    @FXML
    private Canvas frame;
    @FXML
    private StackPane layers;

    @FXML
    private void initialize(){
        instance= this;
        WelcomeStage.getInstance().start();
    }
    public void initControls(){
        Main.getInstance().getMainScene().setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.W) || e.getCode().equals(KeyCode.UP))
                Player.getInstance().moveUp();
            if (e.getCode().equals(KeyCode.A) || e.getCode().equals(KeyCode.LEFT))
                Player.getInstance().moveLeft();
            if (e.getCode().equals(KeyCode.S) || e.getCode().equals(KeyCode.DOWN))
                Player.getInstance().moveDown();
            if (e.getCode().equals(KeyCode.D) || e.getCode().equals(KeyCode.RIGHT))
                Player.getInstance().moveRight();
            if (e.getCode().equals(KeyCode.SPACE))
                Player.getInstance().shoot();
            if(e.getCode().equals(KeyCode.BACK_SPACE))
                System.out.println(Player.getInstance().getPosX()+" "+Player.getInstance().getPosY()+" "+frame.getWidth()+" "+frame.getHeight());
        });
    }
    public static Game getInstance(){return instance;}
    public Canvas getFrame(){return frame;}
    public StackPane getLayers(){return layers;}
}