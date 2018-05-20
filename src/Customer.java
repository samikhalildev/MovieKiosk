import java.util.*;
import java.util.stream.Collectors;

public class Customer {
	
	private String name;
	private int ID;
	private int balance;
	
	private List<Movie> currentlyRented = new LinkedList<>();
	private List<Movie> rentingHistory = new LinkedList<>();
	private List<Movie> favourites = new LinkedList<>();

	public Customer(int ID, String name, int balance){
		this.ID = ID;
		this.name = name;
		this.balance = balance;
	}
	
	//Check if there are favourites
	public boolean hasFavourites(){
		for(Movie movie: favourites)
			if(movie != null)
				return true;
		
		return false;
	}
	
	public void showFavourites(){
		for(Movie movie: favourites)
			System.out.println(movie);
	}
	
	public void addRentedMovie(Movie rentedMovie){
		currentlyRented.add(rentedMovie);
		rentingHistory.add(rentedMovie);
		
		rentedMovie.setCount(1);
		favourites.add(rentedMovie);	
	}
	
	/*
	 * for each element in the list until (size()-1) last unsorted element
	 * check if the count at index i is less than the next element (i+1)
	 * swap them and after a pass decrease lastunsorted by 1 
	 * in the next pass lastUnSorted will be size()-2 because the last element is done
	 */
	public void sortByRentCount(){
		
		//Remove duplicates
		favourites = favourites.stream().distinct().collect(Collectors.toList());
		
		int swapCounter = -1;

		//if counter is 0 after one pass, the list will be sorted and will exit out of the loop		
		while(swapCounter != 0){
			
			swapCounter = 0;
			int lastUnSorted = favourites.size() - 1;
			
			for(int i = 0; i < lastUnSorted; i++){	
				if(favourites.get(i).getCount() < favourites.get(i+1).getCount()){		
					swap(favourites, i, i + 1);
					swapCounter++;
				}
			}
			lastUnSorted--;
		}
		favouritesLength(3, -1);
	}

	private void swap(List<Movie> favourites, int i, int j){
		
		//store the first and second list
		Movie firstElement = favourites.get(i);
		Movie secondElement = favourites.get(j);
		
		//swap  
		favourites.set(i, secondElement);
		favourites.set(j, firstElement);
	}
	
	//if the list size is greater than 3, this function will remove the last list and repeat until the list only contains 3 elements
	private void favouritesLength(int max, int lastIndex){
		while(favourites.size() > max){
			lastIndex = favourites.size()-1;
			favourites.remove(lastIndex);
		}
	}
	
	public void rentedMovies(){
		for(Movie i: currentlyRented)
			System.out.println(i);
	}
	
	public void rentingHistory(){
		for(Movie i: rentingHistory)
			System.out.println(i);
	}
	
	public boolean name(Movie movie){
		return this.name.equals(movie);	
	}
	
	public void returnMovie(Movie rentedMovie){
		currentlyRented.remove(rentedMovie);
	}
	
	public void addBalance(int amount){
		this.balance += amount;
	}
	
	public boolean checkBalance(int price){
		return balance >= price;
	}
		
	public int getID(){
		return ID;
	}
	
	public String getName(){
		return name;
	}
	
	public void pay(int amount) {
		balance -= amount;
	}
	
	public int getBalance(){	
		return balance;
	}
	
	public boolean hasRecord(int userInput){
		return (ID == userInput);
	}
	
	public boolean hasEnoughBalance(int userInput){
		return (balance >= userInput);
	}
	
	public String moviesRented(){
		return name + " has the following movies: \n"
				+ "Movies currently rented by " + name +": ";
	}
	
	@Override
	public String toString(){
		return ID + "\t" + name + "\t$ " + balance;
	}
	
}
