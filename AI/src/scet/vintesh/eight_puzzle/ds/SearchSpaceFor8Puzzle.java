/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.eight_puzzle.ds;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Vintesh
 */
public class SearchSpaceFor8Puzzle {

    public BoardFor8Puzzle ROOT;
    public Queue<BoardFor8Puzzle> OPEN = new PriorityQueue<BoardFor8Puzzle>();
    public Queue<BoardFor8Puzzle> CLOSED = new PriorityQueue<BoardFor8Puzzle>();
    public LinkedList<BoardFor8Puzzle> solution = new LinkedList<BoardFor8Puzzle>();
    public static BoardFor8Puzzle goalState;

    static {
        int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        goalState = new BoardFor8Puzzle(null, tiles);
    }

    /**
     * Returns the HEAD of the QUEUE/BEST node from the LIST & Also REMOVE it
     * from the list.
     *
     * @return
     */
    public BoardFor8Puzzle getBestNodeFromOPEN() {
        return this.OPEN.remove();
    }

    /**
     * Check the given node is exist in the OPEN list or not
     *
     * @param board - node to be checked for existences in the OPEN LIST
     * @return - BoardFor8Puzzle Instance if it is exists or else NULL
     */
    public BoardFor8Puzzle isNodeExistInOPEN(BoardFor8Puzzle board) {
        for (BoardFor8Puzzle node : OPEN) {
            if (node.equals(board)) {
                return node;
            }
        }
        return null;
    }

    /**
     * Check the given node is exist in the CLOSED list or not
     *
     * @param board - node to be checked for existences in the OPEN LIST
     * @return - BoardFor8Puzzle Instance if it is exists or else NULL
     */
    public BoardFor8Puzzle isNodeExistInCLOSED(BoardFor8Puzzle board) {
        for (BoardFor8Puzzle node : CLOSED) {
            if (node.equals(board)) {
                return node;
            }
        }
        return null;
    }
}
