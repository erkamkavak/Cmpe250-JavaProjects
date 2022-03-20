import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TherapyQueue extends Queue {		
	public static int mostWaitedPlayerId; 
	public static double mostWaitedTime = 0; 
	
	public TherapyQueue() {
		super(); 
		this.setType(Queue.therapyType); 
		queue = new PriorityQueue<>(comparator); 
	}
	
	public Arrival processTime(double currentTime) {
		Arrival removedArrival = super.processTime(currentTime); 
		if(removedArrival != null) {
			double totalWaitedTime = removedArrival.getPlayer().getTotalWaitedTime(Queue.therapyType); 
			if(totalWaitedTime > mostWaitedTime) {
				mostWaitedTime = totalWaitedTime; 
				mostWaitedPlayerId = removedArrival.getPlayer().getId(); 
			}
			else if(totalWaitedTime == mostWaitedTime) {
				mostWaitedPlayerId = Math.min(mostWaitedPlayerId, removedArrival.getPlayer().getId());
			}
		}

		
		return removedArrival;
	}
	
	public void addArrivalToQueue(Arrival arrival, double currentTime) {
		arrival.setTrainingTime(); 
		arrival.setType(Queue.therapyType); 
		arrival.setArriveTime(currentTime); 
		super.addArrivalToQueue(arrival);
	}
	
	

	Comparator<Arrival> comparator = (a1, a2) -> {
		// players will be compared based on their training time
		// if same training time  -> compared based on arrive time
		// if same arrive time -> compared based on id(low id first)	
		if(a1.getTrainingTime() < a2.getTrainingTime() ) {
			return 1; 
		}
		else if(a1.getTrainingTime() > a2.getTrainingTime()) {
			return -1; 
		}
		else {
			if(a1.getArriveTime() > a2.getArriveTime()) {
				return 1; 
			}
			else if(a1.getArriveTime() < a2.getArriveTime()) {
				return -1; 
			}
			else {
				Player p1 = a1.getPlayer(); 
				Player p2 = a2.getPlayer(); 
				if(p1.getId() > p2.getId() ) {
					return 1; 
				}
				else {
					return -1; 
				}
			}
		}
	};
	
}
