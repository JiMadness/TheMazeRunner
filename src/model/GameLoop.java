package model;

import Monsters.Monster;
import Monsters.MonsterType;
import controller.Game;
import graphics.AmmoGift;
import graphics.Gifts;
import graphics.ScoreGift;
import graphics.WiningGift;
import javafx.animation.AnimationTimer;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.util.ArrayList;

public class GameLoop extends AnimationTimer {
    private static GameLoop instance = new GameLoop();
    private AudioClip gameTrack = new AudioClip(new File("resources/sounds/gameTrack.mp3").toURI().toString());
    private static ArrayList<Monster> monsters;
    private static ArrayList<Gifts> gifts;

    public static GameLoop getInstance() {
        return instance;
    }

    private GameLoop() {

        gifts=new ArrayList<>();
        gifts.add(new ScoreGift());
        gifts.add(new ScoreGift());
        gifts.add(new ScoreGift());
        gifts.add(new ScoreGift());
        gifts.add(new AmmoGift());
        gifts.add(new AmmoGift());
        gifts.add(new AmmoGift());
        gifts.add(new AmmoGift());
        gifts.add(new WiningGift());
    }
    private void handleTransitions() {
        if (Player.getInstance().getPosX() <= 0 && Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()) != null) {
            Maze.getCurrentNode().getData().stop();
            getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();

            if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
                Maze.getCurrentNode().getMonster().switchFrame();
            }

            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()));

            if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
                Maze.getCurrentNode().getMonster().switchFrame();
                Maze.getCurrentNode().getMonster().plantInFrame();
            }

            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMaxX() - 1);
            getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Show();

        } else if (Player.getInstance().getPosX() >= 832 && Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()) != null) {
           getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();

            if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
                Maze.getCurrentNode().getMonster().switchFrame();
            }

            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()));

            if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
                Maze.getCurrentNode().getMonster().switchFrame();
                Maze.getCurrentNode().getMonster().plantInFrame();
            }

            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMinX() + 1);
            getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Show();

        } else if (Player.getInstance().getPosY() >= 502 && Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()) != null) {

            if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
                Maze.getCurrentNode().getMonster().switchFrame();
            }

           getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();

            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()));

            if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
                Maze.getCurrentNode().getMonster().switchFrame();
                Maze.getCurrentNode().getMonster().plantInFrame();
            }

            Maze.getCurrentNode().getData().start();

            Player.getInstance().setPosY(Maze.getMaxY() + 1);
            getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Show();

        } else if (Player.getInstance().getPosY() <= 108 && Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()) != null) {

            if ((Maze.getCurrentNode().getMonsterIndex() > -1) && (Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())){
                Maze.getCurrentNode().getMonster().switchFrame();
            }

            getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()));

            if((Maze.getCurrentNode().getMonsterIndex()>-1)&&(Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
                Maze.getCurrentNode().getMonster().switchFrame();
                Maze.getCurrentNode().getMonster().plantInFrame();
            }

            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosY(Maze.getMinY() - 1);
            getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Show();

        }
    }

    @Override
    public void handle(long now) {
        Game.getInstance().makePlayerMovement();
        if (!gameTrack.isPlaying())
            gameTrack.play();
        if ((Maze.getCurrentNode().getMonsterIndex() > -1) && (Maze.getCurrentNode().getMonsterIndex() < getMonsters().size())) {
            Maze.getCurrentNode().getMonster().update();
        }
        Decorations.getInstance().updateDecorations();
        handleTransitions();
    }
    public static ArrayList<Monster> getMonsters() {
        return monsters;
    }
    public static ArrayList<Gifts> getGifts() {
        return gifts;
    }

    public static void setMonsters(ArrayList<Monster> monsters) {
        GameLoop.monsters = monsters;
    }
    public static void setGifts(ArrayList<Gifts> gifts) {
        GameLoop.gifts = gifts;
    }
    public void reviveMonsters(){
        for(Monster x: monsters){
            x.resetLives();
            x.Revive();
        }
    }
    public void resetMaze(){
        Maze.getCurrentNode().getData().stop();
        Maze.setCurrentNode(Game.getInstance().testMaze.getMazeTree().getRoot());
        getGifts().get(Maze.getCurrentNode().getGiftsIndex()).Hide();
        Maze.getCurrentNode().getData().start();
    }
    public double getDeltaDisplacement(){
        return 0.25;
    }
}
