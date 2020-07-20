// Megan Spiers
// Last Edited: 07/20/2020
// Main File for CS471Project


import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class processScheduling {
	
	
	
	public static int displayMenu(PrintWriter outputFile){
		
        Scanner scnrInput = new Scanner(System.in);
        
        while(true) {
        	
            System.out.println("Choose One of the Orders Below:\n");
            System.out.println("1. FIFO");
            System.out.println("2. SJF W/O Preemption");
            System.out.println("3. Priority W/ Preemption");
            System.out.println("4. Sixty-Six (Exit) \n");
           
            System.out.println("Enter your choice:");
            
            String input = scnrInput.next();
            
            if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
            	
            	switch(input) {
            	
                        case "1":
                        	
                        	outputFile.println("\nOption 1 Test\n");
                            break;
                            
                            
                        case "2":
                        	
                        	System.out.println("\nOption 2 Test\n");
                            break;
                            
                            
                        case "3":
                        	
                        	System.out.println("\nOption 3 Test\n");
                        	break;
                        	
                        	
                        case "4":
                        	
                        	System.out.println("\nGood Soldiers Follow Orders...");
                        	System.out.println("Program Terminated");
                        	
                        	scnrInput.close();
                        	return 0;
                        	
               }
                        
            } 
            
            else {
            	
            	System.out.println("Enter only an Integer (1, 2, 3, or 4)\n");
            	
            }
            
        }
        
	}

	
	public static void main(String[] args) throws IOException {
		
		
	    FileInputStream inputFile = new FileInputStream("RandomInput.txt");
	    PrintWriter outputFile = new PrintWriter("StatisticsfortheRun.txt");

	      
	    Scanner scnr = new Scanner(inputFile);
	    
	    
	    displayMenu(outputFile);
	    
	    
	    scnr.close();
	    inputFile.close();
	    outputFile.close();
	    
	}

}
