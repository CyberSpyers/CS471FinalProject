import java.util.concurrent.locks.ReentrantLock;

public class producer extends Thread {

	ProducerConsumer items;

	public producer(ProducerConsumer items) {

		this.items = items;

	}
	
	// Establish monitor lock
	ReentrantLock lock = new ReentrantLock();

	public void run(){
		
		// Initial lock
		lock.lock();

		try {
			while(true) {
	
				
	
				// Add random amount of food from 1-100
				items.insertItem();
				
				
				/*******************************************/
				/* I had to change my sleep time to longer */
				/* or else my messages got jack up using   */
				/* multiple consumers.                     */
				/*******************************************/
				try {
					Thread.sleep(700);
				}
				catch(InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		} finally {
			// disable lock
			lock.unlock();
		}
		
	}

}