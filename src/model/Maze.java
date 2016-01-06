package model;

import datastructures.Node;
import datastructures.Tree;

public class Maze {
    private Tree<GameStage> mazeTree = new Tree<>();
    public Maze(GameStage root){
     getMazeTree().setRoot(new Node<>(root));
    }
    public Tree<GameStage> getMazeTree() {
        return mazeTree;
    }
}
