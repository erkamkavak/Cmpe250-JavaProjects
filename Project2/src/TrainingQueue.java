import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TrainingQueue extends Queue {
	
	public TrainingQueue() {
		super(); 
		this.setType(Queue.trainingType); 
		queue = new PriorityQueue<>(comparator); 
	}

	
	
	Comparator<Arrival> comparator = (a1, a2) -> {
		Player player1 = a1.getPlayer(); 
		Player player2 = a2.getPlayer();
		
		// players will be compared based on their arrive time 
		// if same arrive time  -> compared based on id(low id first)
		if( a1.getArriveTime() > a2.getArriveTime() ) {
			return 1;
		}
		else if( a1.getArriveTime() < a2.getArriveTime() ) {
			return -1;
		}
		else {
			if(player1.getId() > player2.getId()) {
				return 1; 
			}
			else {
				return -1; 
			}
		}
		
	};
		
	
}
