package model;

import Monsters.Monster;
import Monsters.MonsterType;
import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.media.AudioClip;


import java.io.File;
import java.util.ArrayList;

public class GameLoop extends AnimationTimer {
    private static GameLoop instance = new GameLoop();
    private AudioClip gameTrack = new AudioClip(new File("resources/sounds/gameTrack.mp3").toURI().toString());
    private static ArrayList<Monster> monsters;

    public static GameLoop getInstance() {
        return instance;
    }

    private GameLoop() {
        setMonsters(new ArrayList<>());
        getMonsters().add(null);
        getMonsters().add(Monster.makeMonster(MonsterType.CHROME));
        getMonsters().add(Monster.makeMonster(MonsterType.FIREFOX));
        getMonsters().add(Monster.makeMonster(MonsterType.EXPLORER));
    }
    private void handleTransitions() {
        if (Player.getInstance().getPosX() <= 0 && Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()) != null) {
            Maze.getCurrentNode().getData().stop();

            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()));
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).plantInFrame();
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMaxX() - 1);
        } else if (Player.getInstance().getPosX() >= 832 && Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()) != null) {
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()));
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).plantInFrame();

            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMinX() + 1);
        } else if (Player.getInstance().getPosY() >= 502 && Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()) != null) {
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()));
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).plantInFrame();
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosY(Maze.getMaxY() + 1);
        } else if (Player.getInstance().getPosY() <= 108 && Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()) != null) {
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()));
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).switchFrame();
            getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).plantInFrame();
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosY(Maze.getMinY() - 1);
        }
    }

    @Override
    public void handle(long now) {
        Game.getInstance().makePlayerMovement();
        if (!gameTrack.isPlaying())
            gameTrack.play();
        getMonsters().get(Maze.getCurrentNode().getMonsterIndex()).update();
        Decorations.getInstance().updateDecorations();
        handleTransitions();
    }
    public static ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public static void setMonsters(ArrayList<Monster> monsters) {
        GameLoop.monsters = monsters;
    }
}
