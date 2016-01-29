package graphics;


import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import model.GameLoop;
import model.Maze;
import model.Player;

public class Ammo extends AnimationTimer {
    private int Limit = 10;
    private Image Ammos;
    public Canvas AmmoCanvas=new Canvas(Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
    private double refreshTime;
    Player.Moving dir;
    private double lastTime = 0;
    private double posX;
    private double posY;
    private boolean max = false;
    private static Image Shoot = new Image("Ammo/ball.png");
    private static Image boom = new Image("Ammo/bom.png");
    public Ammo(){
    }

    public Ammo(Player.Moving movingDirection, double posX, double posY, int ref) {
        this.dir = movingDirection;
        this.refreshTime = ref;
        this.posX = posX;
        this.posY = posY;
        Game.getInstance().getLayers().getChildren().add(AmmoCanvas);
        this.start();
        AmmoCanvas.setVisible(false);
    }


    @Override
    public void handle(long now) {
        if (now - lastTime >= getRefreshTime() * 100 || lastTime == 0) {
            lastTime = now;
            AmmoCanvas.getGraphicsContext2D().clearRect(0, 0, AmmoCanvas.getWidth(), AmmoCanvas.getHeight());
            if (dir == Player.Moving.UP) {
                if (max) {
                    max=false;
                    this.stop();
                    this.hide();
                }
                else if (posY <= Maze.getMaxY()) {
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    max = true;
                }
                else if(checkInMonsterRange()){
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).collide();
                    Player.getInstance().setScore(Player.getInstance().getScore()+5);

                    max = true;
                }
                else if (posX > Maze.getXPath() - Maze.getDelta() && posX < Maze.getXPath() + Maze.getDelta()) {
                    posY -= 2;
                    System.out.println("here");
                    AmmoCanvas.getGraphicsContext2D().drawImage(Shoot, posX, posY-50);
                }
            }
            if (dir == Player.Moving.DOWN) {
                if (max) {
                    max=false;
                    this.stop();
                    this.hide();

                }

                else if (posY >= Maze.getMinY()) {
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    max = true;
                }
                else if(checkInMonsterRange()){
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).collide();
                    Player.getInstance().setScore(Player.getInstance().getScore()+5);
                    max = true;
                }
                else if (posX > Maze.getXPath() - Maze.getDelta() && posX < Maze.getXPath() + Maze.getDelta()) {
                    posY += 2;
                    System.out.println("here");
                    AmmoCanvas.getGraphicsContext2D().drawImage(Shoot, posX-10, posY+10);
                }
            }
            if (dir == Player.Moving.RIGHT) {
                if (max) {
                    max=false;
                    this.stop();
                    this.hide();

                }

                else if (posX >= Maze.getMaxX()) {
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    max = true;
                }
                else if(checkInMonsterRange()){
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).collide();
                    Player.getInstance().setScore(Player.getInstance().getScore()+5);

                    max = true;
                }
                else if (posY > Maze.getYPath() - Maze.getDelta() && posY < Maze.getYPath() + Maze.getDelta()) {
                    posX += 2;
                    System.out.println("here");
                    AmmoCanvas.getGraphicsContext2D().drawImage(Shoot, posX+30, posY);
                }
            }
            if (dir == Player.Moving.LEFT) {
                if (max) {
                    max=false;
                    this.stop();
                    this.hide();
                }
               else if (posX <= Maze.getMinX()) {
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    max = true;
                }
                else if(checkInMonsterRange()){
                    AmmoCanvas.getGraphicsContext2D().drawImage(boom, posX, posY);
                    GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).collide();
                    Player.getInstance().setScore(Player.getInstance().getScore()+5);

                    max = true;
                }
                else if (posY > Maze.getYPath() - Maze.getDelta() && posY < Maze.getYPath() + Maze.getDelta()) {
                    posX -= 2;
                    System.out.println("here");
                    AmmoCanvas.getGraphicsContext2D().drawImage(Shoot, posX-30, posY);
                }
            }
        }
    }
    public boolean checkInMonsterRange(){
        if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < GameLoop.getInstance().getMonsters().size()))
        return (this.posX < GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).getPosX() + 30)
                && (this.posX > GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).getPosX() - 30)
                && (this.posY > GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).getPosY() - 30)
                && (this.posY < GameLoop.getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).getPosY() + 30);
        return false;
    }

    public void updatePosition(double newX, double newY) {
        posX = newX;
        posY = newY;
    }

    public void hide() {
        AmmoCanvas.setVisible(false);
        this.stop();
    }

    public void show() {
        this.start();
        AmmoCanvas.setVisible(true);
    }
    public void setPosX(double x){
        this.posX=x;
    }
    public double getX() {
        return posX;
    }

    public double getY() {
        return posY;
    }
    public void setPosY(double y){this.posY=y;}
    public void setDir(Player.Moving dir){this.dir=dir;}

    public double getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(double refreshTime) {
        this.refreshTime = refreshTime;
    }

    public void addAmmo(int bonus) {
        this.Limit += bonus;
    }

}
