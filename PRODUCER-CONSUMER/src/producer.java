// Megan Spiers
// Last Edited: 07/31/2020
// For use with CS471Project Part 2 Files


// Import necessary stuff
import java.util.concurrent.locks.ReentrantLock;


// Create producer class for use with producer threads
public class producer extends Thread {

	
	// Declare necessary variables
	ProducerConsumer items;
	int sleepTime = ProducerConsumerPatrons.sleepTime;

	
	// Initialize items variables
	public producer(ProducerConsumer items) {

		this.items = items;

	}
	
	
	// Establish monitor lock
	ReentrantLock lock = new ReentrantLock();

	
	// Create run method to be called when producer threads are started
	@Override
	public void run(){
		
		
		// Initial lock
		this.lock.lock();

		
		// Try to insert item and then sleep and then disable lock
		try {
			
			while(true) {
	
				
				this.items.insertItem();
				
				
				try { 
					
					Thread.sleep(this.sleepTime);
					
				} 
				
				catch(InterruptedException e) {
					
					Thread.currentThread().interrupt();
					
				}
				
			}
		
		} 
		
		finally {
			
			
			// disable lock
			this.lock.unlock();
			
		}
		
		
	}
	

}