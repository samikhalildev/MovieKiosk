import java.util.*;

public class Kiosk {
	
	private Catalogue catalogue;
	private List<Customer> customers = new LinkedList<>();
		
	public Kiosk(){
		
		//Database of customers
		customers.add( new Customer (101, 	"Sami",   		50));
		customers.add( new Customer (102, 	"John", 		10));
		customers.add( new Customer (103, 	"David", 		32));
		customers.add( new Customer (104, 	"Aiden", 		26));
		customers.add( new Customer (105, 	"Steve", 		17));
		customers.add( new Customer (106, 	"Tony", 		14));
		customers.add( new Customer (107, 	"Mark", 		44));
		customers.add( new Customer (108, 	"Bill", 		58));
		customers.add( new Customer (109, 	"Eric", 		71));
		customers.add( new Customer (110, 	"Wayde", 		35));

		catalogue = new Catalogue(this);
	}
	
	public static void main(String[] args){
		
		new Kiosk().use();
	}

	private void use(){
		
		char userInput;
		
		while((userInput=readFirstMenu()) != 'X') {
			
			switch(userInput){
				case '1': catalogueMenu(); break;
				case '2': viewRecord(); break;
				case '3': showFavourite(); break;
				case '4': topUp(); break;
				case '5': administrationMenu(); break;
				default: help(); break;
				
			}
		}
	}
	
	private void catalogueMenu(){
		catalogue.catalogueMenu();
	}
	
	public Customer readCustomerID(){
		int ID = readID();
		Customer customer = lookUpID(ID);
		
		return customer;
	}
	
	public Customer readCustomerID_Valid(){
		System.out.println();
		int ID = readID();
		Customer customer = lookUpID(ID);
		
		return customer;
	}
	
	public int readID(){
		System.out.print("Enter a valid customer ID: ");
		return In.nextInt();
	}
	
	public String readMovieTitle(String type){	
		System.out.print("Enter the title of the movie you wish to " + type +": ");
		return In.nextLine();
	}
	
	/*
	 * Show customer ID, name, balance, movies rented and their renting history
	 */
	private void viewRecord(){
		
		System.out.println("");
		
		int userInput = readID();
		Customer customer = lookUpID(userInput);
		
		if(customer != null){
			System.out.println("ID: " + customer.getID());
			System.out.println("Name: " + customer.getName());
			System.out.println("Balance: $" + customer.getBalance());
			System.out.println("Movies currently rented by " + customer.getName() + ": ");
			customer.rentedMovies();
			
			System.out.println(customer.getName() + "'s renting history: ");
			customer.rentingHistory();
			
			System.out.println("");
		}
		
		else
			System.out.println("That customer does not exist.\n");
	}
	
	//Display the favourite list
	private void showFavourite(){
		System.out.println();
		int ID = readID();
		
		Customer customer = lookUpID(ID);
		
		if(customer != null){
			customer.sortByRentCount();
			//customer.fav();
			if(customer.hasFavourites()){
				System.out.println(customer.getName() + "'s favourite movies are: ");
				customer.showFavourites();
				System.out.println();
				
			} else 
				System.out.println(customer.getName() + " doesn't have any favourites.\n");
			
		} else 
			System.out.println("This ID does not exist.\n");
		
	}
	
	/*
	 * This method adds the user amount into the current balance
	 * It shows what the previous balance was and the current one
	 */
	private void topUp(){
		
		System.out.println();
		
		int ID = readID();
		int amount = readAmount();
		
		Customer customer = lookUpID(ID);
		
		if(customer != null){
			System.out.println("\nTransaction complete.");
			System.out.println(customer.getName() + "'s balance was: $" + customer.getBalance());
			customer.addBalance(amount);
			System.out.println(customer.getName() + "'s current balance is: $" + customer.getBalance());
			System.out.println("");
			
		} else
			System.out.println("This ID does not exist.\n");
	}

	
	//Looks up the customer ID to see if it is available in the customers list
	private Customer lookUpID (int userInput){
		
		for(Customer ID: customers)
			if(ID.hasRecord(userInput))
				return ID;
		
		return null;
	}
	
	private int readAmount(){
		
		System.out.print("Enter the top-up amount:");
		return In.nextInt();
	}
	
	private void administrationMenu(){
		
		char userInput;
		
		while((userInput=readAdminMenu()) != 'R') {
			
			switch(userInput){
				case '1': displayCustomer(); break;
				case '2': addCustomer(); break;
				case '3': removeCustomer(); break;
				case '4': catalogue.listMovies(); break;
				case '5': catalogue.addMovie(); break;
				case '6': catalogue.removeMovie(); break;
			}
		}
	}
	
	//Show all the customers
	private void displayCustomer(){
		
		System.out.println("\nThe Kiosk has the following customers:");
		
		for(Customer i: customers)
			System.out.println(i);
		
		System.out.println();
	}

	//Asks for new customer details and adds it to the customer list
	private void addCustomer(){
		
		System.out.println("\nAdding a new customer.");
		
		int ID = readNewID();
		String name = readName();
		int price = readBalance();
		
		Customer isExists = lookUpID(ID);
		
		if(isExists == null){
			customers.add(new Customer(ID, name, price));
			System.out.println("Customer added.\n");
			
		} else
			System.out.println("This ID already exists.\n");
		
	}

	
	//Looks up the ID to see if the customer is available
	//If it is, it will remove that customer from the list
	private void removeCustomer(){
		
		System.out.println();
		System.out.println("Removing a customer.");
		
		int userInput = readID();
		Customer i = lookUpID(userInput);
		
		if(i != null){
			customers.remove(i);
			System.out.println("Customer removed.\n");
			
		} else
			System.out.println("That customer does not exist.\n");
	}

	
	private char readFirstMenu(){
		
		System.out.println("Welcome to the Movie Kiosk! Please make a selection from the menu:");
		System.out.println("1. Explore the catalogue.");
		System.out.println("2. View your customer record.");
		System.out.println("3. Show you favourite movies.");
		System.out.println("4. Top up account.");
		System.out.println("5. Enter Admin Mode.");
		System.out.println("X. Exit the system.");
		
		System.out.print("Enter a choice: ");
		return In.nextChar();
	}
	
	private char readAdminMenu(){
		
		System.out.println("Welcome to the administration menu:");
		System.out.println("1. List all customers.");
		System.out.println("2. Add a customer.");
		System.out.println("3. Remove a customer.");
		System.out.println("4. List all movies.");
		System.out.println("5. Add a movie to the catalogue.");
		System.out.println("6. Remove a movie from the catalogue.");
		System.out.println("R. Return to the previous menu.");
		
		System.out.print("Enter a choice: ");
		return In.nextChar();
	}
	
	private void help(){
		System.out.println("Please enter a number between 1 and 5, or press X to exit.");
	}
	
	
	private int readNewID(){
		System.out.print("Enter a new ID: ");
		return In.nextInt();
	}
	
	private String readName(){
		System.out.print("Enter the customer's name: ");
		return In.nextLine();
	}
	
	private int readBalance(){
		System.out.print("Enter the customer's initial balance: ");
		return In.nextInt();
	}
}