package model;

import controller.Game;
import javafx.scene.image.Image;

public class RightPathStage implements GameStage{
    private int ID=0;
    public void start(){
        Maze.setMinX(0);
        Maze.setMinY(502);
        Maze.setMaxX(450);
        Maze.setMaxY(108);
        Game.getInstance().getFrame().getGraphicsContext2D().drawImage(new Image("design/RightPath.jpg"), 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
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
