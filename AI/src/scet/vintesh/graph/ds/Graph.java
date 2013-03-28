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
public class Graph {

    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;

    public Graph() {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
        for (Edge edge : edges) {
            edge.getSourceNode().addPathToNode(edge);
        }
        System.out.println("Done with setting all the paths in the egde list of the vertex. i.e. Adjecency List of Vertex");
    }

    public Node getNodeWithIdentifier(String id) throws IllegalAccessException {
        for (Node node : nodes) {
            if (node.getIdentifier().equalsIgnoreCase(id)) {
                return node;
            }
        }
        System.out.println("From: Graph.java _ Node with Id: " + id + " is not found");
        throw new IllegalAccessException("Node with Id: " + id + " is not found");
    }

    public boolean isAllEdgesVisited() {
        for (Edge edge : edges) {
            if (!(edge.isVisited())) {
                return false;
            }
        }
        return true;
    }

    public Edge findEdgeWithStartAndEndNodes(Node sourceNode, Node destinationNode) {
        for (Edge edge : edges) {
            if (edge.getSourceNode() == sourceNode && edge.getDestinationNode() == destinationNode) {
                return edge;
            }
        }
        return null;
    }
}
