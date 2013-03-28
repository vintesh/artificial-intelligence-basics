/*
 * Implemented as Tutorial of Masters Program 
 * M.E. - Computer Engineering 
 * Artificial Intelligence
 * SCET, Surat
 */
package scet.vintesh.graph.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import scet.vintesh.graph.ds.Edge;
import scet.vintesh.graph.ds.Graph;
import scet.vintesh.graph.ds.Node;

/**
 *
 * @author Vintesh
 */
public class FindMultiplePaths {

    public static ArrayList<LinkedList<Node>> solutions = new ArrayList<LinkedList<Node>>();

    //<editor-fold defaultstate="collapsed" desc="Try - 2: Graph Without Cycle from stackoverflow & need to be Tested">
    private static void findPath(LinkedList<Node> path, Node sourceNode, Node destinationNode) {

        boolean isNodeIsParent = false;
        for (Node node : path) {
            if (node == sourceNode) {
                isNodeIsParent = true;
                break;
            }
        }
        if (!isNodeIsParent) {
            path.add(sourceNode);
        }

        for (Edge edge : sourceNode.getPaths()) {
            if (edge.getSourceNode() == destinationNode) {
                solutions.add(path);
            } else {
                findPath(path, edge.getSourceNode(), destinationNode);
            }
        }
        path.removeLast();
    }

    /*
     * Algo Try - 1 Not Wokring for Cyclic Graph
     */
    public static void findMultiplePathUsingStack(Graph G, Node sourceNode, Node destinationNode) {
        System.out.println("Starting finding the solution using STACK");
        LinkedList<Node> path = new LinkedList<Node>();
        findPath(path, sourceNode, destinationNode);
        printSolutions(solutions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Try - 1: My Attemp with the marking of the edges using DFS">
    /*
     * Algo Try - 2 My Attempt to find the paths using the Marking of the edge
     */
    public static void findMultiplePath(Graph G, Node sourceNode, Node destinationNode) {
        System.out.println("Starting finding the solution");

        while (!G.isAllEdgesVisited()) {
            // To check wheather how many adjecent edges are visited.
            int adjecentEdgesVisited = 0;
            // Clearing the last path stroed.
            LinkedList<Node> path = new LinkedList<Node>();
            // Adding source node to first of the path.
            Node currentNode = sourceNode;
            path.add(sourceNode);
            // If cycle is there then it is marked as Visited.
            Edge lastEdge = null;
            Node lastNodeForBackTrack = null;
            while (adjecentEdgesVisited < currentNode.getPaths().size()) {

                // Identify the node is in path or not?
                boolean isNodeIsParent = false;
                for (Node node : path) {
                    if (node == currentNode) {
                        isNodeIsParent = true;
                        lastEdge.setVisited(true);
                        currentNode = lastNodeForBackTrack;
                        adjecentEdgesVisited = 0;
                        break;
                    }
                }
                if (!isNodeIsParent) {
                    path.add(currentNode);
                }

                // Take any one edge which is not visited. 
                if (currentNode.getPaths().isEmpty()) {
                    path.add(currentNode);
                    break;
                }
                for (Edge edge : currentNode.getPaths()) {
                    if (edge.isVisited()) {
                        adjecentEdgesVisited++;
                    } else {
                        currentNode = edge.getDestinationNode();
                        adjecentEdgesVisited = 0;
                        lastEdge = edge;
                        lastNodeForBackTrack = edge.getSourceNode();
                        break;
                    }
                }

            }

            // Now the traversing is done means it is @ last/leaf node in the graph.
            Node lastNode = path.getLast();
            Node secondLastNode = path.get(path.size() - 2);

            lastEdge = G.findEdgeWithStartAndEndNodes(secondLastNode, lastNode);
            lastEdge.setVisited(true);

            // Check Weather the last node is the destination node or not?
            if (lastNode == destinationNode) {
                // Adding the path to the solution list if the last vertex is the destination vertex given.
                solutions.add(new LinkedList<Node>(path));
            }
        }

        printSolutions(solutions);

    }

    private static void printSolutions(ArrayList<LinkedList<Node>> solutions) {
        System.out.println("The available Solutions are: ");
        for (LinkedList<Node> linkedList : solutions) {
            System.out.print("Path: ");
            for (Node node : linkedList) {
                System.out.print(node.getIdentifier() + " - ");
            }
            System.out.println();
        }
    }
    //</editor-fold>

    /*
     * Algo Try - 3 Implementation of the Algorithm given here:
     * http://stackoverflow.com/questions/58306/graph-algorithm-to-find-all-connections-between-two-arbitrary-vertices/58461
     */
    public static void findAllPaths(Graph G, Node sourceNode, Node destinationNode) {
        LinkedList<Node> visitedNodes = new LinkedList<Node>();
        visitedNodes.add(sourceNode);
        BFS(visitedNodes, destinationNode, G);
        printSolutions(solutions);
    }

    private static void BFS(LinkedList<Node> visitedNodes, Node destinationNode, Graph G) {
        LinkedList<Node> nodes = new LinkedList<Node>();

        // Adding Adjecent nodes of the last node of the visitedList.
        ArrayList<Edge> paths = visitedNodes.getLast().getPaths();
        for (Edge edge : paths) {
            nodes.add(edge.getDestinationNode());
        }

        // BFS
        for (Node node : nodes) {
            if (visitedNodes.contains(node)) {
                continue;
            }
            if (node == destinationNode) {
                visitedNodes.add(node);
                // Adding solution to the SOLUTION LIST
                solutions.add(new LinkedList<Node>(visitedNodes));
                visitedNodes.removeLast();
            }
        }

        // recursion needs to come after visiting adjacent nodes
        for (Node node : nodes) {
            if (visitedNodes.contains(node) || (node == destinationNode)) {
                continue;
            }
            visitedNodes.addLast(node);
            BFS(visitedNodes, destinationNode, G);
            visitedNodes.removeLast();
        }
    }
}
