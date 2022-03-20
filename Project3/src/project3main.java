
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;

public class project3main {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in = new Scanner(new File(args[0])); 
		PrintStream out = new PrintStream(new File(args[1])); 
		
		int threshold = in.nextInt(); 
		
		int numberOfCities = in.nextInt(); 
		String origin = in.next(); 
		String destination = in.next(); 
		in.nextLine(); 
		
		Graph graph = new Graph(true); 
		Graph graphForHoneymoon = new Graph(false); 
		for(int i = 0; i < numberOfCities; i++) {
			String line = in.nextLine(); 
			String[] parts = line.split(" ");
			String city = parts[0]; 
			
			if(city.startsWith("d") || city.equals(destination)) {
				graphForHoneymoon.addNode(city);
				for(int j = 1; j < parts.length - 1; j+= 2) {
					String dest = parts[j]; 
					int length = Integer.parseInt(parts[j + 1]); 
					if(!dest.startsWith("c")) {
						graphForHoneymoon.addEdge(city, dest, length);
					}
				}
			}
			else {
				graph.addNode(city);
				for(int j = 1; j < parts.length - 1; j += 2) {
					String dest = parts[j]; 
					int length = Integer.parseInt(parts[j + 1]); 
			
					graph.addEdge(city, dest, length);
				}
			}
		}
		
		Object[] obj = graph.findShortestPath(origin, destination); 
		int shortestPathLength = (int) obj[0]; 
		ArrayList<String> shortestPath = (ArrayList<String>) obj[1]; 
		if(shortestPath == null) {
			out.println(-1);
			out.println(-1); 
		}
		else {
			out.println(shortestPath.stream()
					.collect(Collectors.joining(" ")));			
			// they marry
			if(threshold >= shortestPathLength) {
				int cost = graphForHoneymoon.findMSTCost(destination); 
				out.print(cost * 2); 
			}
			// they dont marry
			else {
				out.print(-1); 
			}
		}
		
		
		
	}

}