

import java.util.Iterator;

public class Student implements Comparable<Student> {
	private int id; 
	private String name; 
	private int stayDuration; 
	private double ratingThreshold;
	
	/**
	 * Keeps if the student is ever allocated to any house before. 
	 */
	private boolean isAllocatedBefore; 
	
	public Student(int id, String name, int stayDuration, double ratingThreshold) {
		this.id = id; 
		this.name = name; 
		this.stayDuration = stayDuration; 
		this.ratingThreshold = ratingThreshold; 
	}
	
	/**
	 * Checks if the student is ever allocated to any house before. 
	 * @return true if the student is allocated before
	 */
	public boolean isAllocatedBefore() {
		return isAllocatedBefore; 
	}
	
	/**
	 * Allocates the student to a house and erases it from the available 
	 * student iterator. 
	 * @param studentItr : The iterator of available students to erase the allocated student from it
	 */
	public void allocate(Iterator<Student> studentItr) {
		isAllocatedBefore = true; 
		//remove from available students
		studentItr.remove(); 
	}
	
	/**
	 * Processes one semester for the student. Erases the student from available students
	 * if the stay duration of the student is 0. 
	 * @param availableStudents : The iterator of available students to erase the student if needed.
	 */
	public void processTime(Iterator<Student> availableStudents) {
		setStayDuration(getStayDuration() - 1);
		
		if(getStayDuration() == 0){
			availableStudents.remove(); 
		}
	}
	
	/**
	 * Returns the id of the student.
	 * @return id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id of the student.
	 * @param id : new id of the student. 
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Returns the name of the student.
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the student. 
	 * @param name : new name of the student. 
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Returns the remaining semesters that the student will stay. 
	 * @return remaining duration time for the student.
	 */
	public int getStayDuration() {
		return stayDuration;
	}
	/**
	 * Sets the remaining semesters that the student will stay. 
	 * @param stayDuration : The new remaining semester value 
	 */
	public void setStayDuration(int stayDuration) {
		this.stayDuration = stayDuration;
	}
	/**
	 * Returns the minimum rating of the house that the student wants to stay in. 
	 *
	 * @return rating threshold for the student.
	 */
	public double getRatingThreshold() {
		return ratingThreshold;
	}
	
	/**
	 * Sets the rating threshold of the student. 
	 * @param ratingThreshold : new rating threshold.
	 */
	public void setRatingThreshold(int ratingThreshold) {
		this.ratingThreshold = ratingThreshold;
	}


	/**
	 * Compares the students based on their id. 
	 */
	@Override
	public int compareTo(Student s2) {
		if(this.getId() < s2.getId()) {
			return -1; 
		}
		else {
			return 1; 
		}
	}
	
	public String toString() {
		return "id: " + getId() + " name: " + getName() + " ratingThreshold: " + getRatingThreshold()
		+ " duration: " + getStayDuration();
	}
	
}
