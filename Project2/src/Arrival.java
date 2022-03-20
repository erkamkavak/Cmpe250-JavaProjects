
public class Arrival implements Comparable<Arrival> {
	private int type; 
	private Player player; 
	private double arriveTime; 
	private double duration;
	
	private double processStartTime; 
	private double trainingTime; 
	
	public Arrival(int type, Player player, double arriveTime, double duration) {
		this.setType(type); 
		this.setPlayer(player); 
		this.arriveTime = arriveTime; 
		this.setDuration(duration); 
	}
	
	public double getTrainingTime() {
		if(getType() == Queue.therapyType) {
			return this.trainingTime; 
		}
		else {
			return -1; 
		}
	}
	
	public void setTrainingTime() {
		if(getType() == Queue.trainingType) {
			this.trainingTime = this.getDuration(); 
		}
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
		
	public double getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(double arriveTime) {
		this.arriveTime = arriveTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public int compareTo(Arrival other) {
		if (this.arriveTime < other.getArriveTime() ) {
			return 1; 
		}
		else {
			return -1; 
		}
	}

	public double getProcessStartTime() {
		return processStartTime;
	}

	public void setProcessStartTime(double processStartTime) {
		this.processStartTime = processStartTime;
	}

	public String toString() {
		return "type :" + this.getType() + 
			" arrive time: " + this.getArriveTime() + 
			" player id: " + this.getPlayer().getId() + 
			" duration : " + this.getDuration(); 
	}

	
}
