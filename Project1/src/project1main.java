
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class project1main {
	
	public static void main(String[] args) throws FileNotFoundException {		
		Scanner in = new Scanner(new File(args[0])); 
		PrintStream out = new PrintStream(new File(args[1])); 
		
		// longest duration is the duration of the student that will stay for longest.
		int longestDuration = 0; 
		ArrayList<Student> allStudents = new ArrayList<>(); 
		ArrayList<Student> availableStudents = new ArrayList<>(); 
		
		ArrayList<House> allHouses = new ArrayList<>(); 
		ArrayList<House> availableHouses = new ArrayList<>(); 
		
		while(in.hasNextLine()) {
			String type; 
			try {
				type = in.next(); 
			} 
			catch(Exception NoSuchElementException) {
				break; 
			}
			
			// if it is a house
			if(type.equals("h")) {
				int id = in.nextInt(); 
				int duration = in.nextInt(); 
				double rating = in.nextDouble(); 
				House house = new House(id, duration, rating); 
				// If the house is available(no student in it), adds it to available houses 
				if(house.isAvailable() ) {
					availableHouses.add(house); 
				}
				allHouses.add(house); 
			}
			// if it is a student
			else if(type.equals("s")) {
				int id = in.nextInt(); 
				String name = in.next(); 
				int duration = in.nextInt(); 
				double rating = in.nextDouble(); 
				Student student = new Student(id, name, duration, rating); 
				
				longestDuration = Math.max(longestDuration, duration); 
				allStudents.add(student); 
				// if the student is not graduated, adds it to the available houses. 
				if(student.getStayDuration() != 0) {
					availableStudents.add(student); 
				}
			}
			
		}
		
		// We dont need to sort students for every semester because 
		// there is no student coming in new semesters. 
		Collections.sort(availableStudents); 
		for(int i= 0; i < longestDuration; i++) {
			// We need to sort houses for every semester because 
			// new available houses coming every semester
			Collections.sort(availableHouses); 
			
			Iterator<House> houseItr = availableHouses.iterator(); 
			while(houseItr.hasNext()) {
				House house = houseItr.next(); 
				Iterator<Student> studentItr = availableStudents.iterator(); 
				while(studentItr.hasNext()) {
					Student student = studentItr.next(); 
					if(student.getRatingThreshold() <= house.getRating()) {
						house.giveToStudent(student); 
						student.allocate(studentItr); 
						break;
					}
				}
			}

			// If house is given to a student, we should delete it from 
			// available houses. if it is not given to anyone, that means
			// rating of this house is not enough for any student and because
			// there wont be any student coming, we will never give this 
			// house to anyone so we can erase this house from available houses. 
			// so at the end of the iteration, we should clear 
			// the available houses list. 
			availableHouses.clear();
			
			
			Iterator<Student> studentItr = availableStudents.iterator(); 
			while(studentItr.hasNext()) {
				Student student = studentItr.next();
				student.processTime(studentItr);	
			}
			allHouses.forEach(house -> house.processTime(availableHouses)); 
		}
		
		// We need to sort the students to write them in increasing id
		Collections.sort(allStudents); 
		for(Student student: allStudents) {
			if( !student.isAllocatedBefore()) {
				out.println(student.getName()); 
			}
		}
	}
}
