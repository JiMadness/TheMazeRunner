package model;

import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;

public class WelcomeStage extends AnimationTimer implements GameStage {
    private static WelcomeStage instance = new WelcomeStage();
    private AudioClip startTrack = new AudioClip(new File("resources/sounds/start.mp3").toURI().toString());

    public static WelcomeStage getInstance() {
        return instance;
    }
    private WelcomeStage() {

    }
    @Override
    public void handle(long now) {
        if (!startTrack.isPlaying())
            startTrack.play();
    }
    private void drawGraphics(){
        Game.getInstance().getFrame().getGraphicsContext2D().drawImage(new Image("backgrounds/background.jpg"), 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
    }
    private void playSound(){
        startTrack.play();
    }
    private void stopSound(){
        startTrack.stop();
    }
    public void start(){
        drawGraphics();
        playSound();
        super.start();
    }

    public void stop() {
        stopSound();
        Game.getInstance().getFrame().getGraphicsContext2D().clearRect(0,0,Game.getInstance().getFrame().getWidth(),Game.getInstance().getFrame().getHeight());
        super.stop();
    }
}
