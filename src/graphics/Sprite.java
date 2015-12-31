package graphics;

import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

public class Sprite extends AnimationTimer{

    private ArrayList<Image> images;
    private Canvas spriteCanvas = new Canvas(Game.getInstance().getFrame().getWidth(),Game.getInstance().getFrame().getHeight());
    private Iterator<Image> currentImage;
    private double refreshTime;
    private double lastTime=0;
    private double posX;
    private double posY;
    public Sprite(ArrayList<Image> images,double x,double y,double refreshTime) {
        this.images=images;
        this.setRefreshTime(refreshTime);
        this.posX=x;
        this.posY=y;
        Game.getInstance().getLayers().getChildren().add(spriteCanvas);
        currentImage = images.iterator();
     }
    @Override
    public void handle(long now) {
        if(now-lastTime>= getRefreshTime() *1000000||lastTime==0) {
            lastTime=now;
            spriteCanvas.getGraphicsContext2D().clearRect(0, 0, spriteCanvas.getWidth(), spriteCanvas.getHeight());
            if(!currentImage.hasNext()) currentImage = images.iterator();
            spriteCanvas.getGraphicsContext2D().drawImage(currentImage.next(), posX, posY);
            }
    }
    public void updatePosition(double newX,double newY){
        posX=newX;
        posY=newY;
    }
    public void hide(){
        spriteCanvas.setVisible(false);
    }
    public void show(){
        spriteCanvas.setVisible(true);
        this.start();
    }
    public double getX(){return posX;}
    public double getY(){return posY;}

    public double getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(double refreshTime) {
        this.refreshTime = refreshTime;
    }
}
