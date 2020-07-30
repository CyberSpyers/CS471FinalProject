// Megan Spiers
// Last Edited: 07/30/2020
// Main File for CS471Project Part 2


// Import Necessary Stuff
import java.util.Scanner;


public class ProducerConsumerPatrons {
	
		public static int sleepTime;

		public static void main(String[] args) throws InterruptedException {
			
			Scanner scnr = new Scanner(System.in);
			Boolean flag = true;
			int numProducers = 0;
			int numConsumers = 0;  
			
			
			System.out.println("Enter only a postive integer for each prompt as it appears below");
			
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
			
			
			
			
			
			
			producer[] producerThreads = new producer[numProducers];
			consumer[] consumerThreads = new consumer[numConsumers];

			ProducerConsumer itemBank = new ProducerConsumer();

			for (int i = 0; i < numProducers; i++) {
				producerThreads[i] = new producer(itemBank);
				producerThreads[i].start();
				
			}
			
			for (int i = 0; i < numConsumers; i++) {
				consumerThreads[i] = new consumer(itemBank);
				consumerThreads[i].start();
			}
		
			for (producer thread : producerThreads) {
				thread.join();
			}
			
			for (consumer thread : consumerThreads) {
				thread.join();
			}
			
			
			

			
			
			
			
			
			
			

			scnr.close();
		}
}