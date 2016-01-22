package Monsters;

import graphics.Sprite;
import javafx.scene.image.Image;
import model.Maze;

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

    private Sprite monsterSprite;
    private double posX=200;
    private double posY=200;
    private double refreshTime = 100;
    private static Moving movingDirection = Moving.UP;
    private MonsterType monsterType;
    private int lives;
    public Monster(MonsterType x){
        monsterType = x;
        ArrayList<Image> sprite = new ArrayList<>();
        switch (x){
            case chrome:
                sprite.add(new Image("sprites/character/ChromeMonster.png"));
                lives = 1;
                break;
            case fireFox:
                sprite.add(new Image("sprites/character/FireFoxMonster.png"));
                lives = 2;
                break;
            case IE:
                sprite.add(new Image("sprites/character/IEMonster.png"));
                lives = 3;
                break;
        }
        setMonsterSprite(new Sprite(sprite, getPosX(), getPosY(), getRefreshTime()));

        movingUp_State = new MovingUp_State(this);
        movingDown_State = new MovingDown_State(this);
        movingRight_State = new MovingRight_State(this);
        movingLeft_State = new MovingLeft_State(this);
        dead_State = new Dead_State(this);
        inFrame_State = new InFrame_State(this);
        outOfFrame_State = new OutOfFrame_State(this);
        this.state = outOfFrame_State;
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
    public void lifeloss() {
        this.state.lifeloss();
    }

    @Override
    public void killPlayer() {
        this.state.killPlayer();
    }

    public enum Moving{UP,DOWN,LEFT,RIGHT}


    public void moveUp(){
        if(getPosY()<Maze.getMaxY())
            return;
        if(getPosX()>Maze.getXPath()-Maze.getDelta()&&getPosX()<Maze.getXPath()+Maze.getDelta()) {
            setPosY(getPosY() - 1);
            movingDirection = Moving.UP;
        }
    }
    public void moveDown(){
        if(getPosY()> Maze.getMinY())
            return;
        if(getPosX()>Maze.getXPath()-Maze.getDelta()&&getPosX()<Maze.getXPath()+Maze.getDelta()) {
            setPosY(getPosY() + 1);
            movingDirection = Moving.DOWN;
        }
    }
    public void moveLeft(){
        if(getPosX()<Maze.getMinX())
            return;
        if(getPosY()>Maze.getYPath()-Maze.getDelta()&&getPosY()<Maze.getYPath()+Maze.getDelta()) {
            setPosX(getPosX() - 1);
            movingDirection = Moving.LEFT;
        }
    }

    public void moveRight(){
        if(getPosX()>Maze.getMaxX())
            return;
        if(getPosY()>Maze.getYPath()-Maze.getDelta()&&getPosY()<Maze.getYPath()+Maze.getDelta()) {
            setPosX(getPosX() + 1);
            movingDirection = Moving.RIGHT;
        }
    }
    public void update(){
        this.state.MakeDecision();
        this.state.move();
        this.monsterSprite.updatePosition(getPosX(), getPosY());
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        getMonsterSprite().updatePosition(posX, posY);

    }
    public void setPosY(double posY) {
        this.posY = posY;
        getMonsterSprite().updatePosition(posX, posY);
    }

    public double getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(double refreshTime) {
        this.refreshTime = refreshTime;
    }

    public Sprite getMonsterSprite() {
        return monsterSprite;
    }

    public void setMonsterSprite(Sprite monsterSprite) {
        this.monsterSprite = monsterSprite;
    }
    public void resetLives(){
        switch (monsterType){
            case chrome:
                lives = 1;
                break;
            case fireFox:
                lives = 2;
                break;
            case IE:
                lives = 3;
                break;
        }
    }
    public int loseLife(){
        lives--;
        return lives;
    }
    public int getLives(){
        return lives;
    }
}
