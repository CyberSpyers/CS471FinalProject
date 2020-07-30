// Megan Spiers
// Last Edited: 07/30/2020
// For use with CS471Project Part 2 Files


public class ProducerConsumer { 
	
	int items; 
	
	public ProducerConsumer() { 
		this.items = 0; 
	} 
	
	public void insertItem() { 
		this.items = this.items + 1; 
		System.out.println("Adding 1 item to items, the balance is now " + this.items + " items.");
	} 
	
	public void removeItem() {
		this.items = this.items - 1; 
		System.out.println("Taking 1 item from items, the balance is now " + this.items + " items."); 
	}
	
}