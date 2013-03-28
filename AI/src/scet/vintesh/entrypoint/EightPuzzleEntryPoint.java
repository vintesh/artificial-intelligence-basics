/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.entrypoint;

import java.util.ArrayList;
import java.util.Iterator;
import scet.vintesh.eight_puzzle.ds.BoardFor8Puzzle;
import scet.vintesh.eight_puzzle.ds.SearchSpaceFor8Puzzle;
import scet.vintesh.heuristic.search.AStarAlgo;

/**
 *
 * @author Vintesh
 */
public class EightPuzzleEntryPoint {

    private static SearchSpaceFor8Puzzle searchSpace = new SearchSpaceFor8Puzzle();

    public static void main(String[] args) {
        int[][] initialBoardState = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
//        int[][] initialBoardState = {{6, 1, 2}, {0, 7, 8}, {3, 5, 4}};
        BoardFor8Puzzle startNode = new BoardFor8Puzzle(null, initialBoardState);

        searchSpace.ROOT = startNode;
        searchSpace.OPEN.add(startNode);
        startSolving();
//        startSolvingUsingBestFirstSearch(SearchSpaceFor8Puzzle.currentNode);
    }

    private static void startSolving() {
        Iterator applyAStar = AStarAlgo.applyAStar(searchSpace);
        int step = 0;

        for (Iterator it = applyAStar; it.hasNext();) {
            BoardFor8Puzzle board = (BoardFor8Puzzle) it.next();
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
    private static void startSolvingUsingBestFirstSearch(BoardFor8Puzzle currentNode) {
        if (currentNode.equals(SearchSpaceFor8Puzzle.goalState)) {
            System.out.println("Solution is found.");
            return;
        } else {
            ArrayList<BoardFor8Puzzle> newChilds = currentNode.generateSetAndGetChilds(currentNode);
            for (BoardFor8Puzzle board : newChilds) {
                searchSpace.OPEN.add(board);
            }
            BoardFor8Puzzle selectedNode = searchSpace.getBestNodeFromOPEN();
//            System.out.println(STEPS + " - Node: " + selectedNode.toString());
//            if (STEPS++ == 25) {
//                System.out.println("TEST");
//            }
            startSolvingUsingBestFirstSearch(selectedNode);
        }
    }
}
