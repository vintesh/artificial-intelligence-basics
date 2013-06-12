/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.genetic_algorithm;

import java.util.PriorityQueue;

/**
 *
 * @author Vintesh
 */
public class MyPriorityQueue<T> extends PriorityQueue<T> {

    /**
     * Returns an object by it's Index in the Queue
     *
     * @param i - specified index
     * @return - object by it's Index in the Queue
     */
    public T get(int i) {
        Object[] arrayObject = this.toArray();
        return (T) arrayObject[i];
    }
}
