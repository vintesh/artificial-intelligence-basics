/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.eight_puzzle.ds;

/**
 *
 * @author Vintesh
 */
public class Tester {

    public static void main(String[] args) {
        int[][] node1 = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        int[][] node2 = {{1, 2, 3}, {4, 0, 5}, {6, 7, 8}};
        BoardFor8Puzzle board1 = new BoardFor8Puzzle(null, node1);
        BoardFor8Puzzle board2 = new BoardFor8Puzzle(board1, node2);
        System.out.println(board1.equals(board2));
    }
}
