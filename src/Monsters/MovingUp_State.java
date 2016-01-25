package Monsters;

import controller.Game;
import model.Maze;
import model.Player;

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
        monster.setPosX(Maze.getXPath());
        this.monster.setPosX(Maze.getXPath());
        if(monster.checkInPlayerRange()){
            this.monster.killPlayer();
            return;
        }
        if(!monster.checkTurningPoint() && this.monster.doingTurn){
            this.monster.doingTurn = false;
        }
        if(monster.checkTurningPoint() && this.monster.doingTurn){
            this.monster.state = this.monster.movingUp_State;
            return;
        }
        if(monster.getPosY()  < Maze.getMaxY()){
            this.monster.state = this.monster.movingDown_State;
            return;
        }
        if(monster.checkTurningPoint()) {
            this.monster.doingTurn = true;
            if ((this.monster.getPosY() >= Maze.getYPath()) && (Maze.getXPath()-Maze.getDelta() > Maze.getMinX())) {
                //moving left is available (which is it's left)
                this.monster.state = this.monster.movingLeft_State;
            } else if (monster.getPosY() > Maze.getMaxY()) {
                //moving up is available
                this.monster.state = this.monster.movingUp_State;
            } else {
                //moving right is available (which is it's right)
                this.monster.state = this.monster.movingRight_State;
            }
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
        Player.getInstance().setWalkable(false);
        Game.getInstance().getFrame().getGraphicsContext2D().drawImage(Game.getInstance().gameOverImage, 0, 0, Game.getInstance().getFrame().getWidth(), Game.getInstance().getFrame().getHeight());
        Player.getInstance().getUpSprite().hide();
        Player.getInstance().getLeftSprite().hide();
        Player.getInstance().getRightSprite().hide();
        Player.getInstance().getDownSprite().hide();
        this.monster.getMonsterSprite().hide();
    }
}
