package model;

import Monsters.Monster;
import Monsters.MonsterType;
import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.CubicCurve;
import main.Main;

import java.io.File;
import java.util.ArrayList;

public class GameLoop extends AnimationTimer {
    private static GameLoop instance = new GameLoop();
    private AudioClip gameTrack = new AudioClip(new File("resources/sounds/gameTrack.mp3").toURI().toString());
    public static GameLoop getInstance() {
        return instance;
    }
    public static ArrayList<Monster> monsters;

    private GameLoop() {
        monsters=new ArrayList<>();
        monsters.add(new Monster(MonsterType.fireFox));
        monsters.add(new Monster(MonsterType.fireFox));
        monsters.add(new Monster(MonsterType.chrome));
        monsters.add(new Monster(MonsterType.IE));
    }

    private void handleTransitions(){
        if (Player.getInstance().getPosX() <= 0 && Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()) != null) {
            Maze.getCurrentNode().getData().stop();

                monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
                Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.LEFT.ordinal()));
                monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
                monsters.get(Maze.getCurrentNode().getHasMonster()).plantInFrame();
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMaxX() - 1);
        } else if (Player.getInstance().getPosX() >= 832 && Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()) != null) {
                monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.RIGHT.ordinal()));
            monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
            monsters.get(Maze.getCurrentNode().getHasMonster()).plantInFrame();

            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosX(Maze.getMinX() + 1);
        } else if (Player.getInstance().getPosY() >= 502 && Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()) != null) {
                monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.DOWN.ordinal()));
               monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
           monsters.get(Maze.getCurrentNode().getHasMonster()).plantInFrame();
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosY(Maze.getMaxY() + 1);
        } else if (Player.getInstance().getPosY() <= 108 && Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()) != null) {
              monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
            Maze.getCurrentNode().getData().stop();
            Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(Path.UP.ordinal()));
           monsters.get(Maze.getCurrentNode().getHasMonster()).switchFrame();
            monsters.get(Maze.getCurrentNode().getHasMonster()).plantInFrame();
            Maze.getCurrentNode().getData().start();
            Player.getInstance().setPosY(Maze.getMinY() - 1);
        }
    }

    @Override
    public void handle(long now) {
        Game.getInstance().makePlayerMovement();
        if (!gameTrack.isPlaying())
            gameTrack.play();
            monsters.get(Maze.getCurrentNode().getHasMonster()).update();
        Decorations.getInstance().updateDecorations();
        handleTransitions();
    }
}
