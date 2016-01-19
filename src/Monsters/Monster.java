package Monsters;

import graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Monster implements MonsterState{
    MonsterState movingUp_State;
    MonsterState movingDown_State;
    MonsterState movingRight_State;
    MonsterState movingLeft_State;
    MonsterState dead_State;
    MonsterState inFrame_State;
    MonsterState outOfFrame_State;
    MonsterState alive_State;

    MonsterState state = dead_State;

    private static Monster instance = new Monster();
    private Sprite upSprite;
    private double posX=200;
    private double posY=200;
    private double refreshTime = 100;
    private static Moving movingDirection = Moving.UP;

    private Monster(){
        movingUp_State = new MovingUp_State(this);
        movingDown_State = new MovingDown_State(this);
        movingRight_State = new MovingRight_State(this);
        movingLeft_State = new MovingLeft_State(this);
        dead_State = new Dead_State(this);
        inFrame_State = new InFrame_State(this);
        outOfFrame_State = new OutOfFrame_State(this);
        alive_State = new Alive_State(this);
        this.state = dead_State;
    }

    public void plantInMap() {
        this.state.plantInMap();
    }

    public void plantInFrame() {
        this.state.plantInFrame();
    }

    public void move() {
        this.state.move();
    }

    public void MakeDecision() {
        this.state.MakeDecision();
    }

    public void switchFrame() {
        this.state.switchFrame();
    }

    public void collide() {
        this.state.collide();
    }

    public void Revive() {
        this.state.Revive();
    }

    public void Die() {
        this.state.Die();
    }

    public void killPlayer() {
        this.state.killPlayer();
    }

    public enum Moving{UP,DOWN,LEFT,RIGHT}

    public void moveUp(){
        if(getPosY()<2)
            return;
        setPosY(getPosY() - 0.5);
        getUpSprite().show();
        movingDirection = Moving.UP;
    }


    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        getUpSprite().updatePosition(posX, posY);

    }
    public void setPosY(double posY) {
        this.posY = posY;
        getUpSprite().updatePosition(posX, posY);
    }

    public double getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(double refreshTime) {
        this.refreshTime = refreshTime;
    }



    public Sprite getUpSprite() {
        return upSprite;
    }
    public static Monster getInstance(){return instance;}

    public void setUpSprite(Sprite upSprite) {
        this.upSprite = upSprite;
    }
}
