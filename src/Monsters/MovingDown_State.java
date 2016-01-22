package Monsters;

import model.Maze;
import model.Player;

/**
 * Created by ehab on 1/17/2016.
 */
public class MovingDown_State implements MonsterState{
    Monster monster;
    public  MovingDown_State(Monster x){
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
        if(monster.getPosY()>Maze.getMinY())
            return;
        if(monster.getPosX()>Maze.getXPath()-Maze.getDelta()&&monster.getPosX()<Maze.getXPath()+Maze.getDelta()) {
            monster.moveDown();
        }
    }

    @Override
    public void MakeDecision(){
        this.monster.setPosX(Maze.getXPath());
        if(this.monster.getPosY()+1 == Player.getInstance().getPosY()){
            this.monster.killPlayer();
            return;
        }
        if(this.monster.getPosY()+1 >= Maze.getMinY()){
            this.monster.state = this.monster.movingUp_State;
            return;
        }
        if((this.monster.getPosY() > Maze.getYPath()) && (monster.getPosX()+Maze.getDelta()+5 < Maze.getMaxX())){
            //moving right is available (which is it's left)
            this.monster.state = this.monster.movingRight_State;
            return;
        }else if(this.monster.getPosY() < Maze.getMinY()){
            //moving down is available
            this.monster.state = this.monster.movingDown_State;
            return;
        }else {
            //moving left is available (which is it's right)
            this.monster.state = this.monster.movingLeft_State;
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
