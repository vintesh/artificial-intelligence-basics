/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.entrypoint;

import java.util.ArrayList;
import java.util.Scanner;
import scet.vintesh.graph.ds.Edge;
import scet.vintesh.graph.ds.Graph;
import scet.vintesh.graph.ds.Node;
import scet.vintesh.graph.traversal.FindMultiplePaths;

/**
 *
 * @author Vintesh
 */
public class PathFinderEntryPoint {

    private static Graph G = new Graph();

    public static void main(String[] args) {
        graphTraversal();
    }

    private static void graphTraversal() {
        System.out.println("Path Finder: ->");
        try {
            initGraphTopo1();
            Node sourceNode = G.getNodeWithIdentifier("A");
            Node destinationNode = G.getNodeWithIdentifier("E");
//            initGraphTopo2();
//            Node sourceNode = G.getNodeWithIdentifier("1");
//            Node destinationNode = G.getNodeWithIdentifier("6");
//            FindMultiplePaths.findMultiplePath(G, sourceNode, destinationNode);
//            FindMultiplePaths.findMultiplePathUsingStack(G, sourceNode, destinationNode);
            FindMultiplePaths.findAllPaths(G, sourceNode, destinationNode);
        } catch (Exception e) {
            System.out.println("Node is not Found - Check Init Of the Graph or Source Node or Destination Node");
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Init Graph Method">
    private static void initGraphTopo1() throws IllegalAccessException {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("A"));
        nodes.add(new Node("B"));
        nodes.add(new Node("C"));
        nodes.add(new Node("D"));
        nodes.add(new Node("E"));

        // Here keep in mind that you have to add nodes before adding edges or it will raise exceptions.
        G.setNodes(nodes);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(G.getNodeWithIdentifier("A"), G.getNodeWithIdentifier("B"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("A"), G.getNodeWithIdentifier("D"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("B"), G.getNodeWithIdentifier("C"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("D"), G.getNodeWithIdentifier("C"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("D"), G.getNodeWithIdentifier("E"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("C"), G.getNodeWithIdentifier("E"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("C"), G.getNodeWithIdentifier("D"), false));

        G.setEdges(edges);
    }

    private static void initGraphTopo2() throws IllegalAccessException {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(new Node("1"));
        nodes.add(new Node("2"));
        nodes.add(new Node("3"));
        nodes.add(new Node("4"));
        nodes.add(new Node("5"));
        nodes.add(new Node("6"));
        nodes.add(new Node("7"));

        // Here keep in mind that you have to add nodes before adding edges or it will raise exceptions.
        G.setNodes(nodes);

        ArrayList<Edge> edges = new ArrayList<Edge>();
        edges.add(new Edge(G.getNodeWithIdentifier("1"), G.getNodeWithIdentifier("2"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("1"), G.getNodeWithIdentifier("3"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("1"), G.getNodeWithIdentifier("7"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("2"), G.getNodeWithIdentifier("3"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("2"), G.getNodeWithIdentifier("4"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("3"), G.getNodeWithIdentifier("2"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("3"), G.getNodeWithIdentifier("4"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("3"), G.getNodeWithIdentifier("7"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("4"), G.getNodeWithIdentifier("5"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("4"), G.getNodeWithIdentifier("6"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("5"), G.getNodeWithIdentifier("6"), false));
        edges.add(new Edge(G.getNodeWithIdentifier("7"), G.getNodeWithIdentifier("3"), false));

        G.setEdges(edges);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="For Inputting the Graph">
    private static void inputGraph() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the no. of Vertices/Nodes in Graph: ");
        int noOfVertices = scanner.nextInt();
        ArrayList<Node> nodes = new ArrayList<Node>();
        for (int i = 0; i < noOfVertices; i++) {
            System.out.print("Enter the Vertex/Node Identifier: ");
            nodes.add(new Node(scanner.nextLine()));
        }
        G.setNodes(nodes);

        System.out.print("Enter the no. of Edges in Graph: ");
        int noOfEdges = scanner.nextInt();
        ArrayList<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < noOfEdges; i++) {
            edges.add(inputEdge());
        }
    }

    private static Edge inputEdge() {
        Scanner scanner = new Scanner(System.in);
        Node sourceNode = null, destinationNode = null;
        boolean nodeExists = false;
        do {
            System.out.print("Enter the Source Vertex/Node Identifier: ");
            String sNodeId = scanner.nextLine();
            try {
                sourceNode = G.getNodeWithIdentifier(sNodeId);
                nodeExists = true;
            } catch (IllegalAccessException ex) {
                System.out.println("The Vertex Not Found in Graph .. Add Again");

            }
        } while (!nodeExists);

        nodeExists = false;
        do {
            System.out.print("Enter the Destination Vertex/Node Identifier: ");
            String sNodeId = scanner.nextLine();
            try {
                destinationNode = G.getNodeWithIdentifier(sNodeId);
                nodeExists = true;
            } catch (IllegalAccessException ex) {
                System.out.println("The Vertex Not Found in Graph .. Add Again");
            }
        } while (!nodeExists);

        System.out.println("Edge " + sourceNode.getIdentifier() + "->" + destinationNode.getIdentifier() + " is added.");
        return new Edge(sourceNode, destinationNode, false);
    }
    //</editor-fold>
}
