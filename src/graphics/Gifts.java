package graphics;


import controller.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public abstract class Gifts {
    int Value;
    private boolean Hidden=true,Taken=false;
    public enum Type{Ammo,Score,Wining}
    Type type;
    Image Icon;
    double PosX,PosY,ApperantY;
    public Canvas GiftsCanvas = new Canvas(Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());

    public Gifts(){
    }
    public abstract void Show();
    public void Hide(){
        if(!isHidden()){
        setHidden(true);
        GiftsCanvas.setVisible(false);}
    }
    public boolean isTaken(){return Taken;}
    public void setTaken(boolean taken){this.Taken=taken;}
    public boolean isHidden(){
        return Hidden;
    }
    public void setHidden(boolean Hidden){
        this.Hidden=Hidden;
    }
    public abstract void AddBonus();
    public double getPosX(){return this.PosX;}
    public double getPosY(){return this.PosY;}

}
