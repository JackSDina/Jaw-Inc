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
