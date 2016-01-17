package Monsters;

import graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * Created by ehab on 1/13/2016.
 */
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

    private Sprite upSprite;
    private double posX=200;
    private double posY=200;
    private double refreshTime = 100;
    private static Moving movingDirection = Moving.UP;

    public Monster(){
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

    @Override
    public void plantInMap() {
        this.state.plantInMap();
    }

    @Override
    public void plantInFrame() {
        this.state.plantInFrame();
    }

    @Override
    public void move() {
        this.state.move();
    }

    @Override
    public void MakeDecision() {
        this.state.MakeDecision();
    }

    @Override
    public void switchFrame() {
        this.state.switchFrame();
    }

    @Override
    public void collide() {
        this.state.collide();
    }

    @Override
    public void Revive() {
        this.state.Revive();
    }

    @Override
    public void Die() {
        this.state.Die();
    }

    @Override
    public void killPlayer() {
        this.state.killPlayer();
    }

    public enum Moving{UP,DOWN,LEFT,RIGHT}
    private Monster(){
        ArrayList<Image> ups = new ArrayList<>();
        ups.add(new Image("sprites/character/Monster.png"));
        setUpSprite(new Sprite(ups, getPosX(), getPosY(), getRefreshTime()));
    }

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

    public void setUpSprite(Sprite upSprite) {
        this.upSprite = upSprite;
    }
}
