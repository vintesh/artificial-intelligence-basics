/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.heuristic.search;

import java.util.ArrayList;
import java.util.Iterator;
import scet.vintesh.eight_puzzle.ds.BoardFor8Puzzle;
import scet.vintesh.eight_puzzle.ds.SearchSpaceFor8Puzzle;

/**
 *
 * @author Vintesh
 */
public class AStarAlgo {

    private static int generatedStateNumber = 1;

    /**
     * Apply A* Algorithm & Stores the solution in the SEARCHSPACE's object's
     * member -> solution
     *
     * @param searchSpace - searchSpace with Goal State & Initial State
     * @return Iterator of solution linkedlist which generated from goal to
     * initial state & iterator is in reverse order so one can traverse FROM
     * initial state TO goal state.
     */
    public static Iterator applyAStar(SearchSpaceFor8Puzzle searchSpace) {
        while (searchSpace.OPEN.size() != 0) {
            if (searchSpace.OPEN.isEmpty()) {
                throw new IllegalStateException("OPEN List seems Empty. No Solution Found.");
            }
            // Get the best node from the List. i.e. HEAD of the PRIORITY QUEUE
            BoardFor8Puzzle bestNodeFromOPEN = searchSpace.getBestNodeFromOPEN();
            // Add to the CLOSED list.
            searchSpace.CLOSED.add(bestNodeFromOPEN);
            // Is that bestNode is the GOAL then Return or Continue.
            if (bestNodeFromOPEN.equals(SearchSpaceFor8Puzzle.goalState)) {
                System.out.println("Solution is found.");
                System.out.println("Path To Follow: ");
                BoardFor8Puzzle tracer = bestNodeFromOPEN;
                while (true) {
                    searchSpace.solution.add(tracer);
                    tracer = tracer.getParent();
                    if (tracer == null) {
                        break;
                    }
                }
                return searchSpace.solution.descendingIterator();
            } else {
                // Generate it's Successors - Generating the childs also added the parent link & 
                // also calculates the F, H & G Function values for them @ the time of thier genration
                ArrayList<BoardFor8Puzzle> successors = bestNodeFromOPEN.generateSetAndGetChilds(bestNodeFromOPEN);
                ArrayList<BoardFor8Puzzle> successorsToSet = new ArrayList<BoardFor8Puzzle>(successors);
                // For Each Successor 
                for (BoardFor8Puzzle board : successors) {
                    System.out.println(generatedStateNumber++ + " - Node: " + board);
                    boolean nodePresentInOpenOrClosed = false;
                    // -----------------------------------------------------
                    // Check whether the generated node is exist in OPEN or not
                    BoardFor8Puzzle old = searchSpace.isNodeExistInOPEN(board);
                    // If OLD is not NULL, then it Exist
                    if (old != null) {
                        nodePresentInOpenOrClosed = true;
                        // First of all remove the node from the SUCESSOR list
                        successorsToSet.remove(board);
                        // Change Parent if the Heuristic Value of SUCESSOR is lesser then the already generated node.
                        if (old.getFinalHeauristicValue() > board.getFinalHeauristicValue()) {
                            old.setParent(bestNodeFromOPEN);
                        }
                    }
                    // -----------------------------------------------------
                    // Check whether the generated node is exist in OPEN or not
                    BoardFor8Puzzle closedOld = searchSpace.isNodeExistInCLOSED(board);
                    // If OLD is not NULL, then it Exist
                    if (closedOld != null) {
                        nodePresentInOpenOrClosed = true;
                        // First of all remove the node from the SUCESSOR list
                        successorsToSet.remove(board);
                        // Change Parent if the Heuristic Value of SUCESSOR is lesser then the already generated node.
                        if (closedOld.getFinalHeauristicValue() > board.getFinalHeauristicValue()) {
                            closedOld.setParent(bestNodeFromOPEN);
                        }
                    }
                    // -----------------------------------------------------
                    // If node is not in OPEN or CLOSED then Add it to OPEN
                    if (!nodePresentInOpenOrClosed) {
                        searchSpace.OPEN.add(board);
                    }
                }
                bestNodeFromOPEN.setChildren(successorsToSet);
            }
        }
        throw new IllegalStateException("Open List is Empty - From: A*Algo");
    }
}
