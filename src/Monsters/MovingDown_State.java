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

    }

    @Override
    public void MakeDecision(){
        if(this.monster.getPosY()+1 == Player.getInstance().getPosY()){
            this.monster.killPlayer();
            return;
        }
        if(this.monster.getPosY()+1 == Maze.getMinY()){
            this.monster.state = this.monster.movingUp_State;
            return;
        }
        if(this.monster.getPosX()>Maze.getXPath()-Maze.getDelta()&&this.monster.getPosX()<Maze.getXPath()+Maze.getDelta()){
            //moving down is available
            this.monster.state = this.monster.movingDown_State;
            return;
        }else if(this.monster.getPosY()>Maze.getYPath()-Maze.getDelta()&&this.monster.getPosY()<Maze.getYPath()+Maze.getDelta()) {
            //moving left is available
            this.monster.state = this.monster.movingLeft_State;
            return;
        }else if(this.monster.getPosY()>Maze.getYPath()-Maze.getDelta()&&this.monster.getPosY()<Maze.getYPath()+Maze.getDelta()){
            //moving right is available
            this.monster.state = this.monster.movingRight_State;
            return;
        }
    }

    @Override
    public void switchFrame() {

    }

    @Override
    public void collide() {

    }

    @Override
    public void Revive() {

    }

    @Override
    public void Die() {

    }

    @Override
    public void killPlayer() {

    }
}
