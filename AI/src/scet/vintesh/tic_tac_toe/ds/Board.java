/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.tic_tac_toe.ds;

import java.util.ArrayList;

/**
 *
 * @author Vintesh
 */
public final class Board implements Comparable<Board> {

    enum tileValue {

        ZERO, CROSS
    }
    private Board parent;
    private ArrayList<Board> childs;
    private int[][] tiles = new int[3][3];
    private float heuristicValue;

    public Board(Board parent, int[][] tiles) {
        this.parent = parent;
        this.tiles = tiles;
        // Setting & calculating the position of blank tile.
        this.calculateHeuristicValue();
    }

    /**
     * @TODO: Implement .Equals Method
     *
     * @param o
     * @return
     */
    public int compareTo(Board o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @TODO: Implement the heuristic function.
     */
    public void calculateHeuristicValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ArrayList<Board> getChilds() {
        return childs;
    }

    public float getHeuristicValue() {
        return heuristicValue;
    }
}
