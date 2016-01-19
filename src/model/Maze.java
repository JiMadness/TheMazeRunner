package model;

import datastructures.Node;
import datastructures.Tree;


public class Maze {
    private static Tree<GameStage> mazeTree = new Tree<>();
    private static Node<GameStage> currentNode;
    private static int minX;
    private static int minY;
    private static int maxX;
    private static int maxY;
    public Maze(GameStage root){
        setCurrentNode(new Node<>(root));
        getMazeTree().setRoot(getCurrentNode());
    }
    public static int getMinX() {
        return minX;
    }

    public static int getMinY() {
        return minY;
    }

    public static int getMaxX() {
        return maxX;
    }

    public static int getMaxY() {
        return maxY;
    }
    public static void setMinX(int minX) {
        Maze.minX = minX;
    }

    public static void setMinY(int minY) {
        Maze.minY = minY;
    }

    public static void setMaxX(int maxX) {
        Maze.maxX = maxX;
    }

    public static void setMaxY(int maxY) {
        Maze.maxY = maxY;
    }

    public Tree<GameStage> getMazeTree() {
        return mazeTree;
    }
    public static int getXPath() {
        return 425;
    }
    public static int getYPath() {
        return 425;
    }
    public static int getDelta() {
        return 25;
    }

    public static Node<GameStage> getCurrentNode() {
        return currentNode;
    }

    public static void setCurrentNode(Node<GameStage> currentNode) {
        Maze.currentNode = currentNode;
    }
}
