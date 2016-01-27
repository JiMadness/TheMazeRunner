package Monsters;

import Menus.GameOverMenu;
import controller.Game;
import main.Main;
import model.Maze;
import model.Player;

import java.time.Duration;

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
            this.monster.state = this.monster.movingDown_State;
            return;
        }
        if(this.monster.getPosY()  > Maze.getMinY()){
            this.monster.state = this.monster.movingUp_State;
            return;
        }
        if(monster.checkTurningPoint()) {
            this.monster.doingTurn = true;
            if ((this.monster.getPosY() <= Maze.getYPath())  && (Maze.getXPath()+Maze.getDelta() < Maze.getMaxX())){
                //moving right is available (which is it's left)
                this.monster.state = this.monster.movingRight_State;
            } else if (this.monster.getPosY() < Maze.getMinY()) {
                //moving down is available
                this.monster.state = this.monster.movingDown_State;
            } else {
                //moving left is available (which is it's right)
                this.monster.state = this.monster.movingLeft_State;
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
        Player.getInstance().setGameOver(true);

        Game.getInstance().getLayers().getChildren().addAll(Game.getInstance().gameOverImageView);
        Game.getInstance().getLayers().getChildren().addAll(GameOverMenu.getInstance());

        Player.getInstance().getUpSprite().hide();
        Player.getInstance().getLeftSprite().hide();
        Player.getInstance().getRightSprite().hide();
        Player.getInstance().getDownSprite().hide();
        this.switchFrame();
    }
}
