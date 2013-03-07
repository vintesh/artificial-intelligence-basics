/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.entrypoint;

import java.util.ArrayList;
import scet.vintesh.eight_puzzle.ds.Board;
import scet.vintesh.eight_puzzle.ds.SearchSpace;

/**
 *
 * @author Vintesh
 */
public class Solve8Puzzle {

    private static int STEPS = 1;

    public static void main(String[] args) {
//        int[][] initialBoardState = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        int[][] initialBoardState = {{6, 1, 2}, {0, 7, 8}, {3, 5, 4}};
        Board startNode = new Board(null, initialBoardState);

        SearchSpace.ROOT = startNode;
        SearchSpace.currentNode = startNode;

        startAStarAlog(SearchSpace.currentNode);
    }

    private static void startAStarAlog(Board currentNode) {
        if (currentNode.equals(Board.goalState)) {
            System.out.println("Solution is found.");
            return;
        } else {
            ArrayList<Board> newChilds = currentNode.generateAndSetChilds(currentNode);
            for (Board board : newChilds) {
                SearchSpace.OPEN.add(board);
            }
            Board selectedNode = SearchSpace.OPEN.remove();
            System.out.println(STEPS + " - Node: " + selectedNode.toString());
            if (STEPS++ == 25) {
                System.out.println("TEST");
            }
            startAStarAlog(selectedNode);
        }
    }
}
