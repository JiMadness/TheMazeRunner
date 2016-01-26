package graphics;


import controller.Game;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;


public abstract class Gifts {
    int Value;
    private boolean Hidden=true,Taken=false;
    public enum Type{Ammo,Score}
    Type type;
    Image Icon;
    double PosX,PosY,ApperantY;
    public Canvas GiftsCanvas = new Canvas(Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
   // private int random = (int) Math.floor(Math.random()*10);
   // Random X = new Random();
   // Random Y = new Random();
    //int posX=X.nextInt(Maze.getMaxX() - Maze.getMinX() + 1)+ Maze.getMinX() ;
    //int posY=Y.nextInt(Maze.getMaxY() - Maze.getMinY() + 1)+Maze.getMinY() ;
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
    /*public Type GetType(){
        return type;
    }*/
    public abstract void AddBonus();
    public double getPosX(){return this.PosX;}
    public double getPosY(){return this.PosY;}

}
