package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {

    private final int ID;
    private final String name;
    private IntegerProperty balance = new SimpleIntegerProperty();
    private ObservableList<Movie> currentlyRented;
    private ObservableList<Movie> rentingHistory;

    public Customer(int ID, String name, int balance) {

        this.ID = ID;
        this.name = name;
        this.balance.set(balance);
        this.currentlyRented = FXCollections.observableArrayList();
        this.rentingHistory = FXCollections.observableArrayList();

    }

    @Override
    public String toString() {
        return ID + "\t" + name + "\t$ "+ balance.get();
    }

    public ObservableList<Movie> favouriteMovies() {

        ObservableList<Movie> favourites = FXCollections.observableArrayList();

        if (!rentingHistory.isEmpty()) {

            int[] favouriteCount = new int[rentingHistory.size()];

            for (Movie m : rentingHistory) {
                favouriteCount[rentingHistory.indexOf(m)]++;
            }

            int first = max(favouriteCount);

            if (first > -1) {
                favourites.add(this.rentingHistory.get(first));
                favouriteCount[first] = 0;
            }

            int second = max(favouriteCount);

            if (second > -1) {
                favourites.add(this.rentingHistory.get(second));
                favouriteCount[second] = 0;
            }

            int third = max(favouriteCount);

            if (third > -1) {
                favourites.add(this.rentingHistory.get(third));
            }
        }
        return favourites;
    }

    private int max(int[] a) {

        int max = Integer.MIN_VALUE;
        int maxPosition = -1;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0 && a[i] > max) {
                max = a[i];
                maxPosition = i;
            }
        }

        return maxPosition;
    }

   /* public void show() {
        
        System.out.println("ID: "+this.ID);
        System.out.println("Name: "+this.name);
        System.out.println("Balance: $"+this.balance);
        
    }
*/
    public void rentMovie(Movie movie) {

        //check balance
        currentlyRented.add(movie);
        rentingHistory.add(movie);
        

    }
   
    public void deductAmount(int amount) {
        this.balance.set(balance.get() - amount);
    }
    
    public void topUpAccount(int amount) {
        this.balance.set(balance.get() + amount);
    }
    
    public void returnMovie(Movie movie) {

        if (currentlyRented.contains(movie)) {
            currentlyRented.remove(movie);
        }

    }

    public boolean hasMovie(Movie movie) {
        return currentlyRented.contains(movie);
    }

    public boolean hasMovie(String title) {

        for (Movie m : this.currentlyRented) {
            if (m.titleMatches(title))
                return true;
        }

        return false;
    }

    public final int getId() {
        return this.ID;
    }

    public int getBalance() {
        return this.balance.get();
    }
    
    public ReadOnlyIntegerProperty balanceProperty(){
        return balance;
    }
    
    public final String getName() {
        return this.name;
    }

    public final ObservableList<Movie> currentlyRented() {
        return this.currentlyRented;
    }

    public final ObservableList<Movie> rentingHistory() {
        return this.rentingHistory;
    }

}
