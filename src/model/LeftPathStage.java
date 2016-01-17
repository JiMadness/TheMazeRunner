package model;

import controller.Game;
import javafx.scene.image.Image;

public class LeftPathStage implements GameStage{
    private int ID=0;
    public void start(){
        Maze.setMinX(400);
        Maze.setMinY(502);
        Maze.setMaxX(832);
        Maze.setMaxY(108);
        Game.getInstance().getFrame().getGraphicsContext2D().drawImage(new Image("design/LeftPath.jpg"), 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
    }

    public void stop() {
        Game.getInstance().getFrame().getGraphicsContext2D().clearRect(0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID=ID;
    }
}
