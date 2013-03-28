/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.tic_tac_toe.ds;

import java.util.ArrayList;
import scet.vintesh.heuristic.search.ds.Node;

/**
 *
 * @author Vintesh
 */
public final class BoardForTicTacToe implements Comparable<BoardForTicTacToe>, Node {

    enum tileValue {

        ZERO, CROSS
    }
    private BoardForTicTacToe parent;
    private ArrayList<BoardForTicTacToe> childs;
    private int[][] tiles = new int[3][3];
    private float heuristicValue;
    private float actualValue;

    public BoardForTicTacToe(BoardForTicTacToe parent, int[][] tiles) {
        this.parent = parent;
        this.tiles = tiles;
        // Setting & calculating the position of blank tile.
        this.calculateActualCostUptillNow();
        this.calculateHeuristicValue();
    }

    public int compareTo(BoardForTicTacToe o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void calculateHeuristicValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void calculateActualCostUptillNow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public float getFinalHeauristicValue() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
