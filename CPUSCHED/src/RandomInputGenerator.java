import java.io.*; 
  
public class RandomInputGenerator { 
    
	public static void main(String arr[]) throws FileNotFoundException { 
		
        // Creating a File object that represents the disk file. 
        PrintStream newFile = new PrintStream(new File("RandomInput.txt")); 
  
        // Store current System.out before assigning a new value 
        PrintStream console = System.out; 
  
        // Assign o to output stream 
        System.setOut(newFile); 
        System.out.println("This will be written to the text file"); 
  
        // Use stored value for output stream 
        System.setOut(console); 
        System.out.println("This will be written on the console!"); 
    } 
} 