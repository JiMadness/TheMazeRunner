package controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public Maze testMaze1 = new Maze(new ThreePathsStage(Path.NULL));
    public Maze testMaze = new Maze(new ThreePathsStage(Path.NULL));
    public Image gameOverImage = new Image("/backgrounds/Gameover.jpg");
    public ImageView gameOverImageView = new ImageView(gameOverImage);
    @FXML
    private void initialize(){
        gameOverImageView.setFitWidth(900);
        gameOverImageView.setFitHeight(600);
        instance= this;
        GameLoop.getInstance().start();
        testMaze.getMazeTree().getRoot().getData().start();
        Decorations.getInstance().updateDecorations();

        testMaze.getMazeTree().getRoot().addChild(new RightPathStage(Path.RIGHT),Path.RIGHT.ordinal(),1,false);
        testMaze.getMazeTree().getRoot().addChild(new LeftPathStage(Path.LEFT),Path.LEFT.ordinal(),2,false);
        testMaze.getMazeTree().getRoot().addChild(new NoPathStage(Path.UP),Path.UP.ordinal(),3,false);
        testMaze.getMazeTree().getRoot().addChild(new LeftPathStage(Path.DOWN),Path.DOWN.ordinal(),1,true);
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
        if(Player.getInstance().isLookingUp())
            Player.getInstance().getUpSprite().stop();
        else if(Player.getInstance().isLookingDown())
            Player.getInstance().getDownSprite().stop();
        else if(Player.getInstance().isLookingLeft())
            Player.getInstance().getLeftSprite().stop();
        else if(Player.getInstance().isLookingRight())
            Player.getInstance().getRightSprite().stop();
    }

    public static Game getInstance(){return instance;}
    public Canvas getFrame(){return frame;}
    public StackPane getLayers(){return layers;}

}
