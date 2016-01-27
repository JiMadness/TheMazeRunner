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
        GiftsCanvas.setVisible(false);
        Game.getInstance().getLayers().getChildren().add(GiftsCanvas);
    }
    @Override
    public void AddBonus() {
        GiftsCanvas.setVisible(false);
        Player.getInstance().setScore(Player.getInstance().getScore()+this.Value);
       // Game.getInstance().getLayers().getChildren().add(GiftsCanvas);
        setTaken(true);
        Decorations.getInstance().updateDecorations();
    }
    public void Show(){
        {if(!isTaken()){
        this.Value=(int) Math.floor(Math.random()*10);
        this.PosX= Maze.getXPath();
        ApperantY=Maze.getYPath()+150;
        this.PosY=Maze.getYPath();
        this.setHidden(false);
        GiftsCanvas.setVisible(true);
        GiftsCanvas.getGraphicsContext2D().drawImage(Icon,PosX,PosY);
        GiftsCanvas.setVisible(true);
    }}}

}
