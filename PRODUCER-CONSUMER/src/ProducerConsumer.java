// Megan Spiers
// Last Edited: 07/31/2020
// For use with CS471Project Part 2 Files

 
public class ProducerConsumer { 
	
	
	// Declare variable
	int items; 
	
	
	// Initialize variable to 0
	public ProducerConsumer() { 
		this.items = 0; 
	} 
	
	
	// Create insert item method to add 1 to items
	public void insertItem() { 
		this.items = this.items + 1; 
		System.out.println("Adding 1 item to items, the balance is now " + this.items + " items.");
	} 
	
	
	// Create remove item method to remove 1 from items
	public void removeItem() {
		this.items = this.items - 1; 
		System.out.println("Taking 1 item from items, the balance is now " + this.items + " items."); 
	}
	
	
}