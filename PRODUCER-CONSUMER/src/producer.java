// Megan Spiers
// Last Edited: 07/30/2020
// For use with CS471Project Part 2 Files


// Import necessary stuff
import java.util.concurrent.locks.ReentrantLock;

public class producer extends Thread {

	ProducerConsumer items;
	
	int sleepTime = ProducerConsumerPatrons.sleepTime;

	public producer(ProducerConsumer items) {

		this.items = items;

	}
	
	// Establish monitor lock
	ReentrantLock lock = new ReentrantLock();

	@Override
	public void run(){
		
		// Initial lock
		this.lock.lock();

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
		} finally {
			// disable lock
			this.lock.unlock();
		}
		
	}

}