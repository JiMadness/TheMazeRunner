package Monsters;

import model.Maze;
import model.Player;

/**
 * Created by ehab on 1/17/2016.
 */
public class MovingUp_State implements MonsterState{
    Monster monster;
    public  MovingUp_State(Monster x){
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
        if(monster.getPosY()< Maze.getMaxY())
            return;
        if(monster.getPosX()>Maze.getXPath()-Maze.getDelta()&&monster.getPosX()<Maze.getXPath()+Maze.getDelta()) {
            monster.moveUp();
        }
    }

    @Override
    public void MakeDecision() {
        this.monster.setPosX(Maze.getXPath());
        if(this.monster.getPosY()-1 == Player.getInstance().getPosY()){
            this.monster.killPlayer();
            return;
        }
        if(monster.getPosY() +1 <= Maze.getMaxY()){
            this.monster.state = this.monster.movingDown_State;
            return;
        }
        if((this.monster.getPosY() <= Maze.getYPath()) && (this.monster.getPosX() - Maze.getDelta()) - 5 > Maze.getMinX()){
            //moving left is available (which is it's left)
            this.monster.state = this.monster.movingLeft_State;
            return;
        }else if(monster.getPosY() > Maze.getMaxY() ){
            //moving up is available
            this.monster.state = this.monster.movingUp_State;
            return;
        }else {
            //moving right is available (which is it's right)
            this.monster.state = this.monster.movingRight_State;
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
