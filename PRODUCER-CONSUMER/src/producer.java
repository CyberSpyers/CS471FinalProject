import java.util.concurrent.locks.ReentrantLock;

public class producer extends Thread {

	ProducerConsumer bank;

	public producer(ProducerConsumer bank) {

		this.bank = bank;

	}
	
	// Establish monitor lock
	ReentrantLock lock = new ReentrantLock();

	public void run(){
		
		// Initial lock
		lock.lock();

		try {
			while(true) {
	
				double randomNum = Math.random() * 100 + 1;
	
				int random = (int) randomNum;
	
				// Add random amount of food from 1-100
				bank.giveFood(random);
				
				
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