import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class DijkstraAlgorithm {
	// This method take a graph and a source node as parameters and return a graph 
	//with the shortest path from source
	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
	    source.setDistance(0);
	 
	    Set<Node> visitedNodes = new HashSet<>();
	    Set<Node> unvisitedNodes = new HashSet<>();
	 
	    unvisitedNodes.add(source);
	 
	    while (unvisitedNodes.size() != 0) {
	        Node currentNode = getLowestDistanceNode(unvisitedNodes);
	        unvisitedNodes.remove(currentNode);
	        for (Entry < Node, Integer> adjacencyPair: 
	        	currentNode.getAdjacentNodes().entrySet()) {
	            Node adjacentNode = adjacencyPair.getKey();
	            Integer edgeWeight = adjacencyPair.getValue();
	            if (!visitedNodes.contains(adjacentNode)) {
	                CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
	                unvisitedNodes.add(adjacentNode);
	            }
	        }
	        visitedNodes.add(currentNode);
	    }
	    return graph;
	}
	
	//This function will take like parameter a set of nodes and will return the node 
	//with the lowest distance from from  the unvisited nodes set
	private static Node getLowestDistanceNode(Set < Node > unsettledNodes) {
	    Node lowestDistanceNode = null;
	    int lowestDistance = Integer.MAX_VALUE;
	    for (Node node: unsettledNodes) {
	        int nodeDistance = node.getDistance();
	        if (nodeDistance < lowestDistance) {
	            lowestDistance = nodeDistance;
	            lowestDistanceNode = node;
	        }
	    }
	    return lowestDistanceNode;
	}
	
	//This method will take as parameter a node, a edge weight and a source node 
	//It will compare the actual distance with  the newly calculated and will reset the 
	//shortest path in the passed node
	private static void CalculateMinimumDistance(Node evaluationNode,
			  Integer edgeWeigh, Node sourceNode) {
			    Integer sourceDistance = sourceNode.getDistance();
			    if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
			        evaluationNode.setDistance(sourceDistance + edgeWeigh);
			        LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.
			        		getShortestPath());
			        shortestPath.add(sourceNode);
			        evaluationNode.setShortestPath(shortestPath);
			    }
			}
	
	public static void main(String args[]) {
Node nodeA = new Node("A");
Node nodeB = new Node("B");
Node nodeC = new Node("C");
Node nodeD = new Node("D"); 
Node nodeE = new Node("E");
Node nodeF = new Node("F");
 
nodeA.addDestination(nodeB, 10);
nodeA.addDestination(nodeC, 15);
 
nodeB.addDestination(nodeD, 12);
nodeB.addDestination(nodeF, 15);
 
nodeC.addDestination(nodeE, 10);
 
nodeD.addDestination(nodeE, 2);
nodeD.addDestination(nodeF, 1);
 
nodeF.addDestination(nodeE, 5);
 
Graph graph = new Graph();
 
graph.addNode(nodeA);
graph.addNode(nodeB);
graph.addNode(nodeC);
graph.addNode(nodeD);
graph.addNode(nodeE);
graph.addNode(nodeF);
 
graph = DijkstraAlgorithm.calculateShortestPathFromSource(graph, nodeA);
System.out.println("The order is: "+ graph.getNodes());
System.out.println("The order is: "+ graph.getNodes().getClass().getName());
		
    }
}
 