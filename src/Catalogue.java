import java.util.*;

public class Catalogue {
	
	private Kiosk kiosk;
	
	private List<Movie> moviesAvailable = new LinkedList<>();
	private List<Movie> moviesRented = new LinkedList<>();
	private List<Genre> genres = new LinkedList<>();
	
	public Catalogue(Kiosk kiosk){
		
		this.kiosk = kiosk;
		
		//Database of movies
		Genre genre = new Genre ("Comedy");
		addGenre(genre);
		moviesAvailable.add( new Movie ("The Internship",	 	2013, 	genre, 	10));
		moviesAvailable.add( new Movie ("Life of the Party", 		2018, 	genre, 	15));
		moviesAvailable.add( new Movie ("Terminator 2",	 		1991, 	genre, 	3));
		
		genre = new Genre ("Action");
		addGenre(genre);
		moviesAvailable.add( new Movie ("Black Panther", 		2018, 	genre, 	20));
		moviesAvailable.add( new Movie ("Show Dogs", 			2018, 	genre, 	25));
		moviesAvailable.add( new Movie ("Raazi", 			2018, 	genre, 	21));
		moviesAvailable.add( new Movie ("Rampage", 			2018, 	genre, 	28));

		
		genre = new Genre ("Horror");
		addGenre(genre);
		moviesAvailable.add( new Movie ("Truth or Dare", 		2018, 	genre, 	20));
		moviesAvailable.add( new Movie ("The Witch", 			2015, 	genre, 	10));
		moviesAvailable.add( new Movie ("Insidious", 			2010, 	genre, 	7));

		
		genre = new Genre ("Drama");
		addGenre(genre);
		moviesAvailable.add( new Movie ("The Social Network",		2010, 	genre, 	4));
		moviesAvailable.add( new Movie ("102 Not Out",	 		2018, 	genre, 	17));

		genre = new Genre ("Crime");
		addGenre(genre);
		moviesAvailable.add( new Movie ("The Fate of the Furious", 	2017, 	genre, 	35));
		moviesAvailable.add( new Movie ("Furious 7", 			2015, 	genre, 	25));
		moviesAvailable.add( new Movie ("Fast & Furious 6", 		2013, 	genre, 	10));
		moviesAvailable.add( new Movie ("Fast Five", 			2011, 	genre, 	6));
		moviesAvailable.add( new Movie ("Fast & Furious 4", 		2009, 	genre, 	4));

	}
	
	public void catalogueMenu(){
		
		char userInput;

		while((userInput=readCatalogueMenu()) != 'R') {
			switch(userInput){
				case '1': listMovies(); break;
				case '2': displayAvailableMovies(); break;
				case '3': displayGenres(); break;
				case '4': displayMovieByGenre(); break;
				case '5': displayMovieByYear(); break;
				case '6': rentMovie(); break;
				case '7': returnMovie(); break;
				default: help(); break;
			}
		}
	}
	
	private void addGenre(Genre genre) {

        if (!genres.contains(genre))
            genres.add(genre);

    }

	/*
	 * Use the lookup function to read the movie title and year
	 * If the movie is not available, it will get added to the list as a new movie
	 */
	public void addMovie(){
		
		System.out.println("\nAdding a new movie.");
		
		String title = readTitle();
		int year = readYear();
		
		String genre = readGenre();
		int price = readPrice();
		
		Genre genres = new Genre(genre);
		
		Movie movie = lookUpMovie(title, year);
		
		if(movie == null){
			moviesAvailable.add( new Movie (title, year, genres, price));
			System.out.println("Added " + title + " to catalogue.");
			
		} else 
			System.out.println("The movie is already in the catalogue.");
	}
	
	//If the movie entered is available, it will get removed
	public void removeMovie(){
	
		System.out.println("\nRemoving a movie.");
		String title = readTitle();
		
		int year = readYear();
		Movie movie = lookUpMovie(title, year);
		
		if(movie != null){
			moviesAvailable.remove(movie);
			System.out.println(movie + " removed from catalogue.\n");
			
		} else
			System.out.println("No such movie found.\n");
	}
	
	//The look up function is used to lookup a movie by it is title and year
	//It returns the movie list if it was found, otherwise it will return null
	private Movie lookUpMovie (String title, int year){
		
		for(Movie i: moviesAvailable)
			if((i).hasMovieName(title) && (i).hasMovieYear(year))
				return i;
		
		return null;	
	}
	
	public void listMovies(){
		displayMovie("The Kiosk has the following movies: ");
	}
	
	private void displayAvailableMovies(){
		displayMovie("The following movies are available: ");
	}
	
	//Print out all available movies
	public void displayMovie(String s){
		
		System.out.println("\n" + s);
		for(Movie i: moviesAvailable)
			System.out.println(i);
		
		System.out.println();
	}
	
	/*
	 * This methods checks if the movie entered is available
	 * It checks if the customer has enough balance and take payment
	 * Then adds the movie it to the customer's rented list
	 */
	private void rentMovie(){		
		Customer customer = kiosk.readCustomerID_Valid();
		
		if(customer != null){
			
			String title = readMovieTitle("rent");
			Movie movie = lookUpMoviebyTitle(title);
			
			if(movie != null){
				int movieCost = movie.getPrice();
				
				if(customer.checkBalance(movieCost)){
					customer.pay(movieCost);
					customer.addRentedMovie(movie);
					addRentedMovie(movie);
					System.out.println("Movie rented.\n");
					
				} else
					System.out.println("You don't have sufficient funds to rent this movie.\n");
			
			} else
				System.out.println("That movie is not available or doesn't exist.\n");
			
		} else
			System.out.println("This ID does not exist.\n");

	}
	
	private void returnMovie(){
		Customer customer = kiosk.readCustomerID_Valid();
		
		if(customer != null){
			
			String rentedMovies = customer.moviesRented();
			System.out.println(rentedMovies);
			
			displayMoviesRented();
			
			String title = readMovieTitle("return");
			Movie movie = lookUpRentedMoviebyTitle(title);
			
			if(movie != null){
				customer.returnMovie(movie);
				returnMovie(movie);
				System.out.println(title + " has been returned.\n");
				
			} else 
				System.out.println("That movie is not available or doesn't exist.\n");
			
		} else
			System.out.println("This ID does not exist.\n");
		
	}
	
	public void showFavourite(){
		System.out.println();
		Customer customer = kiosk.readCustomerID();
		
		if(customer != null){
			System.out.println(customer.getName() + "'s favourite movies are: ");
			customer.showFavourites();
			System.out.println();
			
		} else
			System.out.println("This ID does not exist.\n");		
	}
		
	private String readMovieTitle(String type){
		
		System.out.print("Enter the title of the movie you wish to " + type +": ");
		return In.nextLine();
		
	}
	
	//Look up movie by it is title and returns the list once it found a match
	public Movie lookUpMoviebyTitle (String title){
		
		for(Movie i: moviesAvailable)
			if((i).hasMovieName(title))
				return i;
		
		return null;
	}
	
	public Movie lookUpRentedMoviebyTitle (String title){
		
		for(Movie i: moviesRented)
			if((i).hasMovieName(title))
				return i;
		
		return null;
	}
	
	//Once a movie has been rented it will add it to a new list and remove it from the available movies list
	public void addRentedMovie(Movie rented){
		
		moviesRented.add(rented);
		moviesAvailable.remove(rented);
	}
	
	//shows the price of the movie
	public int moviePrice(String title){
		
		Movie movie = lookUpMoviebyTitle(title);
		int price = movie.getPrice();
		
		return price;
	}
	
	//adds the movie back to the available list and removes it from the rented list
	public void returnMovie(Movie returnedMovie){
		
		moviesAvailable.add(returnedMovie);
		moviesRented.remove(returnedMovie);	
	}
	
	//show all rented movies
	public void displayMoviesRented(){
		
		for(Movie i: moviesRented)
			System.out.println(i);
	}
	
	//show all genres available
	public void displayGenres(){
		System.out.println("\nThe Kiosk has movies in the following genres: ");
		
		for(Genre i: genres)
			System.out.println(i);
		
		System.out.println();
	}
	
	//look up a genre and return it if it is available
	public Genre lookUpGenre(String genre){
		
		for(Genre i: genres)
			if((i).hasGenre(genre))
				return i;
		
		return null;
	}
	
	/*
	 * If the genre entered is available
	 * It will call "lookUpMovieByGenre"
	 */
	public void displayMovieByGenre(){
		
		System.out.println();
		String genre = readGenre();
		
		Genre g = lookUpGenre(genre);
		
		System.out.println("The kiosk has the following movies in that genre: ");

		if(g != null){
			lookUpMovieByGenre(g);
			
			System.out.println("");
			
		} else
			System.out.println("");
	}

	
	/*
	/* Go through each list and check if the genre is in that list
	 * if it is, it will add the list it found to the new list that contains that genre
	 */
	private void lookUpMovieByGenre(Genre genre){
		
		LinkedList <Movie> genresX = new LinkedList<>();
		
		for(Movie i: moviesAvailable)
			if(i.hasMovieGenre(genre)){
				genresX.add(i);
				System.out.println(i);
			}		
	}
	
	//Reads a year from the user and passes it to the "lookUpMovieByYear" function
	public void displayMovieByYear(){
		
		System.out.println();
		int year = readYear();
		
		System.out.println("The kiosk has the following movies by that year: ");
		
		lookUpMovieByYear(year);
		System.out.println();
	}
	
	//Checks if the year is in moviesAvailable list, if it is
	//It will add it to a new list and display it
	private void lookUpMovieByYear(int year){
		
		LinkedList <Movie> yearsX = new LinkedList<>();

		for(Movie i: moviesAvailable)
			if((i).hasMovieYear(year)){
				yearsX.add(i);
				System.out.println(i);
			}
	}
	
	private String readTitle(){
		
		System.out.print("Enter the title of the movie: ");
		return In.nextLine();
	}
	
	private int readYear(){
		
		System.out.print("Enter the year: ");
		return In.nextInt();
	}
	
	private String readGenre(){
		
		System.out.print("Enter a genre: ");
		return In.nextLine();
	}
	
	private int readPrice(){
		
		System.out.print("Enter price: ");
		return In.nextInt();
	}
	
	private char readCatalogueMenu(){
		System.out.println("Welcome to the Catalogue! Please make a selection from the menu:");
		System.out.println("1. Display all movies.");
		System.out.println("2. Display all available movies.");
		System.out.println("3. Display all genres.");
		System.out.println("4. Display movies in a genre.");
		System.out.println("5. Display all movies by year.");
		System.out.println("6. Rent a movie.");
		System.out.println("7. Return a movie.");
		System.out.println("R. Return to previous menu.");
		
		System.out.print("Enter a choice: ");
		return In.nextChar();
	}
	
	private void help(){
		System.out.println("Please enter a number between 1 and 7 or press R to return to the previous menu.");
	}
}
