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
        if(!gameTrack.isPlaying())
            gameTrack.play();
    }
}
