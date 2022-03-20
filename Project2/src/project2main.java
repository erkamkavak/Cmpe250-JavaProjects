import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class project2main {

	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0])); 
		PrintStream out = new PrintStream(new File(args[1])); 
	
		int n = in.nextInt(); 
		ArrayList<Player> players = new ArrayList<>(); 
		for(int i = 0; i < n; i++) {
			int id = in.nextInt(); 
			int skillLevel = in.nextInt(); 
			
			// Players will be given as their ids are ordered
			Player player = new Player(id, skillLevel); 
			players.add(player); 
		}
		
		int a = in.nextInt(); 
		ArrayList<Arrival> arrivals = new ArrayList<>(); 
		for(int i = 0; i < a; i++) {
			String typeAsString = in.next(); 
			int type; 
			if(typeAsString.equals("t")) {
				type = Queue.trainingType; 
			}
			else {
				type = Queue.massageType; 
			}
			
			int id = in.nextInt(); 
			Player player = players.get(id); 
			double arrivalTime = in.nextDouble(); 
			double duration = in.nextDouble();
			
			Arrival arrival = new Arrival(type, player, arrivalTime, duration); 
			arrivals.add(arrival);  
		}
		Collections.sort(arrivals); 
		

		// all queues should be sorted by their next process time 
		ArrayList<Queue> allQueues = new ArrayList<>();

		
		int numOfTherapist = in.nextInt();
		TherapyQueue therapyQueue = new TherapyQueue(); 		
		for(int id = 0; id < numOfTherapist; id++) {
			double serviceTime = in.nextDouble(); 
			Staff staff = new Staff(id, Queue.therapyType); 
			staff.setServiceTime(serviceTime); 
			
			therapyQueue.addNewStaff(staff);	
		}
		Collections.sort(therapyQueue.staffs);
		allQueues.add(therapyQueue); 
		
		int numOfCoach = in.nextInt(); 
		TrainingQueue trainingQueue = new TrainingQueue(); 
		for(int id = 0; id < numOfCoach; id++) {
			Staff staff = new Staff(id, Queue.trainingType); 
			trainingQueue.addNewStaff(staff);
		}
		Collections.sort(trainingQueue.staffs);
		allQueues.add(trainingQueue); 
		
		int numOfMasseur = in.nextInt(); 
		MassageQueue massageQueue = new MassageQueue();
		for(int id = 0; id < numOfMasseur; id++) {
			Staff staff = new Staff(id, Queue.massageType); 
			massageQueue.addNewStaff(staff); 
		}		
		Collections.sort(massageQueue.staffs);
		allQueues.add(massageQueue); 
		
		
		double currentTime = 0; 
		// every time we should set the time to the minimum of
		// (next arrival time, minimum next process time of any queue) 
		
		
		while(true) {
			Collections.sort(allQueues, new Comparator<Queue>() {
				@Override 
				public int compare(Queue q1, Queue q2) {
					if(q1.getNextProcessTime() > q2.getNextProcessTime()) {
						return 1; 
					}
					else {
						return -1; 
					}
				}
			});
			
			Queue nextQueue = allQueues.get(0); 
			
			if(arrivals.size() == 0 && nextQueue.getNextProcessTime() == Double.MAX_VALUE) {
				break; 
			}

			if(arrivals.size() != 0) {
				Arrival nextArrival = arrivals.get(arrivals.size() - 1); 
				// process a queue
				if(nextQueue.getNextProcessTime() < nextArrival.getArriveTime()) {
					currentTime = nextQueue.getNextProcessTime(); 
				}
				// add an arrival
				else {
					arrivals.remove(arrivals.size() - 1); 

					currentTime = nextArrival.getArriveTime(); 
					if(nextArrival.getType() == Queue.trainingType) {
						trainingQueue.addArrivalToQueue(nextArrival);
					}
					else {
						massageQueue.addArrivalToQueue(nextArrival);						
					}
				}
			}
			else {
				currentTime = nextQueue.getNextProcessTime(); 
			}
			
			for(int i = 0; i < allQueues.size(); i++) {
				Queue queue = allQueues.get(i); 
				
				Arrival arrival = queue.processTime(currentTime); 
				// if the queue that we process is a training queue and if
				// this queue removes a player we should put that player in 
				// a therapy queue 
				if(arrival != null && queue.getType() == Queue.trainingType) {					
					therapyQueue.addArrivalToQueue(arrival, currentTime);
				}
				else if(arrival == null) {
					break; 
				}
			}

		}
		
		out.println(trainingQueue.getMaximumLength() ); 
		out.println(therapyQueue.getMaximumLength() ); 
		out.println(massageQueue.getMaximumLength() ); 
		
		out.println(String.format("%.3f", trainingQueue.getAverageWaitingTime() )); 
		out.println(String.format("%.3f", therapyQueue.getAverageWaitingTime() )); 
		out.println(String.format("%.3f", massageQueue.getAverageWaitingTime() )); 		
		
		out.println(String.format("%.3f", trainingQueue.getAverageProcessTime() )); 
		out.println(String.format("%.3f", therapyQueue.getAverageProcessTime() )); 
		out.println(String.format("%.3f", massageQueue.getAverageProcessTime() )); 		
		
		
		// average turnaround time -> average waiting times in training and therapy + 
		// average processing times in training and therapy
		out.println(String.format("%.3f", trainingQueue.getAverageWaitingTime() + 
				trainingQueue.getAverageProcessTime() + 
				therapyQueue.getAverageWaitingTime() + 
				therapyQueue.getAverageProcessTime() )
		);
		
		out.println(TherapyQueue.mostWaitedPlayerId + " " + String.format("%.3f", TherapyQueue.mostWaitedTime) ); 
		out.println(MassageQueue.leastWaitedPlayerId + " " + (massageQueue.leastWaitedTime == -1 ? "-1" : String.format("%.3f", 	massageQueue.leastWaitedTime) )); 	
		
		out.println(MassageQueue.invalidAttempts); 
		out.println(Queue.cancelledAttempts); 
		out.println(String.format("%.3f", currentTime) ); 
	}
}

