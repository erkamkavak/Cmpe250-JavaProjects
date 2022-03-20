import java.util.ArrayList;

public class Staff implements Comparable<Staff> {
	private int id; 
	private int type; 
	private Arrival processingArrival; 
	
	// only for therapy staff 
	private double serviceTime; 
	
	
	public Staff(int id, int type) {
		this.setId(id); 
		this.setType(type); 
	}

	/**
	 * Returns the next process time (the time where the processing arrival
	 * ends) of the staff 
	 * @return
	 */
	public double getNextProcessTime() {
		if (processingArrival != null) {
			return processingArrival.getProcessStartTime() 
					+ processingArrival.getDuration();
		}
		else {
			return Double.MAX_VALUE;
		}
		
	}
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Arrival getProcessingArrival() {
		return processingArrival;
	}

	public void setProcessingArrival(Arrival processingArrival) {
		if(processingArrival != null && this.getType() == Queue.therapyType) {
			processingArrival.setDuration(this.getServiceTime());
		}
		
		this.processingArrival = processingArrival;	
	}




	@Override
	public int compareTo(Staff s2) {		
		if (this.getNextProcessTime() > s2.getNextProcessTime()) {
			return 1;
		}
		else if(this.getNextProcessTime() < s2.getNextProcessTime()){
			return -1;
		}
		else {
			if(this.getId() < s2.getId()) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
	}
	
	
}
