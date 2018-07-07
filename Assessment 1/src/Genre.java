
public class Genre {
	
	private String name;

	public Genre(String name){
		this.name = name;
	}
	
	public boolean hasGenre(String genre){
		return (name.toLowerCase().trim().equals(genre.toLowerCase()));
	}
	
	@Override
	public String toString(){
		return name;
	}
}