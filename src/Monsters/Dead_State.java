package Monsters;

/**
 * Created by ehab on 1/17/2016.
 */
public class Dead_State implements MonsterState{
    Monster monster;
    public  Dead_State(Monster x){
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
    public void MakeDecision() {
        Monster.getMonsterSprite().hide();
        this.monster.setPosX(0);
        this.monster.setPosY(0);
        this.monster.state = this.monster.dead_State;
    }

    @Override
    public void switchFrame() {

    }

    @Override
    public void collide() {

    }

    @Override
    public void Revive() {
        this.monster.resetLives();
        this.monster.state = this.monster.outOfFrame_State;
    }

    @Override
    public void lifeloss() {
    }

    @Override
    public void killPlayer() {

    }
}
