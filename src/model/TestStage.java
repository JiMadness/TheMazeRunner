package model;

import controller.Game;
import javafx.scene.image.Image;

public class TestStage implements GameStage {
    private static TestStage instance = new TestStage();
    public static TestStage getInstance() {
        return instance;
    }
    private TestStage() {
    }
    public void start(){
       Game.getInstance().getFrame().getGraphicsContext2D().drawImage(new Image("backgrounds/background.jpg"), 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
    }

    public void stop() {

        Game.getInstance().getFrame().getGraphicsContext2D().clearRect(0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
    }
}
