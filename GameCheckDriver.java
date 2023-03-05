import java.util.*;

public class GameCheckDriver {
	
	ArrayList<Game> gameArray = new ArrayList<Game>();
	public static void main(String[] args) {
	}
	
	// Search algorithm for name.
	void searchName (String name) {
		ArrayList<Game> newArray = new ArrayList<Game>();
		
		for (int i = 0; i < gameArray.size(); ++i) {
			if (gameArray.get(i).getName().equalsIgnoreCase(name)) {
				newArray.add(gameArray.get(i));
			}
		}
		for(int i = 0; i < newArray.size(); ++i) {
			System.out.println(newArray.get(i).toString());
		}
	}
	
	void searchPublisher (String publisher) {
		ArrayList<Game> newArray = new ArrayList<Game>();
		
		for (int i = 0; i < gameArray.size(); ++i) {
			if (gameArray.get(i).getPublisher().equalsIgnoreCase(publisher)) {
				newArray.add(gameArray.get(i));
			}
		}
		for(int i = 0; i < newArray.size(); ++i) {
			System.out.println(newArray.get(i).toString());
		}
	}
	
	void searchGenre (int genre) {
		ArrayList<Game> newArray = new ArrayList<Game>();
		
		for (int i = 0; i < gameArray.size(); ++i) {
			if (gameArray.get(i).getGenres().contains(genre)) {
				newArray.add(gameArray.get(i));
			}
		}
		for(int i = 0; i < newArray.size(); ++i) {
			System.out.println(newArray.get(i).toString());
		}
	}
	
	void searchPlatforms (int platforms) {
		ArrayList<Game> newArray = new ArrayList<Game>();
		
		for (int i = 0; i < gameArray.size(); ++i) {
			if (gameArray.get(i).getPlatforms().contains(platforms)) {
				newArray.add(gameArray.get(i));
			}
		}
		for(int i = 0; i < newArray.size(); ++i) {
			System.out.println(newArray.get(i).toString());
		}
	}
	
	void sortRating() {
		//TODO Maybe
        for (int i = 1; i < gameArray.size(); ++i) {
            int key = gameArray.get(i).getRating();
            int j = i - 1;

            while (j >= 0 && gameArray.get(j).getRating() > key) {
            	gameArray.set(j+1, gameArray.get(j));
                j = j - 1;
            }
            gameArray.get(j+1).setRating(key);
        }
	}
	
	void sortPrice () {
		//TODO Maybe
		for (int i = 1; i < gameArray.size(); ++i) {
            float key = gameArray.get(i).getPrice();
            int j = i - 1;
 
            while (j >= 0 && gameArray.get(j).getPrice() > key) {
            	gameArray.set(j+1, gameArray.get(j));
                j = j - 1;
            }
            gameArray.get(j+1).setPrice(key);
        }

	}
	
	void sortName() {
		gameArray.sort((o1, o2)
				-> o1.getName().compareTo(o2.getName()));
	}
}
