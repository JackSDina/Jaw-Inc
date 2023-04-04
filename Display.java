import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.util.Iterator;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * GUI for game catalog
 * 
 * @author Jack McMaken
 * 
 */
public class Display extends JFrame {

    private ArrayList<Game> games;
    private ArrayList<Game> liveArray;
    private JPanel contentPane;
    private String input;
    private JPanel submitQuestionsPanel;
    private JPanel searchResultPanel;

    /**
     * Generates JFrame with all necessary components
     * 
     * @param The array of games
     */
    @SuppressWarnings("resource")
    public Display(ArrayList<Game> games) {
        this.games = games;
        this.liveArray = games;

        // Content pane setup
        setTitle("GameCheck");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        //setResizable(false);
        contentPane = new JPanel();
        contentPane.setDoubleBuffered(true);
        contentPane.setForeground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        // Print title
        JLabel title = new JLabel("GameCheck");
        title.setForeground(new Color(148, 19, 19));
        title.setFont(new Font("Helvetica", Font.PLAIN, 24));
        contentPane.add(title, BorderLayout.NORTH);


        // Setup and show panel for search query
        submitQuestionsPanel = new JPanel();
        submitQuestionsPanel.setPreferredSize(new Dimension(50, 50));
        submitQuestionsPanel.setBackground(new Color(196, 165, 165));
        
        JLabel searchQuestion = new JLabel("Search for a game by name: ");
        JLabel publisherSearchOption = new JLabel("Publisher?");
        JLabel genreSearchOption = new JLabel("Genre?");
        JLabel platformSearchOption = new JLabel("Platform?");
        
        
        searchQuestion.setFont(new Font("Helvetica", Font.BOLD, 12));
        JTextField search = new JTextField(35);
        JTextField publisher = new JTextField(20);
        JTextField genre = new JTextField(20);
        JTextField platform = new JTextField(20);
        JButton submitQuestion = new JButton("Search");
        JButton submitParams = new JButton("Search by params");
        JButton sortRating = new JButton("Sort all by rating");
        JButton sortName = new JButton("Sort all by name");
        
        // Enables use of enter key to press submit button
        getRootPane().setDefaultButton(submitQuestion);

        // Add actionlisteners to buttons
        submitQuestion.addActionListener(e -> showResults(search.getText()));
        submitParams.addActionListener(e -> displayParamSearch(publisher.getText(), genre.getText(), platform.getText()));
        sortRating.addActionListener(e -> sortBy(0));
        sortName.addActionListener(e -> sortBy(1));
        
        // TODO: fix layout
        submitQuestionsPanel.add(searchQuestion, BorderLayout.CENTER);
        submitQuestionsPanel.add(search, BorderLayout.AFTER_LAST_LINE);
        submitQuestionsPanel.add(publisherSearchOption);
        submitQuestionsPanel.add(publisher);
        submitQuestionsPanel.add(genreSearchOption);
        submitQuestionsPanel.add(genre);
        submitQuestionsPanel.add(platformSearchOption);
        submitQuestionsPanel.add(platform);
        submitQuestionsPanel.add(submitQuestion);
        submitQuestionsPanel.add(submitParams);
        submitQuestionsPanel.add(sortName);
        submitQuestionsPanel.add(sortRating);
        
        contentPane.add(submitQuestionsPanel);
        
        // Prepare search result panel
        searchResultPanel = new JPanel();
        contentPane.add(searchResultPanel, BorderLayout.AFTER_LAST_LINE);


    }

    /*
     * Generates and displays search results
     */
    private void showResults(String s) {
        input = s;

        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();

        // Search for game
        ArrayList<Game> selectedGames = GameCheckDriver.searchName(liveArray, input);
        
        // Set text based on result of search
        JTextArea displayGame = new JTextArea(12, 40);
        if (!input.isEmpty()) {
            if (!selectedGames.isEmpty()) {
                for (Game g : selectedGames) {
                    displayGame.append(g.toString() + "\n");
                }
            } else {
                displayGame.setText("Your search couldn't be found.");
            }
        } else {
            displayGame.setText("Please enter a search.");
        }
        
        displayGame.setFont(new Font("Helvetica", Font.PLAIN, 14));
        displayGame.setEditable(false);
        displayGame.setLineWrap(true);
        displayGame.setWrapStyleWord(true);
        displayGame.setCaretPosition(0);
        
        searchResultPanel.add(new JScrollPane(displayGame, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.NORTH);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();

        System.out.println(input);            
    }
    
    /*
     * Generates and displays results of param search
     */
    private void displayParamSearch(String publisher, String genre, String platform) {
        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
        
        JTextArea textArea = new JTextArea(15, 40);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        
        boolean entered = false;
        Set<Game> finalList = new HashSet<Game>();
        ArrayList<Object> inputArr = new ArrayList<Object>(Arrays.asList(publisher, genre, platform));
        if (genre.isEmpty() && publisher.isEmpty() && platform.isEmpty()) {
            textArea.setText("You didn't enter anything.");
        } else if (publisher.isEmpty() && platform.isEmpty()) {  // find only genre
            entered = true;
            finalList = GameCheckDriver.totalSearch(new ArrayList<Integer>(Arrays.asList(2)), inputArr, liveArray);
        } else if (genre.isEmpty() && platform.isEmpty()) { // find only publisher 
            entered = true;
            finalList = GameCheckDriver.totalSearch(new ArrayList<Integer>(Arrays.asList(1)), inputArr, liveArray);
        } else if (genre.isEmpty() && publisher.isEmpty()) { // find only platform
            entered = true;
            finalList = GameCheckDriver.totalSearch(new ArrayList<Integer>(Arrays.asList(3)), inputArr, liveArray);
        } else if (platform.isEmpty()) { // find publisher and genre
            entered = true;
            finalList = GameCheckDriver.totalSearch(new ArrayList<Integer>(Arrays.asList(1, 2)), inputArr, liveArray);
        } else if (genre.isEmpty()) { // find publisher and platform
            entered = true;
            finalList = GameCheckDriver.totalSearch(new ArrayList<Integer>(Arrays.asList(1, 3)), inputArr, liveArray);
        } else if (publisher.isEmpty()) { // find genre and platform
            entered = true;
            finalList = GameCheckDriver.totalSearch(new ArrayList<Integer>(Arrays.asList(2, 3)), inputArr, liveArray);
        } else { // find publisher, genre, and platform
            entered = true;
            finalList = GameCheckDriver.totalSearch(new ArrayList<Integer>(Arrays.asList(1, 2, 3)), inputArr, liveArray);
        }
        
        if (finalList.isEmpty() && entered) {
            textArea.setText("No results found.");
        } else {
            Iterator<Game> finalListIterator = finalList.iterator();            
            while(finalListIterator.hasNext()) {
                textArea.append(finalListIterator.next().toString() + "\n");
            }
        }
        
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        
        searchResultPanel.add(scroll);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
    }
    
    /*
     * Sorts and displays games given a code to determine which parameter to sort by
     */
    private void sortBy(int code) {
        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
        
        JTextArea textArea = new JTextArea(15, 40);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //ArrayList<Game> sorted;
        if (code == 0) {
            liveArray = GameCheckDriver.sortRating(games);
        } else {
            liveArray = GameCheckDriver.sortName(games);
        }
        
        for (int i = 0; i < liveArray.size(); i++) {
            textArea.append(i + 1 + ". " + liveArray.get(i).toString() +"\n");
        }
        
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        
        searchResultPanel.add(scroll);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();

    }

}

