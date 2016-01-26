package model;

import graphics.Ammo;
import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.ArrayList;

public class Player {

    private boolean gameOver;

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public enum Moving{UP,DOWN,LEFT,RIGHT}
    private static Player instance = new Player();

    private Sprite upSprite;
    private Sprite downSprite;
    private Sprite leftSprite;
    private Sprite rightSprite;

    private static Moving movingDirection;
    private AudioClip shootingSound = new AudioClip(new File("resources/sounds/shoot.wav").toURI().toString());
    private AudioClip noAmmo = new AudioClip(new File("resources/sounds/noAmmo.wav").toURI().toString());
    private double posX=Maze.getXPath();
    private double posY=Maze.getYPath();
    private double refreshTime = 100;
    private int score=0;
    private int ammo=10;
    public static Player getInstance() {
        return instance;
    }

    private Player() {
        ArrayList<Image> Ammos = new ArrayList<>();
        Ammos.add(new Image("Ammo/bom.png"));
        Ammos.add(new Image("Ammo/ball.png"));

        ArrayList<Image> ups = new ArrayList<>();
        ups.add(new Image("sprites/character/u1.png"));
        ups.add(new Image("sprites/character/u2.png"));
        ups.add(new Image("sprites/character/u3.png"));
        ups.add(new Image("sprites/character/u4.png"));
        ups.add(new Image("sprites/character/u5.png"));
        ups.add(new Image("sprites/character/u6.png"));
        ups.add(new Image("sprites/character/u7.png"));

        ArrayList<Image> downs = new ArrayList<>();
        downs.add(new Image("sprites/character/d1.png"));
        downs.add(new Image("sprites/character/d2.png"));
        downs.add(new Image("sprites/character/d3.png"));
        downs.add(new Image("sprites/character/d4.png"));
        downs.add(new Image("sprites/character/d5.png"));
        downs.add(new Image("sprites/character/d6.png"));
        downs.add(new Image("sprites/character/d7.png"));


        ArrayList<Image> lefts = new ArrayList<>();
        lefts.add(new Image("sprites/character/l1.png"));
        lefts.add(new Image("sprites/character/l2.png"));
        lefts.add(new Image("sprites/character/l3.png"));
        lefts.add(new Image("sprites/character/l4.png"));
        lefts.add(new Image("sprites/character/l5.png"));
        lefts.add(new Image("sprites/character/l6.png"));
        lefts.add(new Image("sprites/character/l7.png"));

        ArrayList<Image> rights = new ArrayList<>();
        rights.add(new Image("sprites/character/r1.png"));
        rights.add(new Image("sprites/character/r2.png"));
        rights.add(new Image("sprites/character/r3.png"));
        rights.add(new Image("sprites/character/r4.png"));
        rights.add(new Image("sprites/character/r5.png"));
        rights.add(new Image("sprites/character/r6.png"));
        rights.add(new Image("sprites/character/r7.png"));

        upSprite = new Sprite(ups, getPosX(), getPosY(), getRefreshTime());
        downSprite = new Sprite(downs, getPosX(), getPosY(), getRefreshTime());
        leftSprite = new Sprite(lefts, getPosX(), getPosY(), getRefreshTime());
        rightSprite = new Sprite(rights, getPosX(), getPosY(), getRefreshTime());
    }
    public void moveUp(){
        if(isGameOver()) return;
        if(getPosY()<Maze.getMaxY())
            return;
        if(getPosX()>Maze.getXPath()-Maze.getDelta()&&getPosX()<Maze.getXPath()+Maze.getDelta()) {
            getDownSprite().hide();
            getLeftSprite().hide();
            getRightSprite().hide();
            setPosY(getPosY() - 2);
            getUpSprite().show();
            if(checkInGiftRange()){
                if(!GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).isHidden()){
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).AddBonus();
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).setTaken(true);
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();}
            }
            movingDirection = Moving.UP;
        }
    }
    public void moveDown(){
        if(isGameOver()) return;
        if(getPosY()>Maze.getMinY())
            return;
        if(getPosX()>Maze.getXPath()-Maze.getDelta()&&getPosX()<Maze.getXPath()+Maze.getDelta()) {
            getUpSprite().hide();
            getLeftSprite().hide();
            getRightSprite().hide();
            setPosY(getPosY() + 2);
            getDownSprite().show();
            if(checkInGiftRange()){
                if(!GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).isHidden()){
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).AddBonus();
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).setTaken(true);
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();}
            }
            movingDirection = Moving.DOWN;
        }
    }
    public void moveLeft(){
        if(isGameOver()) return;
        if(getPosX()<Maze.getMinX())
            return;
        if(getPosY()>Maze.getYPath()-Maze.getDelta()&&getPosY()<Maze.getYPath()+Maze.getDelta()) {
            getUpSprite().hide();
            getDownSprite().hide();
            getRightSprite().hide();
            setPosX(getPosX() - 2);
            getLeftSprite().show();
            if(checkInGiftRange()){
                if(!GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).isHidden()){
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).AddBonus();
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).setTaken(true);

                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();}
            }
            movingDirection = Moving.LEFT;
        }
    }
    public void moveRight(){
        if(isGameOver()) return;
        if(getPosX()>Maze.getMaxX())
            return;
        if(getPosY()>Maze.getYPath()-Maze.getDelta()&&getPosY()<Maze.getYPath()+Maze.getDelta()) {
            getUpSprite().hide();
            getDownSprite().hide();
            getLeftSprite().hide();
            setPosX(getPosX() + 2);
            getRightSprite().show();
            if(checkInGiftRange()){
                if(!GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).isHidden()){
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).AddBonus();
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).setTaken(true);
                    GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();}
            }
            movingDirection = Moving.RIGHT;
        }
    }
    public void shoot(){
        if(isGameOver()) return;
        if(getAmmo()>0){
            shootingSound.play();
            setAmmo(getAmmo()-1);
            Ammo a=new Ammo(movingDirection,getPosX(),getPosY(),100000);
            a.show();
            Decorations.getInstance().updateDecorations();
        }
        else noAmmo.play();
    }
    public Sprite getUpSprite() {
        return upSprite;
    }

    public Sprite getDownSprite() {
        return downSprite;
    }

    public Sprite getLeftSprite() {
        return leftSprite;
    }

    public Sprite getRightSprite() {
        return rightSprite;
    }
    public boolean isLookingUp(){
        return movingDirection == Moving.UP;
    }
    public boolean isLookingDown(){
        return movingDirection == Moving.DOWN;
    }
    public boolean isLookingLeft(){
        return movingDirection == Moving.LEFT;
    }
    public boolean isLookingRight(){
        return movingDirection == Moving.RIGHT;
    }
    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getRefreshTime() {
        return refreshTime;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        getUpSprite().updatePosition(posX, posY);
        getDownSprite().updatePosition(posX, posY);
        getLeftSprite().updatePosition(posX, posY);
        getRightSprite().updatePosition(posX, posY);
    }

    public void setPosY(double posY) {
        this.posY = posY;
        getUpSprite().updatePosition(posX, posY);
        getDownSprite().updatePosition(posX, posY);
        getLeftSprite().updatePosition(posX, posY);
        getRightSprite().updatePosition(posX, posY);
    }

    public void setRefreshTime(double refreshTime) {
        this.refreshTime = refreshTime;
        getUpSprite().setRefreshTime(refreshTime);
        getDownSprite().setRefreshTime(refreshTime);
        getLeftSprite().setRefreshTime(refreshTime);
        getRightSprite().setRefreshTime(refreshTime);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
    public boolean checkInGiftRange(){
        return (this.posX < GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).getPosX() + 30)
                && (this.posX > GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).getPosX() - 30)
                && (this.posY > GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).getPosY() - 30)
                && (this.posY < GameLoop.getGifts().get(Maze.getCurrentNode().getGiftsIndex()).getPosY() + 30);
    }
}
