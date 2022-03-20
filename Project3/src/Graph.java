
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Graph {
	HashMap<String, ArrayList<Edge>> nodes; 
	private boolean directed; 
	
	public Graph(boolean directed) {
		nodes = new HashMap<>(); 
		this.directed = directed; 
	}
	
	public void addNode(String node) {
		nodes.putIfAbsent(node, new ArrayList<Edge>()); 
	}
	
	public void addEdge(String src, String dest, int length) {
		addNode(src); 
		nodes.get(src).add(new Edge(src, dest, length)); 			
		addNode(dest);
		if(!directed) {
			nodes.get(dest).add(new Edge(dest, src, length)); 			
		}
	}
	
	public Object[] findShortestPath(String origin, String destination) {
		int shortestPathLength = 0; 
		HashMap<String, Edge> shortestPaths = new HashMap<>(); 		
		PriorityQueue<Edge> edges = new PriorityQueue<>(); 
		
		edges.add(new Edge(origin, origin, 0)); 
		
		while(!edges.isEmpty()) {
			Edge shortestEdge = edges.poll(); 
			
			String cameFrom = shortestEdge.getSource(); 
			String currentCity = shortestEdge.getDestination(); 
			int lengthUpToCurrent = shortestEdge.getLength(); 
			if( shortestPaths.containsKey(currentCity) )	{
				continue; 
			}
			
			shortestPaths.put(currentCity, shortestEdge);
			 
			for(Edge wayFromCurrent : nodes.get(currentCity)) {
				String dest = wayFromCurrent.getDestination(); 
				int length = wayFromCurrent.getLength(); 
				
				if(!shortestPaths.containsKey(dest)) {
					edges.add(new Edge(currentCity, dest, lengthUpToCurrent + length));
				}
			}
			
		}
		
		if(!shortestPaths.containsKey(destination) || shortestPaths.get(destination) == null) {
			shortestPathLength = -1; 
			return new Object[] {shortestPathLength, null}; 
		}
		Edge wayToOrigin = shortestPaths.get(destination);
		shortestPathLength = wayToOrigin.getLength(); 
		
		ArrayList<String> shortestPath = new ArrayList<>();
		shortestPath.add(destination); 
		while(!wayToOrigin.getDestination().equals(origin)) {
			shortestPath.add(wayToOrigin.getSource()); 
			wayToOrigin = shortestPaths.get(wayToOrigin.getSource()); 
		}
		Collections.reverse(shortestPath);
		
		return new Object[] {shortestPathLength, shortestPath}; 
	}
	
	public int findMSTCost(String start) {
		for(String node : nodes.keySet()) {
			if(nodes.get(node).isEmpty()) {
				return -1; 
			}
		}

		PriorityQueue<Edge> edges = new PriorityQueue<>(); 
		edges.add(new Edge(start, start, 0)); 
		
		HashSet<String> visited = new HashSet<>(); 
		int minimumCost = 0; 
		while(!edges.isEmpty()) {
			Edge top = edges.poll(); 
			
			String dest = top.getDestination();
			if(visited.contains(dest)) {
				continue; 
			}
			
			minimumCost += top.getLength(); 
			visited.add(dest); 
			for(Edge edge : nodes.get(dest) ) {
				if ( !visited.contains(edge.getDestination()) ) {
					edges.add(edge); 				
				}
			}
			
		}
		
		return minimumCost; 
	}
}




class Edge implements Comparable<Edge> {
	private String source; 
	private String destination; 
	private int length; 
	
	public Edge(String source, String destination, int length) {
		this.source = source; 
		this.destination = destination; 
		this.length = length; 
	}
	
	public String getSource() {
		return this.source; 
	}
	
	public String getDestination() {
		return this.destination; 
	}
	
	public int getLength() {
		return this.length; 
	}
	
	public String toString() {
		return "source : " + this.getSource() + " destination : " +  this.getDestination() + " length : " + this.getLength();
	}

	@Override
	public int compareTo(Edge edge2) {
		return this.getLength() - edge2.getLength();
	}
}