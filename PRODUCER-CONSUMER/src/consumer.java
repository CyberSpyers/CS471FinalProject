// Megan Spiers
// Last Edited: 07/30/2020
// For use with CS471Project Part 2 Files


// Import necessary stuff
import java.util.concurrent.locks.ReentrantLock;

public class consumer extends Thread{ 
	
	ProducerConsumer items; 
	int sleepTime = ProducerConsumerPatrons.sleepTime;
	
	public consumer(ProducerConsumer items) { 
		
		this.items = items; 
		
	} 
	
	// Establish monitor lock
	ReentrantLock lock = new ReentrantLock();
	
	
	@Override
	public void run(){
		
		// Initial lock
		this.lock.lock();
		
		try {
		
			while(true){ 
				
				
				
				if(this.items.items < 0) {
					
					try {
						wait();
					} catch (InterruptedException e) {
						//
					}
					System.out.println("Waiting to get items.");
				}
					
				else {
					
					try{
						
						notify();
					} catch (IllegalMonitorStateException e) {
						//
					}
					
					
					if(this.items.items >= 1){ 
						
						this.items.removeItem(); 
						try {
							Thread.sleep(this.sleepTime); 
						}
						catch(InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
					else{ 
						// If there's not enough to take away the desired amount, state the problem
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
		} finally {
			// disable lock
			this.lock.unlock();
		}
	} 
}