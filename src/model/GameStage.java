package model;

public interface GameStage{
    void start();
    void stop();
    int getID();
    StageType getStageType();
    void setID(int ID);
}