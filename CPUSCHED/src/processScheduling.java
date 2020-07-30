// Megan Spiers
// Last Edited: 07/30/2020
// Main File for CS471Project Part 1


// Import Necessary Stuff
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import javax.management.Attribute;
import javax.management.MBeanServer;
import javax.management.ObjectName;


// Begin class
public class processScheduling {
	
	// Declare Global Variables
	final static int numProcesses = 10000;
	static int[] processIDs = new int[numProcesses];
	static int[] arrivalTimes = new int[numProcesses];
	static int[] priorities = new int[numProcesses];
	static int[] CPUBurstUnits = new int[numProcesses];
	
	
	// This method reads the input files and sorts data into 4 arrays
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
	
	
	/* 
	 * This method returns the CPU Utilization. This is not entirely my code. 
	 * Method code based on: https://stackoverflow.com/questions/3044841/cpu-usage-mbean-on-sun-jvm
	 */
	
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
	
	
	// This method returns the run statistics in FIFO order
	public static void FIFO(String orderSelected, PrintWriter outputFile) throws Exception {
		
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
		outputFile.println("\nOrder Selected: " + orderSelected);
		outputFile.println("Statistics for the Run\n");
		
		outputFile.println("Number of Processes: " + numProcesses);
		outputFile.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		outputFile.printf("Throughput: %.2f%n", throughput);
		outputFile.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		outputFile.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		outputFile.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		outputFile.printf("Average Response Time: %.2f\n\n", averageResponseTime);
		
		// Print statistics to console
		System.out.println("\nOrder Selected: " + orderSelected);
		System.out.println("Statistics for the Run\n");
		
		System.out.println("Number of Processes: " + numProcesses);
		System.out.println("Total Elapsed Time (For the Scheduler): " + totalElapsedTime + "ms");
		System.out.printf("Throughput: %.2f%n", throughput);
		System.out.printf("CPU Utilization: %.2f%%\n", CPUutilization);
		System.out.printf("Average Waiting Time: %.2f\n", averageWaitTime);
		System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
		System.out.printf("Average Response Time: %.2f\n\n", averageResponseTime);

	}
	
	
	/*
	 * This method sorts CPUBurstUnits array in ascending order, sorts the other arrays according to
	 * this order, and then calls the FIFO method to print the run statistic in SJFWOPreemption order
	 */
	public static void SJFWOPreemption(String orderSelected, PrintWriter outputFile) throws Exception {
		
		
		// Create temporary arrays to store the values of the original arrays
		int[] temparr1 = new int[processIDs.length];
        int[] temparr2 = new int[priorities.length];
        int[] temparr3 = new int[arrivalTimes.length];
        int[] temparr4 = new int[CPUBurstUnits.length];
        
        
        // Populate temporary arrays
        for (int i=0; i<processIDs.length; i++) { 
            temparr1[i] = processIDs[i]; 
        	temparr2[i] = priorities[i];
        	temparr3[i] = arrivalTimes[i];
        	temparr4[i] = CPUBurstUnits[i];
        }
        

        // Call method to sort arrays
        sortByBase(CPUBurstUnits, processIDs,arrivalTimes, priorities);
        
       
        // Call FIFO to calculate statistics for sorted arrays
        FIFO(orderSelected, outputFile);
        
        
        // Populate arrays back with their original values
        for (int i=0; i<processIDs.length; i++) { 
            processIDs[i] = temparr1[i]; 
            priorities[i] = temparr2[i]; 
            arrivalTimes[i] = temparr3[i]; 
            CPUBurstUnits[i] = temparr4[i]; 
        }

	}
	
	
	/*
	 * This method sorts priorities array in ascending order, sorts the other arrays according to
	 * this order, and then calls the FIFO method to print the run statistic in priorityWPreemption order
	 */
	public static void priorityWPreemption(String orderSelected, PrintWriter outputFile) throws Exception {
		
		// Create temporary arrays to store the values of the original arrays
        int[] temparr1 = new int[processIDs.length];
        int[] temparr2 = new int[priorities.length];
        int[] temparr3 = new int[arrivalTimes.length];
        int[] temparr4 = new int[CPUBurstUnits.length];
        
        
        // Populate temporary arrays
        for (int i=0; i<processIDs.length; i++) { 
            temparr1[i] = processIDs[i]; 
        	temparr2[i] = priorities[i];
        	temparr3[i] = arrivalTimes[i];
        	temparr4[i] = CPUBurstUnits[i];
        }
        

        // Call method to sort arrays
        sortByBase(priorities,processIDs,arrivalTimes,CPUBurstUnits);
        
        // Call FIFO to calculate statistics for sorted arrays
        FIFO(orderSelected, outputFile);
        
        
     // Populate arrays back with their original values
        for (int i=0; i<processIDs.length; i++) { 
            processIDs[i] = temparr1[i]; 
            priorities[i] = temparr2[i]; 
            arrivalTimes[i] = temparr3[i]; 
            CPUBurstUnits[i] = temparr4[i]; 
        }
	}
	
	
	// This method sort the arrays sorted1, sorted2, and sorted3 in the index order of sorted array base
    public static void sortByBase(int[] base, int[] sorted1, int[] sorted2, int[] sorted3) {

    	// Reorder arrays according to array base
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < base.length - i - 1;j++) {
                if (base[j] > base[j + 1] ) {
                    int temp = base[j]; 
                    base[j] = base[j+1];
                    base[j+1] = temp;
                    temp = sorted1[j]; 
                    sorted1[j] = sorted1[j + 1];
                    sorted1[j + 1] = temp;
                     temp = sorted2[j]; 
                     sorted2[j] = sorted2[j + 1];
                     sorted2[j + 1] = temp;
                    temp = sorted3[j]; 
                    sorted3[j] = sorted3[j + 1];
                    sorted3[j + 1] = temp;
                }
            }
        }

    }
	
	
    // This method serves as a main menu for the code
	public static void displayMenu(FileInputStream inputFile, PrintWriter outputFile) throws Exception{
		

        Scanner scnrInput = new Scanner(System.in);
        readFile(inputFile);
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
                        	FIFO(orderSelected, outputFile);
                            break;
                            
                            
                        case "2":
                        	
                        	orderSelected = "SJF W/O Preemption";
                        	SJFWOPreemption(orderSelected, outputFile);
                            break;
                            
                            
                        case "3":
                        	
                        	orderSelected = "Priority W/ Preemption";
                        	priorityWPreemption(orderSelected, outputFile);
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


	// Main method driver code
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