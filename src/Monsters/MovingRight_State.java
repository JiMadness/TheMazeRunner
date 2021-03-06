package Monsters;

import Menus.GameOverMenu;
import controller.Game;
import javafx.scene.image.Image;
import main.Main;
import model.Maze;
import model.Player;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.time.Duration;


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
        monster.setPosY(Maze.getYPath() + 50);
        this.monster.setPosY(Maze.getYPath());
        if(monster.checkInPlayerRange()){
            this.monster.killPlayer();
            return;
        }
        if(!monster.checkTurningPoint() && this.monster.doingTurn){
            this.monster.doingTurn = false;
        }
        if(monster.checkTurningPoint() && this.monster.doingTurn){
            this.monster.state = this.monster.movingRight_State;
            return;
        }
        if(this.monster.getPosX()+1 >=  Maze.getMaxX()){
            this.monster.state = this.monster.movingLeft_State;
            return;
        }
        if(monster.checkTurningPoint()) {
            this.monster.doingTurn = true;
            if (monster.getPosX()<= Maze.getXPath()) {
                //moving up is available (which is it's left)
                this.monster.state = this.monster.movingUp_State;
            } else if (this.monster.getPosX() < Maze.getMaxX()) {
                //moving right is available (which is it's front)
                this.monster.state = this.monster.movingRight_State;
            } else {
                //moving down is available
                this.monster.state = this.monster.movingDown_State;
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
