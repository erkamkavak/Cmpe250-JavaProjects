// import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

// import org.junit.jupiter.api.Test;

class MyJunitTest {
	void singleCase(int n) throws FileNotFoundException{
		Scanner in = new Scanner(new File("testing/test_cases/inputs/input_" + n + ".txt"));
		PrintStream out = new PrintStream(new File("./input.txt"));
		while(in.hasNextLine()) {
			String line = in.nextLine();
			out.println(line);
		}
		
		long start = System.currentTimeMillis(); 
		project4main.main(new String[] {"./input.txt", "./my_output.txt"});
		long end = System.currentTimeMillis(); 
		System.out.println("Testcase " + n + ": " + ((end - start)/1000.0) ); 
		
		in = new Scanner(new File("./my_output.txt"));
		Scanner inCorrect = new Scanner(new File("testing/test_cases/outputs/output_" + n + ".txt"));
		int lineNumber = 1;
		while(in.hasNextLine() && inCorrect.hasNextLine()) {
			String line1 = in.nextLine();
			String line2 = inCorrect.nextLine();
			if(!line1.equals(line2)) {
				System.out.println("Wrong answer on case " + n + ", line " + lineNumber);
			}
			lineNumber++;
		}
		
		if(in.hasNextLine() || inCorrect.hasNextLine()) {
			System.out.println("Wrong answer on case " + n +", number of lines do not match");
		}
		
		
	}
	
	@Test
	void test() throws FileNotFoundException {
		for(int i = 0; i <= 9; i++) {
			singleCase(i);
		}
	}
}
