package Monsters;

/**
 * Created by ehab on 1/17/2016.
 */
public interface MonsterState {
    void plantInMap();
    void plantInFrame();
    void move();
    void MakeDecision();
    void switchFrame();
    void collide();
    void Revive();
    void lifeloss();
    void killPlayer();
}
