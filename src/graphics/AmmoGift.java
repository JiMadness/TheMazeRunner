package graphics;


import controller.Game;
import javafx.scene.image.Image;
import model.Decorations;
import model.Maze;

public class AmmoGift extends Gifts {


    public AmmoGift() {
        super();
        this.type=Type.Ammo;
        this.Icon=new Image("AGift.png");
        GiftsCanvas.setVisible(false);
        Game.getInstance().getLayers().getChildren().add(GiftsCanvas);
    }

    @Override
    public void Show() {
        if(!isTaken()){
        this.Value=(int) Math.floor(Math.random()*10);
        this.PosX= Maze.getXPath();
        ApperantY=Maze.getYPath()+150;
        this.PosY=Maze.getYPath();
        this.setHidden(false);
        GiftsCanvas.setVisible(true);
        GiftsCanvas.getGraphicsContext2D().drawImage(Icon,PosX,PosY);
        GiftsCanvas.setVisible(true);
    }}

    public void AddBonus(){
        Pool.ReleaseAmmos(Value);
        Decorations.getInstance().updateDecorations();
    }
}
