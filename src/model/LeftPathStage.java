package model;

import controller.Game;
import javafx.scene.image.Image;

public class LeftPathStage implements GameStage{
    private int ID=0;
    private Path parentCameFrom;
    private static Image stageImage = new Image("design/leftp.jpg");
    public void start(){
        Maze.setMinX(400);
        Maze.setMinY(502);
        Maze.setMaxX(832);
        Maze.setMaxY(108);
        Game.getInstance().getFrame().getGraphicsContext2D().drawImage(stageImage, 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
        Maze.getCurrentNode().initialize();
    }
    public LeftPathStage(Path parentCameFrom){
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
        return StageType.LEFTPATH;
    }

    public void setID(int ID) {
        this.ID=ID;
    }
    public Path getParentCameFrom(){return parentCameFrom;}
}
