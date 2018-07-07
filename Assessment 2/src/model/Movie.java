package model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {

    private final StringProperty title = new SimpleStringProperty();
    private final int year;
    private final int price;  
    private final Genre genre;

    public Movie(String title, int year, Genre genre, int price) {
        this.title.set(title);
        this.genre = genre;
        this.year = year;
        this.price = price;
    }

    public final Genre getGenre() {
        return this.genre;
    }

    public final int getPrice() {
        return this.price;
    }
    
    public final int getYear() {
        return this.year;
    }
    
    public final String getTitle() {
        return this.title.get();
    }
    
    public ReadOnlyStringProperty titleProperty(){
        return title;
    }

    public boolean titleMatches(String title) {

        return this.title.get().toLowerCase().equals(title);
    }
    
    public boolean yearMatches(int year) {

        return this.year == year;
    }

    @Override
    public String toString() {
        return this.year +"\t"+this.title.get() + "\t" +this.genre.toString() + "\t$" + this.price;
    }

}
