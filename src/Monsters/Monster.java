package Monsters;

import graphics.Sprite;
import javafx.scene.image.Image;
import model.GameLoop;
import model.Maze;
import model.Player;

import java.util.ArrayList;

public class Monster implements MonsterState{
    MonsterState movingUp_State;
    MonsterState movingDown_State;
    MonsterState movingRight_State;
    MonsterState movingLeft_State;
    MonsterState dead_State;
    MonsterState inFrame_State;
    MonsterState outOfFrame_State;

    MonsterState state;

    double deltaDisplacement;
    int Speed;
    boolean doingTurn;
    private Sprite monsterSprite;
    private double posX=200;
    private double posY=200;
    private double ApparentY =250;
    private double refreshTime = 100;
    private static Moving movingDirection = Moving.UP;
    private MonsterType monsterType;
    private int lives;
    private Monster(MonsterType x){
        monsterType = x;
        ArrayList<Image> sprite = new ArrayList<>();
        switch (x){
            case CHROME:
                sprite.add(new Image("sprites/character/ChromeMonster.png"));
                lives = 1;
                Speed = 50;
                break;
            case FIREFOX:
                sprite.add(new Image("sprites/character/FireFoxMonster.png"));
                lives = 2;
                Speed = 6;
                break;
            case EXPLORER:
                sprite.add(new Image("sprites/character/IEMonster.png"));
                lives = 3;
                Speed = 5;
                break;
        }
        this.monsterSprite = new Sprite(sprite, getPosX(), getPosY(), getRefreshTime());

        movingUp_State = new MovingUp_State(this);
        movingDown_State = new MovingDown_State(this);
        movingRight_State = new MovingRight_State(this);
        movingLeft_State = new MovingLeft_State(this);
        dead_State = new Dead_State(this);
        inFrame_State = new InFrame_State(this);
        outOfFrame_State = new OutOfFrame_State(this);
        this.state = outOfFrame_State;
        deltaDisplacement = GameLoop.getInstance().getDeltaDisplacement();
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

    public double getApparentY() {
        return ApparentY;
    }

    public void setApparentY(double apparentY) {
        ApparentY = apparentY;
    }

    public enum Moving{UP,DOWN,LEFT,RIGHT}


    public void moveUp(){
        setPosY(getPosY() - deltaDisplacement);
        movingDirection = Moving.UP;
    }
    public void moveDown(){
            setPosY(getPosY() + deltaDisplacement);
            movingDirection = Moving.DOWN;
    }
    public void moveLeft(){
            setPosX(getPosX() - deltaDisplacement);
            movingDirection = Moving.LEFT;
    }

    public void moveRight(){
        setPosX(getPosX() + deltaDisplacement);
        movingDirection = Moving.RIGHT;
    }
    public void update(){
        /*
        System.err.print("Current State: ");
        if(this.state == this.movingRight_State){
            System.err.print("movingRight_State");
        }else if(this.state == this.movingLeft_State){
            System.err.print("movingLeft_State");
        }else if(this.state == this.movingUp_State){
            System.err.print("movingUp_State");
        }else if(this.state == this.movingDown_State){
            System.err.print("movingDown_State");
        }else if(this.state == this.inFrame_State){
            System.err.print("inFrame_State");
        }else if(this.state == this.outOfFrame_State){
            System.err.print("outOfFrame_State");
        }
        System.err.println();
        */
        if(state == dead_State) return;
        if(state == outOfFrame_State) return;

        for(int i = 0; i<= this.Speed; i++) {
            this.state.MakeDecision();
            this.state.move();
            this.monsterSprite.updatePosition(getPosX(), getApparentY());
        }
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        getMonsterSprite().updatePosition(posX, getApparentY());

    }
    public void setPosY(double posY) {
        this.posY = posY;
        this.setApparentY(this.posY + 45);
        getMonsterSprite().updatePosition(posX, getApparentY());
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
            case CHROME:
                lives = 1;
                break;
            case FIREFOX:
                lives = 2;
                break;
            case EXPLORER:
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
    public boolean checkTurningPoint(){
        return (this.getPosX() < (Maze.getYPath() + Maze.getDelta()))
                && (this.getPosX() > (Maze.getYPath() - Maze.getDelta()))
                && (this.getPosY()  < (Maze.getXPath() + Maze.getDelta()))
                && (this.getPosY()  > (Maze.getXPath() - Maze.getDelta()));
    }
    public boolean checkInPlayerRange(){
        return (this.getPosX() < Player.getInstance().getPosX() + 30)
                && (this.getPosX() > Player.getInstance().getPosX() - 30)
                && (this.getPosY() > Player.getInstance().getPosY() - 30)
                && (this.getPosY() < Player.getInstance().getPosY() + 30);
    }
    public static Monster makeMonster(MonsterType x){
        return new Monster(x);
    }
}
