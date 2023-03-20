import java.util.*;
import java.awt.EventQueue;
import java.io.*;

/**
 * Driver class for GameCheck
 * @author djax1, dinajs
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
		
		EventQueue.invokeLater(new Runnable() {
            	public void run() {
                	try {
                    Display frame = new Display(gameData);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
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
    static ArrayList<Game> searchName (ArrayList<Game> g, String name) {
        ArrayList<Game> newArray = new ArrayList<Game>();
        name = name.toLowerCase();
        for (int i = 0; i < g.size(); ++i) {
            if (g.get(i).getName().equalsIgnoreCase(name)) {
                newArray.add(g.get(i));
            } else if(g.get(i).getName().toLowerCase().contains(name)) {
                newArray.add(g.get(i)); 
            }
        }
        
        for(int i = 0; i < newArray.size(); ++i) {
            System.out.println(newArray.get(i).toString());
        }
        return newArray;
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
//        for (int i = 1; i < gameArray.size(); ++i) {
//            int key = gameArray.get(i).getRating();
//            int j = i - 1;
//
//            while (j >= 0 && gameArray.get(j).getRating() > key) {
//            	gameArray.set(j+1, gameArray.get(j));
//                j = j - 1;
//            }
//            gameArray.get(j+1).setRating(key);
//        }
		// Heapify function is called for the items in
		// the array.
		for (int i = gameArray.size()/2 - 1; i >= 0; --i) {
			heapify(gameArray, gameArray.size(), i, 1);
		}
		
		// Swaps each item in the array.
		for (int i = gameArray.size()-1; i > 0; --i) {
			swap(gameArray, i, 0);
			heapify(gameArray, i, 0, 1);
		}
	}
	
	void sortPrice () {
		//TODO Maybe
//		for (int i = 1; i < gameArray.size(); ++i) {
//            float key = gameArray.get(i).getPrice();
//            int j = i - 1;
// 
//            while (j >= 0 && gameArray.get(j).getPrice() > key) {
//            	gameArray.set(j+1, gameArray.get(j));
//                j = j - 1;
//            }
//            gameArray.get(j+1).setPrice(key);
//        }
		for (int i = gameArray.size()/2 - 1; i >= 0; --i) {
			heapify(gameArray, gameArray.size(), i, 2);
		}
		
		// Swaps each item in the array.
		for (int i = gameArray.size()-1; i > 0; --i) {
			swap(gameArray, i, 0);
			heapify(gameArray, i, 0, 2);
		}

	}
	
	void sortName() {
		gameArray.sort((o1, o2)
				-> o1.getName().compareTo(o2.getName()));
	}
	
	void swap (ArrayList<Game> gameArray, int next, int previous) {
		Game temp = new Game(gameArray.get(previous));
		gameArray.set(previous, gameArray.get(next));
		gameArray.set(next, temp);
	}
	
	void heapify (ArrayList<Game> gameArray, int size, int i, int sortType) {
		// Initializing variables needed for
		// tree creation.
		int root = i;
		int right = 2*i+2;
		int left = 2*i+1;
		
		if (sortType == 1) {
			// Checks to see if the left or right
			// is larger then the root.
			if ((right < size) && (gameArray.get(right).getRating() > gameArray.get(root).getRating())) {
				root = right;
			}
			if ((left < size) && (gameArray.get(left).getRating() > gameArray.get(root).getRating())) {
				root = left;
			}
			
			// Checks to make sure the largest is root.
			if (root != i) {
				swap(gameArray, root, i);
				
				// Heapifies the subtrees.
				heapify(gameArray, size, root, sortType);
			}
		} else if (sortType == 2) {
			// Checks to see if the left or right
			// is larger then the root.
			if ((right < size) && (gameArray.get(right).getPrice() > gameArray.get(root).getPrice())) {
				root = right;
			}
			if ((left < size) && (gameArray.get(left).getPrice() > gameArray.get(root).getPrice())) {
				root = left;
			}
			
			// Checks to make sure the largest is root.
			if (root != i) {
				swap(gameArray, root, i);
				
				// Heapifies the subtrees.
				heapify(gameArray, size, root, sortType);
			}
		}
		
	}
}

	/**
	 * William
	 * for later: check genre and platform params
	 */
