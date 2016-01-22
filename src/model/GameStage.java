package model;

public interface GameStage{
    void start();
    void stop();
    int getID();
    StageType getStageType();
    Path getParentCameFrom();
    void setID(int ID);
}