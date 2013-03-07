/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scet.vintesh.eight_puzzle.ds;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Vintesh
 */
public class SearchSpace {

    public static Board ROOT;
    public static Board currentNode;
    public static Queue<Board> OPEN = new PriorityQueue<Board>();
}
