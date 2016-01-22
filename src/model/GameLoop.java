package model;

import controller.Game;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.When;
import javafx.scene.media.AudioClip;
import jdk.nashorn.internal.ir.WhileNode;

import java.io.File;
import java.io.SyncFailedException;

public class GameLoop extends AnimationTimer{
    private static GameLoop instance = new GameLoop();
    private AudioClip gameTrack = new AudioClip(new File("resources/sounds/gameTrack.mp3").toURI().toString());

    public static GameLoop getInstance() {
        return instance;
    }
    private GameLoop() {
    }
    @Override
    public void handle(long now) {
        Game.getInstance().makePlayerMovement();
        //Game.getInstance().makeMonsterMovement();
        if (!gameTrack.isPlaying())
            gameTrack.play();
        Decorations.getInstance().updateDecorations();

            switch (Maze.getCurrentNode().getData().getStageType()) {
                case THREEPATHS:
                    if (Player.getInstance().getPosX() <= Maze.getMinX()) {     //leftmost
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(1));
                        Player.getInstance().setPosX(Maze.getMaxX() - 1);
                        System.out.print("hellooo");

                        Maze.getCurrentNode().getData().start();

                    }
                    if (Player.getInstance().getPosX() >= Maze.getMaxX()) {   //rightmost
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(0));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosX(Maze.getMinX() + 1);

                    }
                    if (Player.getInstance().getPosY() >= Maze.getMinY()) {   //down
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(1));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosY(Maze.getMaxY() + 1);
                    }
                    if (Player.getInstance().getPosY() <= Maze.getMaxY()) {    //up
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(1));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosY(Maze.getMinY() - 1);
                    }
                    //do same for the other 3 possibilities
                    break;
                case LEFTPATH:
                    if (Player.getInstance().getPosY() >= Maze.getMinY()) {   //down
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(0));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosY(Maze.getMaxY() + 1);
                    }
                    if (Player.getInstance().getPosY() <= Maze.getMaxY()) {    //up
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(0));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosY(Maze.getMinY() - 1);
                    }
                    if (Player.getInstance().getPosX() >= Maze.getMaxX() ) {  //leftmost
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(0));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosX(Maze.getMinX() + 1);
                    }
                    break;
                case RIGHTPATH:
                    if (Player.getInstance().getPosY() >= Maze.getMinY()) {   //down
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(1));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosY(Maze.getMaxY() + 1);
                    }
                    if (Player.getInstance().getPosY() <= Maze.getMaxY()) {    //up
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(1));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosY(Maze.getMinY() - 1);
                    }
                    if (Player.getInstance().getPosX() <= Maze.getMinX()) {   //rightmost
                        Maze.getCurrentNode().getData().stop();
                        Maze.setCurrentNode(Maze.getCurrentNode().getChildren().get(0));
                        Maze.getCurrentNode().getData().start();
                        Player.getInstance().setPosX(Maze.getMinX() + 1);

                    }



                    break;

                //do same as THREEPATHS
            }

    }
}

