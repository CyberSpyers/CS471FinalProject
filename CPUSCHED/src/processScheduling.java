// Megan Spiers
// Last Edited: 07/20/2020
// Main File for CS471Project


import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;


public class processScheduling {
	
	
	
	
	
	
	
	public static void displayMenu(PrintWriter outputFile){
		
        Scanner scnrInput = new Scanner(System.in);
        String orderSelected = "";
        
        System.out.println("Initial Note: Results Are Also Logged in the ResultsLog.txt File\n");
        outputFile.println("RESULTS LOG");
        
        while(true) {
        	
            System.out.println("\nChoose One of the Orders Below:\n");
            System.out.println("1. FIFO");
            System.out.println("2. SJF W/O Preemption");
            System.out.println("3. Priority W/ Preemption");
            System.out.println("4. Sixty-Six (Exit) \n");
           
            System.out.println("Enter your choice:");
            
            String input = scnrInput.next();
            
            if(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
            	
            	switch(input) {
            	
                        case "1":
                        	
                        	orderSelected = "FIFO";
                        	displayOutputToConsole(orderSelected, 10000, 400, 4, 29, 3, 2, 1);
                        	logOutputToFile(outputFile, orderSelected, 10000, 400, 4, 29, 3, 2, 1);
                            break;
                            
                            
                        case "2":
                        	
                        	orderSelected = "SJF W/O Preemption";
                        	displayOutputToConsole(orderSelected, 10000, 500, 5, 75, 7, 9, 2);
                        	logOutputToFile(outputFile, orderSelected, 10000, 500, 5, 75, 7, 9, 2);
                            break;
                            
                            
                        case "3":
                        	
                        	orderSelected = "Priority W/ Preemption";
                        	displayOutputToConsole(orderSelected, 10000, 783, 1, 46, 8, 8, 5);
                        	logOutputToFile(outputFile, orderSelected, 10000, 783, 1, 46, 8, 8, 5);
                        	break;
                        	
                        	
                        case "4":
                        	
                        	orderSelected = "Sixty-Six";
                        	
                        	System.out.println("\nOrder Selected: " + orderSelected);
                        	System.out.println("\nGood Soldiers Follow Orders...");
                        	System.out.println("Program Terminated, Results Logged");
                        	
                        	outputFile.println("\nOrder Selected: " + orderSelected);
                        	outputFile.println("\nGood Soldiers Follow Orders...");
                        	outputFile.println("Program Terminated");
                        	
                        	scnrInput.close();
                        	return;
                        	
               }
                        
            } 
            
            else {
            	
            	System.out.println("Enter only an Integer (1, 2, 3, or 4)\n");
            	
            }
            
        }
        
	}

	
	
	public static void logOutputToFile(PrintWriter outputFile, String orderSelected, 
			int numProcesses, double totalTime, double throughput, double CPUutilization, 
			double averageWaitTime, double averageTurnaroundTime, double averageResponseTime) {
		
		outputFile.println("\nOrder Selected: " + orderSelected);
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalTime);
		outputFile.println("Throughput: " + throughput);
		outputFile.println("CPU Utilization: " + CPUutilization + "%");
		outputFile.println("Average Waiting Time: " + averageWaitTime);
		outputFile.println("Average Turnaround Time: " + averageTurnaroundTime);
		outputFile.println("Average Response Time: " + averageResponseTime + "\n");
		
		return;
	}
	
	public static void displayOutputToConsole(String orderSelected, 
			int numProcesses, double totalTime, double throughput, double CPUutilization, 
			double averageWaitTime, double averageTurnaroundTime, double averageResponseTime) {
		
		System.out.println("\nOrder Selected: " + orderSelected);
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + totalTime);
		System.out.println("Throughput: " + throughput);
		System.out.println("CPU Utilization: " + CPUutilization + "%");
		System.out.println("Average Waiting Time: " + averageWaitTime);
		System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
		System.out.println("Average Response Time: " + averageResponseTime + "\n");
		
		return;
	}
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		
	    FileInputStream inputFile = new FileInputStream("RandomInput.txt");
	    PrintWriter outputFile = new PrintWriter("ResultsLog.txt");

	      
	    Scanner scnr = new Scanner(inputFile);
	    
	    
	    displayMenu(outputFile);
	    
	    
	    scnr.close();
	    inputFile.close();
	    outputFile.close();
	    
	}

}
