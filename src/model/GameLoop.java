package model;

import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.media.AudioClip;
import main.Main;

import java.io.File;

public class GameLoop extends AnimationTimer{
    private static GameLoop instance = new GameLoop();
    private AudioClip gameTrack = new AudioClip(new File("resources/sounds/gameTrack.mp3").toURI().toString());

    public static GameLoop getInstance() {
        return instance;
    }
    private GameLoop() {
    }
    @Override
    public void handle(long now) {
        Game.getInstance().makePlayerMovement();
        Game.getInstance().makeMonsterMovement();
        if(!gameTrack.isPlaying())
            gameTrack.play();
        Decorations.getInstance().updateDecorations();
        if(Player.getInstance().getPosX()>=Maze.getMaxX()){
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(0));
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMinX());
            Player.getInstance().setPosY(Maze.getYPath());
        }
        else if(Player.getInstance().getPosX()<=Maze.getMinX()){
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getParent());
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMaxX());
            Player.getInstance().setPosY(Maze.getYPath());
        }
    }
}
