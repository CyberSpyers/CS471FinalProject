import java.util.concurrent.locks.ReentrantLock;

public class consumer extends Thread{ 
	
	ProducerConsumer bank; 
	
	public consumer(ProducerConsumer bank) { 
		
		this.bank = bank; 
		
	} 
	
	// Establish monitor lock
	ReentrantLock lock = new ReentrantLock();
	
	
	public void run(){
		
		// Initial lock
		lock.lock();
		
		try {
		
			while(true){ 
				double randomDouble =Math.random() * 100 + 1; 
				int random = (int) randomDouble; 
				
				// If food bank is empty, wait until its not
				if(bank.food < 0) {
					
					try {
						wait();
					} catch (InterruptedException e) {
						
					}
					System.out.println("Waiting to get food.");
				}
					
				else {
					
					try{
						// If food bank is not empty, get it going
						notify();
					} catch (IllegalMonitorStateException e) {
						
					}
					
					// If there's enough food in the bank to take away the desired amount, do so
					if(bank.food >= random){ 
						
						bank.takeFood(random); 
						try {
							Thread.sleep(500); // I had to change this to a higher number to ensure my threads didn't get out of whack
						}
						catch(InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
					else{ 
						// If there's not enough to take away the desired amount, state the problem
						System.out.println("Not enough food to take " + random + " items. Current balance is " + bank.food + " items."); 
						try {
							Thread.sleep(100);
						}
						catch(InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					} 
				}
				
			} 
		} finally {
			// disable lock
			lock.unlock();
		}
	} 
}