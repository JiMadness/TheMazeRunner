package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import main.Main;
import model.*;


public class Game{
    private static Game instance;
    @FXML
    private Canvas frame;
    @FXML
    private StackPane layers;
    private Maze testMaze = new Maze(TestStage.getInstance());
    @FXML
    private void initialize(){
        instance= this;
        GameLoop.getInstance().start();
        Decorations.getInstance().updateDecorations();
        testMaze.getMazeTree().getRoot().getData().start();
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
        });
    }
    public void makePlayerMovement(){
        if(Player.getInstance().isMovingUp())
            Player.getInstance().getUpSprite().stop();
        else if(Player.getInstance().isMovingDown())
            Player.getInstance().getDownSprite().stop();
        else if(Player.getInstance().isMovingLeft())
            Player.getInstance().getLeftSprite().stop();
        else if(Player.getInstance().isMovingRight())
            Player.getInstance().getRightSprite().stop();
    }

    public static Game getInstance(){return instance;}
    public Canvas getFrame(){return frame;}
    public StackPane getLayers(){return layers;}
}
