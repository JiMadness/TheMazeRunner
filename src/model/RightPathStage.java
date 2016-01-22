package model;

import controller.Game;
import javafx.scene.image.Image;

public class RightPathStage implements GameStage{
    private int ID=0;
    private Path parentCameFrom;
    public void start(){
        Maze.setMinX(0);
        Maze.setMinY(502);
        Maze.setMaxX(450);
        Maze.setMaxY(108);
        Game.getInstance().getFrame().getGraphicsContext2D().drawImage(new Image("design/RightPath.jpg"), 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
        Maze.getCurrentNode().initialize();
    }
    public RightPathStage(Path parentCameFrom){
        this.parentCameFrom = parentCameFrom;
    }

    public void stop() {
        Game.getInstance().getFrame().getGraphicsContext2D().clearRect(0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
    }
    public int getID() {
        return ID;
    }

    @Override
    public StageType getStageType() {
        return StageType.RIGHTPATH;
    }

    public void setID(int ID) {
        this.ID=ID;
    }
    public Path getParentCameFrom(){return  parentCameFrom;}
}
