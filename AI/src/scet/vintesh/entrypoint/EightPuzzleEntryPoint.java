/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.entrypoint;

import java.util.ArrayList;
import java.util.Iterator;
import scet.vintesh.eight_puzzle.ds.Board;
import scet.vintesh.eight_puzzle.ds.SearchSpace;
import scet.vintesh.eight_puzzle.heuristic.search.AStarAlgo;

/**
 *
 * @author Vintesh
 */
public class EightPuzzleEntryPoint {

    private static SearchSpace searchSpace = new SearchSpace();

    public static void main(String[] args) {
        int[][] initialBoardState = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}}; // Ans in - 15 Steps
//        int[][] initialBoardState = {{4, 1, 2}, {0, 8, 7}, {6, 3, 5}}; // Ans in - 17 Steps
//        int[][] initialBoardState = {{4, 6, 1}, {5, 8, 3}, {2, 7, 0}}; // Unsolvable
//        int[][] initialBoardState = {{6, 1, 2}, {0, 7, 8}, {3, 5, 4}}; // Ans in - 25 Steps
//        int[][] initialBoardState = {{3, 0, 4}, {1, 5, 7}, {2, 8, 6}}; // Ans in - 21 Steps
//        int[][] initialBoardState = {{6, 1, 2}, {0, 7, 4}, {3, 5, 8}}; // Unsolvable
//        int[][] initialBoardState = {{7, 4, 1}, {0, 6, 8}, {3, 5, 2}}; // Unsolvable
        Board startNode = new Board(null, initialBoardState);

        searchSpace.ROOT = startNode;
        searchSpace.OPEN.add(startNode);
        startSolving();
//        startSolvingUsingBestFirstSearch(SearchSpace.currentNode);
    }

    private static void startSolving() {
        Iterator applyAStar = AStarAlgo.applyAStar(searchSpace);
        int step = 0;

        for (Iterator it = applyAStar; it.hasNext();) {
            Board board = (Board) it.next();
            System.out.println(step++ + " -> " + board);
        }
    }

    /**
     * The Best First Search Technique to find the GOAL State. This will lead to
     * StackOverFlow Execption sometimes due to the large Search Space because
     * We are generating the reduendent nodes in the Search Space Tree.
     *
     * @param currentNode - The Root of the Tree which is START State
     */
    @Deprecated
    private static void startSolvingUsingBestFirstSearch(Board currentNode) {
        if (currentNode.equals(SearchSpace.goalState)) {
            System.out.println("Solution is found.");
            return;
        } else {
            ArrayList<Board> newChilds = currentNode.generateSetAndGetChilds(currentNode);
            for (Board board : newChilds) {
                searchSpace.OPEN.add(board);
            }
            Board selectedNode = searchSpace.getBestNodeFromOPEN();
//            System.out.println(STEPS + " - Node: " + selectedNode.toString());
//            if (STEPS++ == 25) {
//                System.out.println("TEST");
//            }
            startSolvingUsingBestFirstSearch(selectedNode);
        }
    }
}
