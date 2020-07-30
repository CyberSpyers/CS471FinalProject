// Megan Spiers
// Last Edited: 07/19/2020
// For use with CS471Project Files


// Necessary import for PrintStream
import java.io.*;
  

// Begin Class
public class RandomInputGenerator { 
	
	
	// Creates method to return a random integer between the minimum and maximum
	public static int getRandomInteger(int minimum, int maximum) {
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
    
	
	// Begin main method
	public static void main(String arr[]) throws FileNotFoundException { 
		
		
        // Creating a newFile object that represents the file to be created
        PrintStream newFile = new PrintStream(new File("RandomInput.txt")); 
  
         
        // Store current System.out before assigning a new value 
        PrintStream console = System.out; 
  
        
        // Assign newFile to output stream 
        System.setOut(newFile); 
        
        
        // Create necessary variables
        int arrivalTime;
        int priority;
        int CPUBurstUnits;
        
        
        // Create helpful constants
        final int MAX_PROCESSES = 10000;
        final int MIN_ARRIVAL_TIME = 1;
        final int MAX_ARRIVAL_TIME = 100;
        final int MIN_PRIORITY = 1;
        final int MAX_PRIORITY = 10;
        final int MIN_CPU_BURST_UNITS = 2;
        final int MAX_CPU_BURST_UNITS = 30;
        
        // Populate the newFile, RandomInput.txt, with 10000 processes
        for (int processID = 1; processID <= MAX_PROCESSES; processID++) {
        	
        	
        	// Assigns a random int within the bounds to each variable
        	arrivalTime = getRandomInteger(MIN_ARRIVAL_TIME,MAX_ARRIVAL_TIME);
        	priority = getRandomInteger(MIN_PRIORITY,MAX_PRIORITY);
        	CPUBurstUnits = getRandomInteger(MIN_CPU_BURST_UNITS,MAX_CPU_BURST_UNITS);
        	
        	
        	// prints each line to the file
        	System.out.println(processID + " "
        			+ arrivalTime + " " + priority + " "
        			+ CPUBurstUnits);
        }
        
        
        // Set setOut value for output stream back to console
        System.setOut(console); 

       
    // End main method
    } 
	

// End class
} 


// End Program