/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.tic_tac_toe.ds;

import java.util.Iterator;
import java.util.PriorityQueue;
import scet.vintesh.tic_tac_toe.ds.Board.WhoseTurn;

/**
 *
 * @author Vintesh
 */
public class GameTree {

    /**
     * My algorithm Try - 1 - which might be working but yet to test & have to
     * modify by returing value i.e. it should return the heuristic value
     * instead of the Board.
     *
     * @param depth
     * @param currentBoard
     * @param computerSymbol
     * @return
     */
    static Board getNextStateByApplyingMinMax(int depth, Board currentBoard, WhoseTurn computerSymbol) {
        if (depth == 0) {
            return currentBoard;
        }

        currentBoard.generateChildren(computerSymbol);
        PriorityQueue<Board> childrens = currentBoard.getChilds();

        for (Iterator<Board> it = childrens.iterator(); it.hasNext();) {
            Board board = it.next();
            getNextStateByApplyingMinMax(depth - 1, board, computerSymbol.getOtherOne());
            if (depth > 0 && depth < 4) {
                if (depth % 2 == 1) {
                    return childrens.element();
                } else {
                    Object[] object = childrens.toArray();
                    return (Board) object[object.length - 1];
                }
            }
        }

        return childrens.element();
    }
    
    /**
     * MinMax Algorithm used is:
     * http://www.ocf.berkeley.edu/~yosenl/extras/alphabeta/alphabeta.html
     *
     * @param board - current Board instance
     * @param whoseTurn - which move is to be done X's or O's
     * @param depth - up to which level tree is being Expand
     * @return - the MAX value of the available for Children
     */
    public static int totalNoOfNodesExpanded = 0;

    static float minMax(Board board, WhoseTurn whoseTurn, int depth) {

        // Generating Children
        board.generateChildren(whoseTurn);
        PriorityQueue<Board> children = board.getChilds();
        totalNoOfNodesExpanded += children.size();
        
        /*
         * Returing the HeuristicValue for the leaf nodes in the tree those
         * values gonna be propagate to the parents in the tree
         */
        if (depth == 0) {
            return board.getHeuristicValue();
        }

        // Calling MinMax For All Children
        for (Iterator<Board> it = children.iterator(); it.hasNext();) {
            Board childBoard = it.next();
            childBoard.MinMaxVal = minMax(childBoard, whoseTurn.getOtherOne(), depth - 1);
        }

        switch (whoseTurn) {
            // Here user's move so we are returing the MIN value
            case O:
                Object[] object = children.toArray();
                return ((Board) object[object.length - 1]).MinMaxVal;

            // Assuming the 'X' is for Computer
            case X:
                return children.element().MinMaxVal;

            // Just for avoiding Error
            default:
                return 0;
        }
    }

    static float minMaxWithAlphaBetaCuttOff(Board board, WhoseTurn whoseTurn, int depth, float alpha, float beta) {

        // Generating Children
        board.generateChildren(whoseTurn);
        PriorityQueue<Board> children = board.getChilds();
        
        /*
         * Returing the HeuristicValue for the leaf nodes in the tree those
         * values gonna be propagate to the parents in the tree
         */
        if (depth == 0) {
            return board.getHeuristicValue();
        }

        // Calling MinMax For All Children
        for (Iterator<Board> it = children.iterator(); it.hasNext();) {
            Board childBoard = it.next();
            childBoard.MinMaxVal = minMaxWithAlphaBetaCuttOff(childBoard, whoseTurn.getOtherOne(), depth - 1, alpha, beta);
            totalNoOfNodesExpanded += 1;
        }

        float score;
        switch (whoseTurn) {
            // Here user's move so we are returing the MIN value
            case O:
                Object[] object = children.toArray();
                score = ((Board) object[object.length - 1]).MinMaxVal;
                if (score < beta) {
                    beta = score;
                }
                if (alpha >= beta) {
                    return beta;
                }
                return beta;

            // Assuming the 'X' is for Computer
            case X:
                score = children.element().MinMaxVal;
                if (score > alpha) {
                    alpha = score;
                }
                if (alpha >= beta) {
                    return alpha;
                }
                return alpha;

            // Just for avoiding Error
            default:
                return 0;
        }
    }
}
