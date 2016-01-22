package model;

import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.media.AudioClip;

import java.io.File;

public class GameLoop extends AnimationTimer {
    private static GameLoop instance = new GameLoop();
    private AudioClip gameTrack = new AudioClip(new File("resources/sounds/gameTrack.mp3").toURI().toString());

    public static GameLoop getInstance() {
        return instance;
    }

    private GameLoop() {
    }

    private void handleTransitions(){
        if (Player.getInstance().getPosX() <= 0 && Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()) != null) {
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()));
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMaxX() - 1);
        } else if (Player.getInstance().getPosX() >= 832 && Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()) != null) {
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()));
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMinX() + 1);
        } else if (Player.getInstance().getPosY() >= 502 && Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()) != null) {
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()));
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosY(Maze.getMaxY() + 1);
        } else if (Player.getInstance().getPosY() <= 108 && Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()) != null) {
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()));
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosY(Maze.getMinY() - 1);
        }
    }

    @Override
    public void handle(long now) {
        Game.getInstance().makePlayerMovement();
        if (!gameTrack.isPlaying())
            gameTrack.play();
        Decorations.getInstance().updateDecorations();
        handleTransitions();
    }
}
