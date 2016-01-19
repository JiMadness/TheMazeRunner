package model;

import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.media.AudioClip;

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
        if (!gameTrack.isPlaying())
            gameTrack.play();
        Decorations.getInstance().updateDecorations();
        switch (Maze.getCurrentNode().getData().getStageType()){
            case THREEPATHS:
                if(Player.getInstance().getPosX()<=Maze.getMinX()&&Maze.getCurrentNode().getParent()!=null){
                    Maze.getCurrentNode().getData().stop();
                    Maze.setCurrentNode(Maze.getCurrentNode().getParent());
                    Maze.getCurrentNode().getData().start();
                }
                //do same for the other 3 possibilities
                break;
            case LEFTPATH:
                //do same as THREEPATHS
                break;
            case RIGHTPATH:
                //do same as THREEPATHS
                break;
        }
    }
}
