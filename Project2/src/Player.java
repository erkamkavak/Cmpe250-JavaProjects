
public class Player {
	private int id; 
	private int skillLevel;
		
	private boolean isOnProcess;
	private double[] totalWaitedTimes = new double[] {0, 0, 0}; 
	private int massageAmount; 
	
	public Player(int id, int skillLevel) {
		this.id = id; 
		this.skillLevel = skillLevel; 
	
		this.isOnProcess = false; 
	}
	
	/**
	 * Returns if the player is on a queue or a process. 
	 * @return
	 */
	public boolean isOnProcess() {
		return isOnProcess; 
	}
	
	/**
	 * Checks if the player can be added to the queue and if it can,
	 * adds the player to the queue which the type is given as a parameter. 
	 * @param queueType -> The queue type to add the player to
	 * @return true if the player is added to queue false if it isn't 
	 */
	public boolean addToQueue(int queueType) {		
		if(queueType == Queue.massageType && !canTakeMassage() ) {
			MassageQueue.invalidAttempts += 1; 
			return false; 
		}
		
		if(isOnProcess()) {
			Queue.cancelledAttempts += 1; 
			return false; 
		}
		
		
		if(queueType == Queue.massageType) {
			massageAmount += 1; 
		}

		
		this.isOnProcess = true; 
		return true; 
	}
	
	public int getMassageAmount() {
		return massageAmount;
	}

	public void setMassageAmount(int massageAmount) {
		this.massageAmount = massageAmount;
	}

	/** 
	 * Removes the player from queue.
	 */
	public void removeFromQueue() {
		this.isOnProcess = false; 
	}
	
	/**
	 * Checks if the player can take any more massages and returns true if
	 * it can. 
	 * @return
	 */
	public boolean canTakeMassage() {
		return (massageAmount < 3); 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(int skillLevel) {
		this.skillLevel = skillLevel;
	}

	public double getTotalWaitedTime(int type) {
		return totalWaitedTimes[type];
	}

	public void addTotalWaitedTime(int type, double waitedTime) {
		this.totalWaitedTimes[type] += waitedTime; 
	}
	
	
}
