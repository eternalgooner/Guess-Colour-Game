import java.util.ArrayList;
import java.util.Arrays;


public class ArrayListsExample {
	
	//declare new array list
	private static ArrayList<String> classNames = new ArrayList<String>();
	
	//input 10 names into array list
	public static void main(String[] args) {
		classNames.add("Paul");
		classNames.add("Paula");
		classNames.add("Pauline");
		classNames.add("Neol");
		classNames.add("Gary");
		classNames.add("Ronan");
		classNames.add("Brendan");
		classNames.add("Rachael");
		classNames.add("Trevor");
		classNames.add("David");
		//print the class size to the screen
		System.out.println("Class size is now: " + classNames.size());
		
		//loop through array list to print each element in it
		for(int i = 0; i < classNames.size(); ++i){
			System.out.println(i + ") " + classNames.get(i));
		}
		
		//remove 2 names from the array list & print the size of the array list
		classNames.remove(0);
		classNames.remove(6);
		System.out.println("\n" + "Class size is now: " + classNames.size());
		
		//loop through the array list & print the current names in the list
		for(int i = 0; i < classNames.size(); ++i){
			System.out.println("\n" + i + ") " + classNames.get(i));
		}
		
		//add 3 names to our array list
		classNames.add("Graham");
		classNames.add("John");
		classNames.add("Dean");
		
		//print new class size
		System.out.println("Class size is now: " + classNames.size());
		
		//clear all names from array list & print new class size
		classNames.clear();
		System.out.println("\nClass size is now: " + classNames.size());
		
		//add a string array to our array list 
		String[] names = {"A", "B", "C", "D", "E", "F", "G", "H"};
		classNames.addAll(Arrays.asList(names));
		
		//print out the new array list after adding a string array 
		System.out.println("\nClass size is now: " + classNames.size());
		
		String student = classNames.get(0);
		System.out.println(student);
	}

}
