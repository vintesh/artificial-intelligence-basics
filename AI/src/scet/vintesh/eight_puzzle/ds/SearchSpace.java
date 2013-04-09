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
public class SearchSpace {

    public Board ROOT;
    public Queue<Board> OPEN = new PriorityQueue<Board>();
    public Queue<Board> CLOSED = new PriorityQueue<Board>();
    public LinkedList<Board> solution = new LinkedList<Board>();
    public static Board goalState;

    static {
        int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        goalState = new Board(null, tiles);
    }

    /**
     * Returns the HEAD of the QUEUE/BEST node from the LIST & Also REMOVE it
     * from the list.
     *
     * @return
     */
    public Board getBestNodeFromOPEN() {
        return this.OPEN.remove();
    }

    /**
     * Check the given node is exist in the OPEN list or not
     *
     * @param board - node to be checked for existences in the OPEN LIST
     * @return - Board Instance if it is exists or else NULL
     */
    public Board isNodeExistInOPEN(Board board) {
        for (Board node : OPEN) {
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
     * @return - Board Instance if it is exists or else NULL
     */
    public Board isNodeExistInCLOSED(Board board) {
        for (Board node : CLOSED) {
            if (node.equals(board)) {
                return node;
            }
        }
        return null;
    }
}
