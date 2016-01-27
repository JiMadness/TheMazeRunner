package Monsters;

import Menus.GameOverMenu;
import controller.Game;
import main.Main;
import model.Maze;
import model.Player;

import java.time.Duration;

public class MovingLeft_State implements MonsterState{
    Monster monster;
    public  MovingLeft_State(Monster x){
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
        if(monster.getPosX()< Maze.getMinX())
            return;
        if(monster.getPosY()>Maze.getYPath()-Maze.getDelta()&&monster.getPosY()<Maze.getYPath()+Maze.getDelta()){
            monster.moveLeft();
        }
    }

    @Override
    public void MakeDecision() {
        this.monster.setPosY(Maze.getYPath());
        if(monster.checkInPlayerRange()){
            this.monster.killPlayer();
            return;
        }
        if(!monster.checkTurningPoint() && this.monster.doingTurn){
            this.monster.doingTurn = false;
        }
        if(monster.checkTurningPoint() && this.monster.doingTurn){
            this.monster.state = this.monster.movingLeft_State;
            return;
        }
        if(this.monster.getPosX()-1 <= Maze.getMinX()){
            this.monster.state = this.monster.movingRight_State;
            return;
        }
        if(monster.checkTurningPoint()) {
            this.monster.doingTurn = true;
            if (monster.getPosX()>= Maze.getXPath()) {
                //moving down is available (which is it's left)
                this.monster.state = this.monster.movingDown_State;
            } else if (this.monster.getPosX() > Maze.getMinX()) {
                //moving left is available (which is it's front)
                this.monster.state = this.monster.movingLeft_State;
            } else {
                //moving up is available (which is it's right)
                this.monster.state = this.monster.movingUp_State;
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
