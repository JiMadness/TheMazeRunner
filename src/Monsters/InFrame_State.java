package Monsters;

import model.Maze;
import model.Player;

import java.util.Random;

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
        if((Player.getInstance().getPosY() < Maze.getYPath()+Maze.getDelta())
           &&(Player.getInstance().getPosY() > Maze.getXPath()- Maze.getDelta())
           ){
            //it's in the Xpath
            System.err.println("Player In X");
            this.monster.setPosY(new Random().nextInt(Maze.getMinY() - Maze.getMaxY())  + Maze.getMaxY());
            this.monster.setPosX(Maze.getXPath());
        }else{
            //it's in the Y path
            System.err.println("Player In Y");
            this.monster.setPosY(Maze.getYPath());
            this.monster.setPosX(new Random().nextInt(Maze.getMaxX() - Maze.getMinX())  + Maze.getMinX());
        }
    }

    @Override
    public void move() {

    }

    @Override
    public void MakeDecision() {
        //plant in monster decision;
        this.plantInFrame();
        this.monster.getMonsterSprite().show();
        if((Player.getInstance().getPosY() < Maze.getYPath()+Maze.getDelta())
                &&(Player.getInstance().getPosY() > Maze.getXPath()- Maze.getDelta())
                ){
            if(new Random().nextBoolean()){
                this.monster.state = this.monster.movingUp_State;
            }else{
                this.monster.state = this.monster.movingDown_State;
            }
        }else {
            if(new Random().nextBoolean()){
                this.monster.state = this.monster.movingLeft_State;
            }else{
                this.monster.state = this.monster.movingRight_State;
            }
        }
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
