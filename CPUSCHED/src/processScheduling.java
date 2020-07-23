// Megan Spiers
// Last Edited: 07/22/2020
// Main File for CS471Project


import java.util.Scanner;


import java.io.FileInputStream;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;



public class processScheduling {
	
	final static int numProcesses = 10000;
	static int[] processIDs = new int[numProcesses];
	static int[] arrivalTimes = new int[numProcesses];
	static int[] priorities = new int[numProcesses];
	static int[] CPUBurstUnits = new int[numProcesses];
	
	
	public static void readFile(FileInputStream inputFile) {
		Scanner scnrInput = new Scanner(inputFile);

		for (int i = 0; i < numProcesses; i++) {
			processIDs[i] = scnrInput.nextInt();
			arrivalTimes[i] = scnrInput.nextInt();
			priorities[i] = scnrInput.nextInt();
			CPUBurstUnits[i] = scnrInput.nextInt();
		
		}
		scnrInput.close();
	}
	
	
	public static double getCPUUtilization() throws Exception {
		   MBeanServer server = ManagementFactory.getPlatformMBeanServer();

		   ObjectName objectName = ObjectName.getInstance("java.lang:type=OperatingSystem");
		   javax.management.AttributeList list = server.getAttributes(objectName, new String[]{ "ProcessCpuLoad" });
		   if (list.isEmpty()) { 
			   return Double.NaN; 
		   }
		
		   Attribute att = (Attribute) list.get(0);
		   Double value = (Double) att.getValue();
		  
		   if (value == -1.0) { 
			   return Double.NaN; 
		   }
		   
		  
		   return ((int) (value * 1000) / 10.0);
	}
	
	
	public static void FIFO(PrintWriter outputFile) throws Exception {
		
		// declare necessary variables
		long startElapsedTime = System.currentTimeMillis();
		int waitTimes[] = new int[numProcesses];  
		int turnaroundTimes[] = new int[numProcesses];
		int responseTimes[] = new int[numProcesses];
		int sumWaitTimes = 0;
	    int sumTurnaroundTimes = 0;
	    int sumCPUBurstUnits = 0;
	    int sumResponseTimes = 0;
		int tempBurstTotal = 0;
		double CPUutilization;
		

		for (int i = 1; i < numProcesses; i++) {
			tempBurstTotal = tempBurstTotal + CPUBurstUnits[i-1];
		    	
		    // Calculate wait time
		    waitTimes[i] = tempBurstTotal - arrivalTimes[i];  
		    	
		    // Calculate turnaround time
		    turnaroundTimes[i] = CPUBurstUnits[i] + waitTimes[i];
		    	
		    // Calculate response time
		    responseTimes[i] = tempBurstTotal - arrivalTimes[i];
		    
		    // Calculate sum for burst units, wait times, turnaround times, and response times
		    sumCPUBurstUnits = sumCPUBurstUnits + CPUBurstUnits[i];
		    sumWaitTimes = sumWaitTimes + waitTimes[i];  
		    sumTurnaroundTimes = sumTurnaroundTimes + turnaroundTimes[i]; 
		    sumResponseTimes = sumResponseTimes + responseTimes[i];
		}
		    
		// Calculate statistics to be printed   
		float throughput = sumCPUBurstUnits / numProcesses;
		float averageWaitTime = sumWaitTimes / numProcesses;
		float averageTurnaroundTime = sumTurnaroundTimes / numProcesses;
		float averageResponseTime = sumResponseTimes / numProcesses;

		
		// Calculate elapsed time
		Thread.sleep(1);
		long endElapsedTime = System.currentTimeMillis();
		double totalElapsedTime = (endElapsedTime - startElapsedTime) - 1;
		
		
		// Calculate CPU utilization
		CPUutilization = getCPUUtilization();
		
		// Print statistics to console
		outputFile.println("\nOrder Selected: FIFO");
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		outputFile.println("Throughput: " + throughput);
		outputFile.println("CPU Utilization: " + CPUutilization + "%");
		outputFile.println("Average Waiting Time: " + averageWaitTime);
		outputFile.println("Average Turnaround Time: " + averageTurnaroundTime);
		outputFile.println("Average Response Time: " + averageResponseTime + "\n");
		
		// Print statistics to ResultsLog.txt
		System.out.println("\nOrder Selected: FIFO");
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + (endElapsedTime - startElapsedTime) + "ms");
		System.out.println("Throughput: " + throughput);
		System.out.println("CPU Utilization: " + CPUutilization + "%");
		System.out.println("Average Waiting Time: " + averageWaitTime);
		System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
		System.out.println("Average Response Time: " + averageResponseTime + "\n");

	}
	
	
public static void SJFWOPreemption(PrintWriter outputFile) throws Exception {
		
		// declare necessary variables
		long startElapsedTime = System.currentTimeMillis();
		int waitTimes[] = new int[numProcesses];  
		int turnaroundTimes[] = new int[numProcesses];
		int responseTimes[] = new int[numProcesses];
		int sumWaitTimes = 0;
	    int sumTurnaroundTimes = 0;
	    int sumCPUBurstUnits = 0;
	    int sumResponseTimes = 0;
		int tempBurstTotal = 0;
		double CPUutilization;
		

		for (int i = 1; i < numProcesses; i++) {
			tempBurstTotal = tempBurstTotal + CPUBurstUnits[i-1];
		    	
		    // Calculate wait time
		    waitTimes[i] = tempBurstTotal - arrivalTimes[i];  
		    	
		    // Calculate turnaround time
		    turnaroundTimes[i] = CPUBurstUnits[i] + waitTimes[i];
		    	
		    // Calculate response time
		    responseTimes[i] = tempBurstTotal - arrivalTimes[i];
		    
		    // Calculate sum for burst units, wait times, turnaround times, and response times
		    sumCPUBurstUnits = sumCPUBurstUnits + CPUBurstUnits[i];
		    sumWaitTimes = sumWaitTimes + waitTimes[i];  
		    sumTurnaroundTimes = sumTurnaroundTimes + turnaroundTimes[i]; 
		    sumResponseTimes = sumResponseTimes + responseTimes[i];
		}
		    
		// Calculate statistics to be printed   
		float throughput = sumCPUBurstUnits / numProcesses;
		float averageWaitTime = sumWaitTimes / numProcesses;
		float averageTurnaroundTime = sumTurnaroundTimes / numProcesses;
		float averageResponseTime = sumResponseTimes / numProcesses;

		
		// Calculate elapsed time
		Thread.sleep(1);
		long endElapsedTime = System.currentTimeMillis();
		double totalElapsedTime = (endElapsedTime - startElapsedTime) - 1;
		
		
		// Calculate CPU utilization
		CPUutilization = getCPUUtilization();
		
		// Print statistics to console
		outputFile.println("\nOrder Selected: SJF W/O Preemption");
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		outputFile.println("Throughput: " + throughput);
		outputFile.println("CPU Utilization: " + CPUutilization + "%");
		outputFile.println("Average Waiting Time: " + averageWaitTime);
		outputFile.println("Average Turnaround Time: " + averageTurnaroundTime);
		outputFile.println("Average Response Time: " + averageResponseTime + "\n");
		
		// Print statistics to ResultsLog.txt
		System.out.println("\nOrder Selected: SJF W/O Preemption");
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + (endElapsedTime - startElapsedTime) + "ms");
		System.out.println("Throughput: " + throughput);
		System.out.println("CPU Utilization: " + CPUutilization + "%");
		System.out.println("Average Waiting Time: " + averageWaitTime);
		System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
		System.out.println("Average Response Time: " + averageResponseTime + "\n");

	}
	
	
public static void priorityWPreemption(PrintWriter outputFile) throws Exception {
	
	// declare necessary variables
	long startElapsedTime = System.currentTimeMillis();
	int waitTimes[] = new int[numProcesses];  
	int turnaroundTimes[] = new int[numProcesses];
	int responseTimes[] = new int[numProcesses];
	int sumWaitTimes = 0;
    int sumTurnaroundTimes = 0;
    int sumCPUBurstUnits = 0;
    int sumResponseTimes = 0;
	int tempBurstTotal = 0;
	double CPUutilization;
	

	for (int i = 1; i < numProcesses; i++) {
		tempBurstTotal = tempBurstTotal + CPUBurstUnits[i-1];
	    	
	    // Calculate wait time
	    waitTimes[i] = tempBurstTotal - arrivalTimes[i];  
	    	
	    // Calculate turnaround time
	    turnaroundTimes[i] = CPUBurstUnits[i] + waitTimes[i];
	    	
	    // Calculate response time
	    responseTimes[i] = tempBurstTotal - arrivalTimes[i];
	    
	    // Calculate sum for burst units, wait times, turnaround times, and response times
	    sumCPUBurstUnits = sumCPUBurstUnits + CPUBurstUnits[i];
	    sumWaitTimes = sumWaitTimes + waitTimes[i];  
	    sumTurnaroundTimes = sumTurnaroundTimes + turnaroundTimes[i]; 
	    sumResponseTimes = sumResponseTimes + responseTimes[i];
	}
	    
	// Calculate statistics to be printed   
	float throughput = sumCPUBurstUnits / numProcesses;
	float averageWaitTime = sumWaitTimes / numProcesses;
	float averageTurnaroundTime = sumTurnaroundTimes / numProcesses;
	float averageResponseTime = sumResponseTimes / numProcesses;

	
	// Calculate elapsed time
	Thread.sleep(1);
	long endElapsedTime = System.currentTimeMillis();
	double totalElapsedTime = (endElapsedTime - startElapsedTime) - 1;
	
	
	// Calculate CPU utilization
	CPUutilization = getCPUUtilization();
	
	// Print statistics to console
	outputFile.println("\nOrder Selected: Priority W/ Preemption");
	outputFile.println("Statistics for the Run\n");
	
	outputFile.println("Number of Processes: " + numProcesses);
	outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
	outputFile.println("Throughput: " + throughput);
	outputFile.println("CPU Utilization: " + CPUutilization + "%");
	outputFile.println("Average Waiting Time: " + averageWaitTime);
	outputFile.println("Average Turnaround Time: " + averageTurnaroundTime);
	outputFile.println("Average Response Time: " + averageResponseTime + "\n");
	
	// Print statistics to ResultsLog.txt
	System.out.println("\nOrder Selected: Priority W/ Preemption");
	System.out.println("Statistics for the Run\n");
	
	System.out.println("Number of Processes: " + numProcesses);
	System.out.println("Total Elapsed Time (For the Scheduler): " + (endElapsedTime - startElapsedTime) + "ms");
	System.out.println("Throughput: " + throughput);
	System.out.println("CPU Utilization: " + CPUutilization + "%");
	System.out.println("Average Waiting Time: " + averageWaitTime);
	System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
	System.out.println("Average Response Time: " + averageResponseTime + "\n");

}
	
	
	public static void displayMenu(FileInputStream inputFile, PrintWriter outputFile) throws Exception{
		
        Scanner scnrInput = new Scanner(System.in);
        readFile(inputFile);
        
        
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
                        	
                        	FIFO(outputFile);
                            break;
                            
                            
                        case "2":
                        	
                        	SJFWOPreemption(outputFile);
                            break;
                            
                            
                        case "3":
                        	
                        	priorityWPreemption(outputFile);
                        	break;
                        	
                        	
                        case "4":
                        	
                        	// Star Wars Pun
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


	public static void main(String[] args) throws Exception {
		
		
	    FileInputStream inputFile = new FileInputStream("RandomInput.txt");
	    PrintWriter outputFile = new PrintWriter("ResultsLog.txt");

	      
	    Scanner scnr = new Scanner(inputFile);
	    
	    
	    displayMenu(inputFile, outputFile);
	    
	    
	    scnr.close();
	    inputFile.close();
	    outputFile.close();
	    
	}

}