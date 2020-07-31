// Megan Spiers
// Last Edited: 07/31/2020
// For use with CS471Project Part 2 Files


// Import necessary stuff
import java.util.concurrent.locks.ReentrantLock;


//Create producer class for use with producer threads
public class consumer extends Thread{ 
	
	
	// Declare necessary variables
	ProducerConsumer items; 
	int sleepTime = ProducerConsumerPatrons.sleepTime;
	
	
	// Initialize items variables
	public consumer(ProducerConsumer items) { 
		
		this.items = items; 
		
	} 
	
	
	// Establish monitor lock
	ReentrantLock lock = new ReentrantLock();
	
	
	// Create run method to be called when consumer threads are started
	@Override
	public void run(){
		
		// Initial lock
		this.lock.lock();
		
		
		// Try to remove item and then sleep and then disable lock
		try {
		
			while(true){ 
				
				if(this.items.items < 0) {
					
					try {
						
						wait();
						
					} 
					
					catch (InterruptedException e) {
						//
					}
					
					System.out.println("Waiting to get items.");
					
				}
					
				else {
					
					try{
						
						notify();
						
					} 
					
					catch (IllegalMonitorStateException e) {
						//
					}
					
					
					// If there is enough to take one, take one
					if(this.items.items >= 1) { 
						
						this.items.removeItem(); 
						
						try {
							
							Thread.sleep(this.sleepTime); 
							
						}
						
						catch(InterruptedException e) {
							
							Thread.currentThread().interrupt();
							
						}
						
					}
					
					else{ 
						
						
						// If there's not enough to take one, state the problem
						System.out.println("Not enough items to take an item. Current balance is " + this.items.items + " items."); 
						
						try {
							
							Thread.sleep(this.sleepTime);
							
						}
						
						catch(InterruptedException e) {
							
							Thread.currentThread().interrupt();
							
						}
						
					} 
					
				}
				
			} 
			
		} 
		
		finally {
			
			// disable lock
			this.lock.unlock();
			
		}
		
	} 
	
}