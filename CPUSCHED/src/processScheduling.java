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
	static double[] arrivalTimes = new double[numProcesses];
	static double[] priorities = new double[numProcesses];
	static double[] CPUBurstUnits = new double[numProcesses];
	
	
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
		   
		  
		   return ( (value * 1000) / 10.0);
	}
	
	
	public static void FIFO(PrintWriter outputFile) throws Exception {
		
		// declare necessary variables
		long startElapsedTime = System.currentTimeMillis();
		double waitTimes[] = new double[numProcesses];  
		waitTimes[0] = 0;
		double turnaroundTimes[] = new double[numProcesses];
		double responseTimes[] = new double[numProcesses];
		double sumWaitTimes = 0;
		double sumTurnaroundTimes = 0;
		double sumCPUBurstUnits = 0;
		double sumResponseTimes = 0;
		double tempBurstTotal = 0;
		double CPUutilization;
		

		for (int i = 1; i < numProcesses; i++) {
			tempBurstTotal = tempBurstTotal + CPUBurstUnits[i-1];
		    	
		    // Calculate wait time
		    waitTimes[i] = CPUBurstUnits[i - 1] + waitTimes[i - 1];  
		    	
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
		double throughput = sumCPUBurstUnits / numProcesses;
		double averageWaitTime = sumWaitTimes / numProcesses;
		double averageTurnaroundTime = sumTurnaroundTimes / numProcesses;
		double averageResponseTime = sumResponseTimes / numProcesses;

		
		// Calculate elapsed time
		Thread.sleep(1);
		long endElapsedTime = System.currentTimeMillis();
		double totalElapsedTime = (endElapsedTime - startElapsedTime) - 1;
		
		
		// Calculate CPU utilization
		CPUutilization = getCPUUtilization();
		
		// Print statistics to ResultsLog.txt
		outputFile.println("\nOrder Selected: FIFO");
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		outputFile.printf("Throughput: %.2f%n", throughput);
		outputFile.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		outputFile.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		outputFile.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		outputFile.printf("Average Response Time: %.2f\n\n", averageResponseTime);
		
		// Print statistics to console
		System.out.println("\nOrder Selected: FIFO");
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		System.out.printf("Throughput: %.2f%n", throughput);
		System.out.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		System.out.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		System.out.printf("Average Response Time: %.2f\n\n", averageResponseTime);

	}
	
	
	public static void SJFWOPreemption(PrintWriter outputFile) throws Exception {
		
		// declare necessary variables
		long startElapsedTime = System.currentTimeMillis();
		double waitTimes[] = new double[numProcesses];  
		waitTimes[0] = 0;
		double turnaroundTimes[] = new double[numProcesses];
		double responseTimes[] = new double[numProcesses];
		double sumWaitTimes = 0;
		double sumTurnaroundTimes = 0;
		double sumCPUBurstUnits = 0;
		double sumResponseTimes = 0;
		double tempBurstTotal = 0;
		double CPUutilization;
		

		for (int i = 1; i < numProcesses; i++) {
			tempBurstTotal = tempBurstTotal + CPUBurstUnits[i-1];
		    	
		    // Calculate wait time
		    waitTimes[i] = CPUBurstUnits[i - 1] + waitTimes[i - 1];  
		    	
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
		double throughput = sumCPUBurstUnits / numProcesses;
		double averageWaitTime = sumWaitTimes / numProcesses;
		double averageTurnaroundTime = sumTurnaroundTimes / numProcesses;
		double averageResponseTime = sumResponseTimes / numProcesses;

		
		// Calculate elapsed time
		Thread.sleep(1);
		long endElapsedTime = System.currentTimeMillis();
		double totalElapsedTime = (endElapsedTime - startElapsedTime) - 1;
		
		
		// Calculate CPU utilization
		CPUutilization = getCPUUtilization();
		
		// Print statistics to ResultsLog.txt
		outputFile.println("\nOrder Selected: FIFO");
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		outputFile.printf("Throughput: %.2f%n", throughput);
		outputFile.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		outputFile.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		outputFile.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		outputFile.printf("Average Response Time: %.2f\n\n", averageResponseTime);
		
		// Print statistics to console
		System.out.println("\nOrder Selected: FIFO");
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + (endElapsedTime - startElapsedTime) + "ms");
		System.out.printf("Throughput: %.2f%n", throughput);
		System.out.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		System.out.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		System.out.printf("Average Response Time: %.2f\n\n", averageResponseTime);

	}
	
	public static void priorityWPreemption(PrintWriter outputFile) throws Exception {
		
		// declare necessary variables
		long startElapsedTime = System.currentTimeMillis();
		double waitTimes[] = new double[numProcesses];  
		waitTimes[0] = 0;
		double turnaroundTimes[] = new double[numProcesses];
		double responseTimes[] = new double[numProcesses];
		double sumWaitTimes = 0;
		double sumTurnaroundTimes = 0;
		double sumCPUBurstUnits = 0;
		double sumResponseTimes = 0;
		double tempBurstTotal = 0;
		double CPUutilization;
		

		for (int i = 1; i < numProcesses; i++) {
			tempBurstTotal = tempBurstTotal + CPUBurstUnits[i-1];
		    	
		    // Calculate wait time
		    waitTimes[i] = CPUBurstUnits[i - 1] + waitTimes[i - 1];  
		    	
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
		double throughput = sumCPUBurstUnits / numProcesses;
		double averageWaitTime = sumWaitTimes / numProcesses;
		double averageTurnaroundTime = sumTurnaroundTimes / numProcesses;
		double averageResponseTime = sumResponseTimes / numProcesses;

		
		// Calculate elapsed time
		Thread.sleep(1);
		long endElapsedTime = System.currentTimeMillis();
		double totalElapsedTime = (endElapsedTime - startElapsedTime) - 1;
		
		
		// Calculate CPU utilization
		CPUutilization = getCPUUtilization();
		
		// Print statistics to ResultsLog.txt
		outputFile.println("\nOrder Selected: FIFO");
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		outputFile.printf("Throughput: %.2f%n", throughput);
		outputFile.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		outputFile.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		outputFile.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		outputFile.printf("Average Response Time: %.2f\n\n", averageResponseTime);
		
		// Print statistics to console
		System.out.println("\nOrder Selected: FIFO");
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + (endElapsedTime - startElapsedTime) + "ms");
		System.out.printf("Throughput: %.2f%n", throughput);
		System.out.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		System.out.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		System.out.printf("Average Response Time: %.2f\n\n", averageResponseTime);

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