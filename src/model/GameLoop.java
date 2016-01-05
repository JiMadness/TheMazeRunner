package model;

import controller.Game;
import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer{
    private static GameLoop instance = new GameLoop();

    public static GameLoop getInstance() {
        return instance;
    }
    private GameLoop() {
    }
    @Override
    public void handle(long now) {
        Game.getInstance().makePlayerMovement();
    }

}
