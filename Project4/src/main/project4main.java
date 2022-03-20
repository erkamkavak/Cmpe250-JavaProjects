import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

public class project4main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        PrintStream out = new PrintStream(new File(args[1]));

        ArrayList<Vehicle> vehicles = new ArrayList<>();
        int trainToGreen = in.nextInt();
        int totalCapacity = 0; 
        for (int i = 0; i < trainToGreen; i++) {
            int capacity = in.nextInt();
            totalCapacity += capacity; 
            // vehicles.add(new Vehicle(capacity, Vehicle.TRAIN_TYPE, Region.GREEN));
        }
        vehicles.add(new Vehicle(totalCapacity, trainToGreen, Vehicle.TRAIN_TYPE, Region.GREEN) );

        int trainToRed = in.nextInt();
        totalCapacity = 0; 
        for (int i = 0; i < trainToRed; i++) {
            int capacity = in.nextInt();
            totalCapacity += capacity; 
        }
        vehicles.add(new Vehicle(totalCapacity, trainToRed, Vehicle.TRAIN_TYPE, Region.RED));

        int reindeerToGreen = in.nextInt();
        totalCapacity = 0; 
        for (int i = 0; i < reindeerToGreen; i++) {
            int capacity = in.nextInt();
            totalCapacity += capacity; 
        }
        vehicles.add(new Vehicle(totalCapacity, reindeerToGreen, Vehicle.REINDEER_TYPE, Region.GREEN));


        int reindeerToRed = in.nextInt();
        totalCapacity = 0; 
        for (int i = 0; i < reindeerToRed; i++) {
            int capacity = in.nextInt();
            totalCapacity += capacity; 
        }
        vehicles.add(new Vehicle(totalCapacity, reindeerToRed, Vehicle.REINDEER_TYPE, Region.RED));

        int numberOfBags = in.nextInt();
        int totalGifts = 0;
        HashMap<String, Bag> bags = new HashMap<>();

        for (int i = 0; i < numberOfBags; i++) {
            String type = in.next();
            int numberOfGifts = in.nextInt();
            totalGifts += numberOfGifts;
            if(!bags.containsKey(type)) {
                bags.put(type, new Bag(type, numberOfGifts));
            }
            else if(bags.containsKey(type) && type.contains("a")) {
                bags.put(type + Integer.toString(i), new Bag(type, numberOfGifts));
            }
            else{
                bags.get(type).increaseNumberOfGifts(numberOfGifts);
            }
        }

        int V = vehicles.size() + numberOfBags + 2;
        Graph graph = new Graph(V);
        for(Vehicle v : vehicles) {
            graph.addVehicle(v);
        }
        for(Bag b : bags.values()) {
            graph.addBag(b);
        }


        out.println(totalGifts - graph.maxFlow());
    }

    void singleCase(int n) throws FileNotFoundException{
		Scanner in = new Scanner(new File("testing/test_cases/inputs/input_" + n + ".txt"));
		PrintStream out = new PrintStream(new File("./input.txt"));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			out.println(line);
		}
		
		long start = System.currentTimeMillis(); 
		project4main.main(new String[] {"./input.txt", "./my_output.txt"});
		long end = System.currentTimeMillis(); 
		System.out.println("Testcase " + n + ": " + ((end - start)/1000.0) ); 
		
		in = new Scanner(new File("./my_output.txt"));
		Scanner inCorrect = new Scanner(new File("testing/test_cases/outputs/output_" + n + ".txt"));
		int lineNumber = 1;
		while(in.hasNextLine() && inCorrect.hasNextLine()) {
			String line1 = in.nextLine();
			String line2 = inCorrect.nextLine();
			if(!line1.equals(line2)) {
				System.out.println("Wrong answer on case " + n + ", line " + lineNumber);
			}
			lineNumber++;
		}
		
		if(in.hasNextLine() || inCorrect.hasNextLine()) {
			System.out.println("Wrong answer on case " + n +", number of lines do not match");
		}
		
		
	}
	
	@Test
	void test() throws FileNotFoundException {
		for(int i = 0; i <= 9; i++) {
			singleCase(i);
		}
	}


}
