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
package scet.vintesh.heuristic.search.ds;

/**
 *
 * @author Vintesh
 */
public interface Node {

    void calculateHeuristicValue();

    void calculateActualCostUptillNow();

    float getFinalHeauristicValue();
}
