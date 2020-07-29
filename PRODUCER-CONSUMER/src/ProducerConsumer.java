
public class ProducerConsumer { 
	
	int items; 
	
	public ProducerConsumer() { 
		items = 0; 
	} 
	
	public void insertItem() { 
		items = items + 1; 
		System.out.println("Adding 1 item to items, the balance is now " + items + " items.");
	} 
	
	public void removeItem() {
		items = items - 1; 
		System.out.println("Taking 1 item from items, the balance is now " + items + " items."); 
	}
	
}
