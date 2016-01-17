package Monsters;

/**
 * Created by ehab on 1/17/2016.
 */
public interface MonsterState {
    public void plantInMap();
    public void plantInFrame();
    public void move();
    public void MakeDecision();
    public void switchFrame();
    public void collide();
    public void Revive();
    public void Die();
    public void killPlayer();
}
