package Monsters;

/**
 * Created by ehab on 1/17/2016.
 */
public class OutOfFrame_State implements MonsterState{
    Monster monster;
    public  OutOfFrame_State(Monster x){
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
        this.monster.getMonsterSprite().hide();
    }

    @Override
    public void MakeDecision() {

    }

    @Override
    public void switchFrame() {
        this.monster.state = this.monster.inFrame_State;
    }

    @Override
    public void collide() {

    }

    @Override
    public void Revive() {this.monster.resetLives();
        this.monster.state = this.monster.outOfFrame_State;
    }

    @Override
    public void lifeloss() {

    }

    @Override
    public void killPlayer() {

    }
}

