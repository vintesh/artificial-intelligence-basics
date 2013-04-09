/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.tic_tac_toe.ds;

import java.util.Scanner;

/**
 *
 * @author Vintesh
 */
public class GameStarter {

    static Scanner scanner = new Scanner(System.in);
    public static Symbol userSymbol = null;
    public static int nextMove = -1;

    public static void main(String[] args) {
        System.out.println("Enter your choice of Symbol: ");
        if (scanner.next().equals(Symbol.CROSS.toString())) {
            userSymbol = Symbol.CROSS;
        } else {
            userSymbol = Symbol.ZERO;
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Input the Positions to move:");
            nextMove = scanner.nextInt();
            
            // Computer's move
            GameTree.makeStateWithNewMove();
        }
    }
}
