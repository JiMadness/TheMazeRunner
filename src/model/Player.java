package model;

import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.ArrayList;

public class Player {
    public enum Moving{UP,DOWN,LEFT,RIGHT}
    private static Player instance = new Player();

    private Sprite upSprite;
    private Sprite downSprite;
    private Sprite leftSprite;
    private Sprite rightSprite;

    public static Moving movingDirection;
    private AudioClip shootingSound = new AudioClip(new File("resources/sounds/shoot.wav").toURI().toString());
    private AudioClip noAmmo = new AudioClip(new File("resources/sounds/noAmmo.wav").toURI().toString());
    private double posX=300;
    private double posY=300;
    private double refreshTime = 100;
    private int score=0;
    private int ammo=10;
    public static Player getInstance() {
        return instance;
    }

    private Player() {
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
        downSprite.hide();
        leftSprite.hide();
        rightSprite.hide();
        setPosY(getPosY() - 2);
        upSprite.show();
        movingDirection = Moving.UP;
    }
    public void moveDown(){
        upSprite.hide();
        leftSprite.hide();
        rightSprite.hide();
        setPosY(getPosY() + 2);
        downSprite.show();
        movingDirection = Moving.DOWN;
    }
    public void moveLeft(){
        upSprite.hide();
        downSprite.hide();
        rightSprite.hide();
        setPosX(getPosX() - 2);
        leftSprite.show();
        movingDirection = Moving.LEFT;
    }
    public void moveRight(){
        upSprite.hide();
        downSprite.hide();
        leftSprite.hide();
        setPosX(getPosX() + 2);
        rightSprite.show();
        movingDirection = Moving.RIGHT;
    }
    public void shoot(){
        if(getAmmo()>0){
            shootingSound.play();
            setAmmo(getAmmo()-1);
        }
        else noAmmo.play();
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
        upSprite.updatePosition(posX,posY);
        downSprite.updatePosition(posX,posY);
        leftSprite.updatePosition(posX,posY);
        rightSprite.updatePosition(posX,posY);
    }

    public void setPosY(double posY) {
        this.posY = posY;
        upSprite.updatePosition(posX,posY);
        downSprite.updatePosition(posX,posY);
        leftSprite.updatePosition(posX,posY);
        rightSprite.updatePosition(posX,posY);
    }

    public void setRefreshTime(double refreshTime) {
        this.refreshTime = refreshTime;
        upSprite.setRefreshTime(refreshTime);
        downSprite.setRefreshTime(refreshTime);
        leftSprite.setRefreshTime(refreshTime);
        rightSprite.setRefreshTime(refreshTime);
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
}
