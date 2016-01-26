package graphics;

import controller.Game;
import javafx.scene.image.Image;
import model.Decorations;
import model.Maze;
import model.Player;

public class ScoreGift extends Gifts {

    public ScoreGift() {
        super();
        this.type=Type.Score;
        this.Icon=new Image("StarsGift.png");
    }
    @Override
    public void AddBonus() {
        Player.getInstance().setScore(Player.getInstance().getScore()+this.Value);
        Decorations.getInstance().updateDecorations();
    }
    public void Show(){
        if(!isTaken()){
       // Random X = new Random();
        //Random Y = new Random();
        this.Value=(int) Math.floor(Math.random()*10);
        int random = (int) Math.floor(Math.random()*10);
        /*int posX=X.nextInt(Maze.getMaxX() - Maze.getMinX() + 1)+ Maze.getMinX() ;
        int posY=Y.nextInt(Maze.getMaxY() - Maze.getMinY() + 1)+Maze.getMinY() ;*/
            this.PosX= Maze.getXPath();
            ApperantY=Maze.getYPath()+150;
            this.PosY=Maze.getYPath();

        this.setHidden(false);
        Game.getInstance().getLayers().getChildren().add(GiftsCanvas);
        GiftsCanvas.getGraphicsContext2D().drawImage(Icon,PosX,PosY);
        GiftsCanvas.setVisible(true);

    }}

}
