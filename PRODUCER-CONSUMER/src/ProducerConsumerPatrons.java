
public class ProducerConsumerPatrons {
	// Ah, main methods.. I know how to work these
		public static void main(String[] args) {

			ProducerConsumer foodBank = new ProducerConsumer();

			producer foodProducer = new producer(foodBank);
			
			
			/*********************************************************/
			/* Created Multiple Consumers to Ensure It Works.        */
			/* I noticed that the more consumers I add, the greater  */
			/* sleep time I had to give to allow messages to display */
			/* correctly. The food bank remained correct without     */
			/* extending time; however, the messages would display   */
			/* slighty out of order at times.                        */
			/*********************************************************/

			consumer foodConsumer = new consumer(foodBank);
			
			consumer foodConsumer2 = new consumer(foodBank);
			
			consumer foodConsumer3 = new consumer(foodBank);
			
			
			//This was for my testing purposes
			//System.out.println("Starting Program");
			
			foodProducer.start();
			
			
			/*****************************************************/
			/* I don't know if this Thread.sleep() is supposed   */
			/* to be here or not, but when I remove it, it seems */
			/* that sometimes the threads get all jacked up      */
			/* and obviously, I don't want that to happen        */
			/*****************************************************/

			try {
				Thread.sleep(1);
			}
			catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			foodConsumer.start();
			
			try {
				Thread.sleep(1);
			}
			catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			foodConsumer2.start();
			
			try {
				Thread.sleep(1);
			}
			catch(InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			
			foodConsumer3.start();


		}
}