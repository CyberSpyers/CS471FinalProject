// Megan Spiers
// Last Edited: 07/31/2020
// Main File for CS471Project Part 2


// Import Necessary Stuff
import java.util.Scanner;


// This is the file you want to run for part 2
public class ProducerConsumerPatrons {
	
	
		// Declare variable to use with all part 2 files
		public static int sleepTime;

		
		// Declare main method
		public static void main(String[] args) throws InterruptedException {
			
			
			// Declare and initialize variables for file
			Scanner scnr = new Scanner(System.in);
			Boolean flag = true;
			int numProducers = 0;
			int numConsumers = 0;  
			
			
			// Print broad instruction for following prompts
			System.out.println("Enter only a postive integer for each prompt as it appears below");
			
			
			/*
			 * Print prompts for # of producer threads, # of consumer threads, & sleep time
			 * Accept only when all are integers >= 0
			 *
			 * It would be more ideal to have three separate try catches to validate all three
			 * variables; however, I combined it just to save space in this file as error checking
			 * was not the point of this project
			 */
			
			
			while (flag) {
				try {	
			
					System.out.print("Enter the desired # of producer threads: ");
					String numProducersString = scnr.nextLine();
					numProducers = Integer.parseInt(numProducersString);
					System.out.print("Enter the desired # of consumer threads: ");
					String numConsumersString = scnr.nextLine();
					numConsumers = Integer.parseInt(numConsumersString);
					System.out.print("Enter the desired sleep time: ");
					String sleepTimeString = scnr.nextLine();
					sleepTime = Integer.parseInt(sleepTimeString);
			
					if ((numProducers >= 0) && (numConsumers >= 0)
							&& (sleepTime >= 0)) {
					
						flag = false;
						System.out.println();
						
					}
					
					else {
						System.out.println("Enter only a postive integer for each prompt");
					}
					
				}
				catch (Exception e) {
					System.out.println("Enter only a postive integer for each prompt");
				}
				
				finally {
					System.out.println();
				}
			}
			
			
			// Create producer and consumer arrays of the inputed size to hold threads
			producer[] producerThreads = new producer[numProducers];
			consumer[] consumerThreads = new consumer[numConsumers];

			
			// Create general ProducerConsumer object variable
			ProducerConsumer itemBank = new ProducerConsumer();

			
			// Start all producer threads
			for (int i = 0; i < numProducers; i++) {
				producerThreads[i] = new producer(itemBank);
				producerThreads[i].start();	
			}
			
			
			// Start all consumer threads
			for (int i = 0; i < numConsumers; i++) {
				consumerThreads[i] = new consumer(itemBank);
				consumerThreads[i].start();
			}
		
			
			// Join all producer threads
			for (producer thread : producerThreads) {
				thread.join();
			}
			
			
			// Join all consumer threads
			for (consumer thread : consumerThreads) {
				thread.join();
			}
			
			
			// Close scnr variable
			scnr.close();
			
			
		}
		
		
}