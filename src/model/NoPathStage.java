package model;

import controller.Game;
import javafx.scene.image.Image;

public class NoPathStage implements GameStage {
    private int ID=0;
    private Path parentCameFrom;
    private static Image stageImage = new Image("design/nopath1.jpg");
    public void start(){
        Maze.setMinX(Maze.getXPath()-Maze.getDelta());
        Maze.setMinY(502);
        Maze.setMaxX(Maze.getXPath()+Maze.getDelta());
        Maze.setMaxY(265);
        Game.getInstance().getFrame().getGraphicsContext2D().drawImage(stageImage, 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
        Maze.getCurrentNode().initialize();
        Maze.getCurrentNode().GiftsIndex=8;
    }
    public NoPathStage(Path parentCameFrom){
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
        return StageType.NoPath;
    }

    public void setID(int ID) {
        this.ID=ID;
    }
    public Path getParentCameFrom(){return parentCameFrom;}
}
