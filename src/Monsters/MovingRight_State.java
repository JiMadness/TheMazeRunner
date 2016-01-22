package Monsters;

import model.Maze;
import model.Player;

/**
 * Created by ehab on 1/17/2016.
 */
public class MovingRight_State implements MonsterState{
    Monster monster;
    public  MovingRight_State(Monster x){
        this.monster = x;
    }

    @Override
    public void plantInMap() {

    }

    @Override
    public void plantInFrame() {

    }

    @Override
    public void move() {
        if(monster.getPosX()> Maze.getMaxX())
            return;
        if(monster.getPosY()>Maze.getYPath()-Maze.getDelta()&&monster.getPosY()<Maze.getYPath()+Maze.getDelta()) {
            monster.moveRight();
        }
    }

    @Override
    public void MakeDecision() {
        this.monster.setPosY(Maze.getYPath());
        if(this.monster.getPosX()+1 == Player.getInstance().getPosX()){
            this.monster.killPlayer();
            return;
        }
        if(this.monster.getPosX()+1 >=  Maze.getMaxX()){
            this.monster.state = this.monster.movingLeft_State;
            return;
        }
        if((monster.getPosX()>Maze.getXPath()) && (this.monster.getPosY() - Maze.getDelta() -5 > Maze.getMaxY())){
            //moving up is available (which is it's left)
            this.monster.state = this.monster.movingUp_State;
            return;
        } else  if(this.monster.getPosX() <  Maze.getMaxX()){
            //moving right is available (which is it's front)
            this.monster.state = this.monster.movingRight_State;
            return;
        }else {
            //moving down is available
            this.monster.state = this.monster.movingDown_State;
            return;
        }

    }

    @Override
    public void switchFrame() {
        this.monster.state = this.monster.outOfFrame_State;
        this.monster.getMonsterSprite().hide();
    }

    @Override
    public void collide() {
        //bullet collision
        lifeloss();
    }

    @Override
    public void Revive() {this.monster.resetLives();
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
