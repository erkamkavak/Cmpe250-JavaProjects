import java.util.Comparator;
import java.util.PriorityQueue;

public class MassageQueue extends Queue {
	public static int invalidAttempts = 0; 
	
	public static int leastWaitedPlayerId = -1; 
	public static double leastWaitedTime = -1; 
	
	
	public MassageQueue() {
		super(); 
		
		this.setType(Queue.massageType); 
		queue = new PriorityQueue<>(comparator); 
	}
	
	public Arrival processTime(double currentTime) {
		Arrival removedArrival = super.processTime(currentTime); 
		
		if(removedArrival != null && removedArrival.getPlayer().getMassageAmount() == 3) {
			double totalWaitedTime = removedArrival.getPlayer().getTotalWaitedTime(Queue.massageType); 
			
			if(leastWaitedTime == -1 || totalWaitedTime < leastWaitedTime) {
				leastWaitedTime = totalWaitedTime; 
				leastWaitedPlayerId = removedArrival.getPlayer().getId(); 
			}
			else if(leastWaitedTime == totalWaitedTime) {
				leastWaitedPlayerId = Math.min(leastWaitedPlayerId, removedArrival.getPlayer().getId());
			}
			
		}
		
		return removedArrival; 
	}
	
	
	Comparator<Arrival> comparator = (a1, a2) -> {
		// players will be compared based on their skill level
		// if same skill level -> compared based on arrive time
		// if same arrive time -> compared based on id(low id first)
		Player p1 = a1.getPlayer(); 
		Player p2 = a2.getPlayer(); 
		if(p1.getSkillLevel() < p2.getSkillLevel() ) {
			return 1; 
		}
		else if (p1.getSkillLevel() > p2.getSkillLevel() ){
			return -1; 
		}
		else {
			if(a1.getArriveTime() > a2.getArriveTime() ) {
				return 1; 
			}
			else if(a1.getArriveTime() < a2.getArriveTime() ) {
				return -1;
			}
			else {
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
