/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.graph.ds;

import java.util.ArrayList;

/**
 *
 * @author Vintesh
 */
public class Node {

    private String identifier;
    private ArrayList<Edge> paths;

    public Node(String identifier) {
        this.identifier = identifier;
        paths = new ArrayList<Edge>();
    }

    public void setPaths(ArrayList<Edge> paths) {
        this.paths = paths;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ArrayList<Edge> getPaths() {
        return paths;
    }

    public void addPathToNode(Edge e) {
        paths.add(e);
    }

    @Override
    public String toString() {
        return this.identifier;
    }
}
