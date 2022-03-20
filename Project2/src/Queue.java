import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Queue implements Comparable<Queue> {
	public static int trainingType = 0;
	public static int therapyType = 1; 
	public static int massageType = 2; 
	
	public static int cancelledAttempts = 0; 
	
	
	private int maximumLength = 0; 
	private double totalProcessTime = 0; 
	private double totalWaitedTime = 0; 
	private int totalPlayer = 0; 
	
	private int type; 
	protected PriorityQueue<Arrival> queue;
	protected ArrayList<Staff> staffs = new ArrayList<Staff>(); 
	
	
	
	public Queue() {

	}
	
	public Staff getAvailableStaff() {
		if(staffs.get(staffs.size() - 1).getNextProcessTime() == Double.MAX_VALUE) {
			return staffs.get(staffs.size() - 1); 
		}
		else {
			return null;
		}
	}
	
	public Staff getFirstAvailableStaff() {
		return staffs.get(0); 
	}
	
	public double getNextProcessTime() {
		return getFirstAvailableStaff().getNextProcessTime();
	}

		
	public Arrival processTime(double currentTime) {
		double nextProcessTime = this.getNextProcessTime(); 
		
		if ( currentTime == nextProcessTime ) {
			Staff processingStaff = this.getFirstAvailableStaff();
			Arrival arrival = processingStaff.getProcessingArrival(); 
			processingStaff.setProcessingArrival(null);
			// arrange staffs arraylist
			Collections.sort(staffs); 
			
			arrival.getPlayer().removeFromQueue(); 
			
			double waitedTime = arrival.getProcessStartTime() - arrival.getArriveTime(); 
			totalProcessTime += arrival.getDuration();
			totalWaitedTime += waitedTime; 
			arrival.getPlayer().addTotalWaitedTime(this.getType(), waitedTime); 
			
			if( !queue.isEmpty() ) {
				Arrival nextArrival = queue.poll(); 
				Staff availableStaff = this.getAvailableStaff(); 
				availableStaff.setProcessingArrival(nextArrival);
				nextArrival.setProcessStartTime(currentTime); 
				
				// arrange staffs arraylist
				Collections.sort(staffs); 
			}
			
			return arrival; 
		}
		else {
			return null; 
		}
		
	}
	
	
	public void addArrivalToQueue(Arrival arrival) {		
		Player p = arrival.getPlayer(); 
		if(!p.addToQueue(this.getType())) {
			return; 
		}
		

		if(getAvailableStaff() != null) {
			getAvailableStaff().setProcessingArrival(arrival); 
			arrival.setProcessStartTime(arrival.getArriveTime()); 
			// arrange staffs arraylist
			Collections.sort(staffs); 

		}
		else {
			queue.add(arrival); 
			int currentPlayersWaiting = queue.size(); 
			maximumLength = Math.max(maximumLength, currentPlayersWaiting);			
		}
		totalPlayer += 1; 
	}
	
	
	public int getMaximumLength() {
		return maximumLength; 
	}
	
	public double getAverageWaitingTime() {	
		if(this.totalPlayer != 0) {
			return this.totalWaitedTime / this.totalPlayer;
		}
		return 0; 
	}
	
	public double getAverageProcessTime() {
		if(this.totalPlayer != 0) {
			return this.totalProcessTime / this.totalPlayer;
		}
		return 0; 
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public void addNewStaff(Staff staff){
		staffs.add(staff); 
	}
	
	
	@Override
	public int compareTo(Queue q2) {
		if(this.getNextProcessTime() > q2.getNextProcessTime()) {
			return 1; 
		}
		else {
			return -1; 
		}
	}
	
	public String toString() {
		return "type :" + this.getType() + " " + 
				"size :" + this.queue.size();
	}
}


