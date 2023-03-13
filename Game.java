import java.util.*;

/*
 * Constructor for the game object for the GameCheck project.
 * Written by Andrew Jackson for CSE201, jacks338.
 */

public class Game {

    String name;
    float price;
    String publisher;
    Set<Integer> genres;
    Set<Integer> platforms;
    int rating;

    /**
     * Constructor for the game object.
     * @param n Name string.
     * @param pr Price float.
     * @param pb Publisher string.
     * @param g Set of integers representing game genres.
     * @param pl Set of integers representing platforms.
     * @param r Rating integer.
     */
    public Game(String n, float pr, String pb, Set<Integer> g, Set<Integer> pl,
    int r) {
        name = n;
        price = pr;
        genres = g;
        platforms = pl;
        rating = r;
    }
	
    public Game(Game game) {
    	this.name = game.name;
    	this.price = game.price;
    	this.genres = game.genres;
    	this.platforms = game.platforms;
    	this.rating = game.rating;
    }

   // this method is used for the to string method
   public static ArrayList<String> convertGenres(Set<Integer> valCode) {
		// Empty list to be filed with valid genres
		ArrayList<String> genreList = new ArrayList<String>();
		// Reference list containing all genres in order according to their code
		ArrayList<String> genres = new ArrayList<String>(Arrays.asList("Indie", "Profile Features Limited", "Action",
				"Casual", "Singleplayer", "Adventure", "Simulation", "Strategy", "RPG", "2D", "Atmospheric", "3D",
				"Puzzle", "Pixel Graphics", "Fantasy", "Story Rich", "Colorful", "Exploration", "Free to Play", "Cute",
				"Multiplayer", "Early Access", "First-Person", "Arcade", "Anime", "Shooter"));
		for (int i = 0; i < 26; i++) {
			if (valCode.contains(i)) {
				genreList.add(genres.get(i));
			}
		}
		return genreList;
	}
	
	public String toString() {
        String fullObj = name + " Rating: " + rating + " Genre Tags:";
        ArrayList<String> genreList = convertGenres(genres);
        for (int i = 0; i < genreList.size() - 1; i++) {
			fullObj += " " + genreList.get(i) + ",";
		}
        fullObj += " " + genreList.get(genreList.size() - 1);
        return fullObj;
    }
    
    // Auto generated getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Set<Integer> getGenres() {
        return genres;
    }

    public void setGenres(Set<Integer> genres) {
        this.genres = genres;
    }

    public Set<Integer> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Set<Integer> platforms) {
        this.platforms = platforms;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Game)) {
            return false;
        }
        
        Game g = (Game)o;
        return this.name.equals(g.name);
    }

}
