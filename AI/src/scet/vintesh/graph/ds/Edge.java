/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.graph.ds;

/**
 *
 * @author Vintesh
 */
public class Edge {

    private Node sourceNode;
    private Node destinationNode;
    private boolean visited;

    public Edge(Node sourceNode, Node destinationNode, boolean visited) {
        this.sourceNode = sourceNode;
        this.destinationNode = destinationNode;
        this.visited = visited;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public Node getSourceNode() {
        return sourceNode;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return this.sourceNode + "->" + this.destinationNode;
    }
}
