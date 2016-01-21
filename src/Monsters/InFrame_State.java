package Monsters;

import model.Maze;
import model.Player;

/**
 * Created by ehab on 1/17/2016.
 */
public class InFrame_State implements MonsterState{
    Monster monster;
    public  InFrame_State(Monster x){
        this.monster = x;
    }
    @Override
    public void plantInMap() {

    }

    @Override
    public void plantInFrame() {
        //this should set the x and y position according to the position of the player(randomly but away from the player with tolerance)
        //and according to the type of the node
        //this is the first attempt
        this.monster.setPosY(Maze.getMinY());
        this.monster.setPosX(Maze.getXPath());
    }

    @Override
    public void move() {

    }

    @Override
    public void MakeDecision() {
        //plant in monster decision;
        this.plantInFrame();
        this.monster.getMonsterSprite().show();
        this.monster.state = this.monster.movingLeft_State;
    }

    @Override
    public void switchFrame() {
        this.monster.getMonsterSprite().hide();
        this.monster.state = this.monster.outOfFrame_State;
    }

    @Override
    public void collide() {
        lifeloss();
    }

    @Override
    public void Revive() {
        this.monster.resetLives();
        this.monster.state = this.monster.outOfFrame_State;
    }

    @Override
    public void lifeloss() {
        if(this.monster.loseLife() == 0){
            this.monster.getMonsterSprite().hide();
            this.monster.setPosX(0);
            this.monster.setPosY(0);
            this.monster.state = this.monster.dead_State;
        }
    }

    @Override
    public void killPlayer() {

    }
}
