// Megan Spiers
// Last Edited: 07/21/2020
// Main File for CS471Project


import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class processScheduling {
	
	final static int numProcesses = 10000;
	
	public static void FIFO(FileInputStream inputFile, PrintWriter outputFile) {
		
		Scanner scnrInput = new Scanner(inputFile);
		int[] processIDs = new int[numProcesses];
		int[] arrivalTimes = new int[numProcesses];
		int[] priorities = new int[numProcesses];
		int[] CPUBurstUnits = new int[numProcesses];
		
		int throughput = 0;
		
		
		for (int i = 0; i < numProcesses; i++) {
			processIDs[i] = scnrInput.nextInt();
			arrivalTimes[i] = scnrInput.nextInt();
			priorities[i] = scnrInput.nextInt();
			CPUBurstUnits[i] = scnrInput.nextInt();
		}
		
	/////////////////////////////////////////////////////////////////////////////////////////
		
		long startElapsedTime = System.currentTimeMillis();
		
		
		
		int service_time[] = new int[numProcesses];  
		service_time[0] = 0;  
		int waitTimes[] = new int[numProcesses];  
		waitTimes[0] = 0;
		int turnAroundTimes[] = new int[numProcesses];
		turnAroundTimes[0] = 0;
		  
		    // calculating waiting time  
		    for (int i = 1; i < numProcesses ; i++)  
		    {  
		        // Add burst time of previous processes  
		        service_time[i] = service_time[i-1] + CPUBurstUnits[i-1];  
		  
		        // Find waiting time for current process =  
		        // sum - at[i]  
		        waitTimes[i] = service_time[i] - arrivalTimes[i];  
		  
		        // If waiting time for a process is in negative  
		        // that means it is already in the ready queue  
		        // before CPU becomes idle so its waiting time is 0  
		        if (waitTimes[i] < 0)  
		            waitTimes[i] = 0;  
		    }  
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		long endElapsedTime = System.currentTimeMillis();
		float totalElapsedTime = (endElapsedTime - startElapsedTime);
		
		int sum = 0;
		for (int i = 0; i <= numProcesses; i++) {
			sum = sum + CPUBurstUnits[i];
		}
		throughput = sum / numProcesses;
	/////////////////////////////////////////////////////////////////////////////////////////	
		
		outputFile.println("\nOrder Selected: FIFO");
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime);
		outputFile.println("Throughput: " + throughput);
		outputFile.println("CPU Utilization: " + CPUutilization + "%");
		outputFile.println("Average Waiting Time: " + averageWaitTime);
		outputFile.println("Average Turnaround Time: " + averageTurnaroundTime);
		outputFile.println("Average Response Time: " + averageResponseTime + "\n");
		
		
		System.out.println("\nOrder Selected: FIFO");
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime);
		System.out.println("Throughput: " + throughput);
		System.out.println("CPU Utilization: " + CPUutilization + "%");
		System.out.println("Average Waiting Time: " + averageWaitTime);
		System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
		System.out.println("Average Response Time: " + averageResponseTime + "\n");
			
	//////////////////////////////////////////////////////////////////////////////////////////	
		scnrInput.close();
	}
	
	
	
	
	
	
	
	public static void displayMenu(FileInputStream inputFile, PrintWriter outputFile){
		
        Scanner scnrInput = new Scanner(System.in);
        
        
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
                        	
                        	
                        	FIFO(inputFile, outputFile);
                            break;
                            
                            
                        case "2":
                        	
                        	
                        	
                            break;
                            
                            
                        case "3":
                        	
                        	
                        	
                        	break;
                        	
                        	
                        case "4":
                        	
                        	System.out.println("\nOrder Selected: Sixty-Six");
                        	System.out.println("\nGood Soldiers Follow Orders...");
                        	System.out.println("Program Terminated, Results Logged");
                        	
                        	outputFile.println("\nOrder Selected: Sixty-Six");
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

	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		
		
	    FileInputStream inputFile = new FileInputStream("RandomInput.txt");
	    PrintWriter outputFile = new PrintWriter("ResultsLog.txt");

	      
	    Scanner scnr = new Scanner(inputFile);
	    
	    
	    displayMenu(inputFile, outputFile);
	    
	    
	    scnr.close();
	    inputFile.close();
	    outputFile.close();
	    
	}

}
