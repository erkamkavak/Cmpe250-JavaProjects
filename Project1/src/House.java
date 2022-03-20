

import java.util.ArrayList;

public class House implements Comparable<House>{
	private int id; 
	private int isAvailableAfter; 
	private double rating; 
	
	public House(int id, int isAvailableAfter, double rating) {
		this.setId(id); 
		this.setIsAvailableAfter(isAvailableAfter); 
		this.setRating(rating); 
	}
	
	/**
	 * Gives the house to a student and changes the availability 
	 * of the house. 
	 * @param student : The student which house will be given to. 
	 */
	public void giveToStudent(Student student) {
		setIsAvailableAfter(student.getStayDuration()); 
	}
	
	/**
	 * Processes one semester for the house and if the house becomes 
	 * available adds it to the available houses. 
	 * @param availableHouses : Arraylist of availableHouses to add the house if needed. 
	 */
	public void processTime(ArrayList<House> availableHouses) {
		setIsAvailableAfter(getIsAvailableAfter() - 1); 
		
		if(isAvailable()) {
			// add to available houses
			availableHouses.add(this); 
		}
	}
	
	/**
	 * Checks if the house is available. 
	 * @return true if the house is available. 
	 */
	public boolean isAvailable() {
		return (isAvailableAfter == 0); 
	}
	
	/**
	 * Returns the id of the house. 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the house to the given value. 
	 * @param id : The new id of the house. 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the rating of the house. 
	 * @return rating of the house. 
	 */
	public double getRating() {
		return rating;
	}

	/**
	 * Sets the rating of the house.
	 * @param rating : The new rating of the house.
	 */
	public void setRating(double rating) {
		this.rating = rating;
	}

	/**
	 * Returns the remaining semester that the house will be ready after. 
	 * @return amount of remaining semester that the house will be ready after. 
	 */
	public int getIsAvailableAfter() {
		return isAvailableAfter;
	}

	/**
	 * Sets the remaining semester value that the house will be ready after. 
	 * @param isAvailableAfter : the new remaining semester value
	 */
	public void setIsAvailableAfter(int isAvailableAfter) {
		this.isAvailableAfter = isAvailableAfter;
	}




	/**
	 * Compares the houses based on their id. 
	 */
	@Override
	public int compareTo(House h2) {
		if(this.getId() < h2.getId()) {
			return -1; 
		}
		else {
			return 1; 
		}
	}
	
	
	public String toString() {
		return "id: " + getId() + " rating: " + this.getRating();
	}
	
	
}
