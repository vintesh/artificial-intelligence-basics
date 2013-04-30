/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.tic_tac_toe.ds;

import java.util.Scanner;
import scet.vintesh.tic_tac_toe.ds.Board.TileValue;
import scet.vintesh.tic_tac_toe.ds.Board.WhoseTurn;

/**
 *
 * @author Vintesh
 */
public class GameStarter {

    private static Scanner scanner = new Scanner(System.in);
    private static Board currentBoard = new Board(null);
    private static int DEPTH = 8;

    /**
     * Here program is made by the Simple Heuristic given at
     * http://www.ocf.berkeley.edu/~yosenl/extras/alphabeta/alphabeta.html
     *
     * Also it is kept fixed that USER's SYMBOL is: O & COMPUTER's SYMBOL: X &
     * Also the First move will be initiated by User.
     */
    public static void startGame() {
        System.out.println("Starting GAME.... YOUR SYMBOL: O COMPUTER's SYMBOL: X ");
        System.out.println("Current State: " + currentBoard);

        // User need input for MAXIMUM 5 times.
        for (int i = 0; i < 5; i++) {
            System.out.print("Input the Positions to place O - (From 0-9):");
            currentBoard.setMove(getValidMoveFromUser(currentBoard), TileValue.ZERO);
            System.out.println("Your Move: " + currentBoard);

            if (isAnyOneWon(currentBoard) || isTie(currentBoard.getTiles())) {
                break;
            }

            // Computer's move
            // currentBoard = GameTree.getNextStateByApplyingMinMax(DEPTH, currentBoard, computerSymbol);
            currentBoard = currentBoard.getChildrenByMinMaxVal(GameTree.minMaxWithAlphaBetaCuttOff(currentBoard, WhoseTurn.X, DEPTH - i * 2, -15, 15));
            System.out.println("AI's Move: " + currentBoard);

            if (isAnyOneWon(currentBoard)) {
                break;
            }
        }
    }

    /**
     * Checks any Player won or not?
     *
     * @param board - The State for which to check
     * @return - true if anyone won, false or else
     */
    private static boolean isAnyOneWon(Board board) {
        if (isWon(board.getTiles(), TileValue.CROSS)) {
            System.out.println("Computer Program Won...!");
            return true;
        }
        if (isWon(board.getTiles(), TileValue.ZERO)) {
            System.out.println("You Won...!");
            return true;
        }
        return false;
    }

    private static boolean isWon(TileValue[][] tiles, TileValue tileValue) {
        // Checking for Rows
        for (int i = 0; i < 3; i++) {
            boolean isOtherExist = false;
            for (int j = 0; j < 3; j++) {
                if (tiles[i][j] != tileValue) {
                    isOtherExist = true;
                    break;
                }
            }
            if (!isOtherExist) {
                return true;
            }
        }
        // Checking for Columns
        for (int i = 0; i < 3; i++) {
            boolean isOtherExist = false;
            for (int j = 0; j < 3; j++) {
                if (tiles[j][i] != tileValue) {
                    isOtherExist = true;
                    break;
                }
            }
            if (!isOtherExist) {
                return true;
            }
        }
        // for Checking 2 Diagonals
        if (tiles[0][0] == tileValue && tiles[1][1] == tileValue && tiles[2][2] == tileValue) {
            return true;
        }
        if (tiles[0][2] == tileValue && tiles[1][1] == tileValue && tiles[2][0] == tileValue) {
            return true;
        }

        return false;
    }

    /**
     * Is Board State is in Tie or not?
     *
     * @param tiles - The tile Position
     * @return - true if tie, false or else
     */
    private static boolean isTie(TileValue[][] tiles) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tiles[i][j] == TileValue.BLANK) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getValidMoveFromUser(Board currentBoard) {
        while (true) {
            int position = scanner.nextInt();
            if (currentBoard.getTiles()[(position / 3)][(position % 3)] == TileValue.BLANK) {
                return position;
            }
            System.out.println("Enter valid moves...");
        }
    }
}
