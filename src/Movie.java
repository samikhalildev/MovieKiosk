
public class Movie {
	
	private String title;
	private int year;
	private int price;
	private Genre genre;
	private int counter;
	
	public Movie(String title, int year, Genre genre, int price){
		this.title = title;
		this.price = price;
		this.year = year;
		this.genre = genre;
	}
	
	public void setCount(int count){
		counter += count;
	}
	
	public int getCount(){
		return counter;
	}
	
	public String getName(){
		return title;	
	}
	
	public int getPrice(){
		return price;
	}

	public boolean hasMovieName(String title){
		return (this.title.toLowerCase().trim().equals(title.toLowerCase()));
	}
	
	public boolean hasMovieYear(int year){
		return (this.year == year);
	}
	
	public boolean hasMovieGenre(Genre genre){
		return (this.genre.equals(genre));
	}
	
	@Override
	public String toString(){
		return year + "\t" + title + "\t" + genre + "\t$" + price;
	}
}
