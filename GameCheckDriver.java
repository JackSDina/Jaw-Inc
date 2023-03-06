import java.util.*;
import java.io.*;

/**
 * Driver class for GameCheck
 * @author jacks338, dinajs
 *
 */

public class GameCheckDriver {
	
	ArrayList<Game> gameArray = new ArrayList<Game>();
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Game> gameData = fillData("steamdb2.1.txt");
		System.out.println(gameData.size());
		System.out.println(gameData.get(0).getName() + " " + gameData.get(0).getRating());
		ArrayList<String> genreTest = convertGenres(gameData.get(0).getGenres());
		
		for (int i = 0; i < genreTest.size(); i++) {
			System.out.println(genreTest.get(i));
		}
	}
	
	/**
	 * Reads data from file one line at a time to add game objects to an arrayList
	 * @param fileName string file name containing the data, meant to read from a 3
	 * column format
	 * @return an arrayList containing all games listed in the file.
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Game> fillData(String fileName) throws FileNotFoundException {
		ArrayList<Game> gameData = new ArrayList();
		File rawData = new File(fileName);
		Scanner fileReader = new Scanner(rawData);
		fileReader.nextLine();
		while (fileReader.hasNextLine()) {
			String line = fileReader.nextLine();
			String[] separatedLine = new String[3];
			separatedLine = line.split("\t");
			Set<Integer> genres = genreReader(separatedLine[2]);
			Set<Integer> platforms = new HashSet<Integer>();
			int rating = Integer.parseInt(separatedLine[1]);
			gameData.add(new Game(separatedLine[0], 0.0f, "", genres, platforms, rating));
		}
		return gameData;
	}
	
	/**
	 * Converts the text string of 1s and 0s to an int code for the genres
	 * @param binaryCode string of 1s and 0s, representing a game's genre
	 * @return Int set that represent the genres that correspond to a game
	 */
	public static Set<Integer> genreReader(String binaryCode) {
		Set<Integer> genres = new HashSet<Integer>();
		for (int i = 0; i < 26; i++) {
			if (binaryCode.substring(i, i + 1).equals("1")) {
				genres.add(i);
			}
		}
		return genres;
	}
	
	/**
	 * This list converts the int code for genres into their respective genre strings
	 * @param valCode Set of integers representing genres in the list
	 * @return an arrayList of genre strings
	 */
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
	
	/**
	 * Search algorithm for finding games based on name
	 * @param name String representation of user's input
	 * @return an arrayList of Games
	 */
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
	
	/**
	 * Search algorithm for finding games based on publishers
	 * @param publisher String representation of user's input
	 * @return an arrayList of Games
	 */
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
	
	/**
	 * Search algorithm for finding games based on genre
	 * @param genre String representation of user's input
	 * @return an arrayList of Games
	 */
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

	/**
	 * Search algorithm for finding games based on platforms
	 * @param platforms String representation of user's input
	 * @return an arrayList of Games
	 */
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

	/**
	 * William
	 * for later: check genre and platform params
	 */
