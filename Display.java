import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GUI for game catalog
 * 
 * @author Jack McMaken
 * 
 */
public class Display extends JFrame {

    private ArrayList<Game> games;
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
        
        JLabel searchQuestion = new JLabel("What game would you like to search for?");
        searchQuestion.setFont(new Font("Helvetica", Font.BOLD, 12));
        JTextField search = new JTextField(35);
        JButton submitQuestion = new JButton("Search");
        JButton submitGenre = new JButton("Search genre");
        JButton sortRating = new JButton("Sort all by rating");
        JButton sortName = new JButton("Sort all by name");
        
        // Enables use of enter key to press submit button
        getRootPane().setDefaultButton(submitQuestion);

        // Add actionlisteners to buttons
        submitQuestion.addActionListener(e -> showResults(search.getText()));
        submitGenre.addActionListener(e -> displayGenreSearch(search.getText()));
        sortRating.addActionListener(e -> sortBy(0));
        sortName.addActionListener(e -> sortBy(1));

        submitQuestionsPanel.add(searchQuestion, BorderLayout.CENTER);
        submitQuestionsPanel.add(search, BorderLayout.AFTER_LAST_LINE);
        submitQuestionsPanel.add(submitQuestion);
        submitQuestionsPanel.add(submitGenre);
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
        ArrayList<Game> selectedGames = GameCheckDriver.searchName(games, input);
        
        // Set text based on result of search
        JTextArea displayGame = new JTextArea(10, 40);
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
     * Generates and displays results of genre search
     */
    private void displayGenreSearch(String genre) {
        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
        
        JTextArea textArea = new JTextArea(15, 40);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
        
        boolean appended = false;
        if (genre.isEmpty()) {
            textArea.setText("You didn't enter anything.");
        } else {
            for (int i = 0; i < games.size(); i++) {
                ArrayList<String> genres = GameCheckDriver.convertGenres(games.get(i).getGenres());
                List<String> lower = genres.stream().map(String::toLowerCase).collect(Collectors.toList());
                if (lower.contains(genre.toLowerCase())) {
                    textArea.append(games.get(i).toString() +"\n");
                    appended = true;
                }
            }
        }
        if (!appended && !genre.isEmpty()) {
            textArea.setText("Genre not found.");
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
        ArrayList<Game> sorted;
        if (code == 0) {
            sorted = GameCheckDriver.sortRating(games);
        } else {
            sorted = GameCheckDriver.sortName(games);
        }
        
        for (int i = 0; i < sorted.size(); i++) {
            textArea.append(i + 1 + ". " + sorted.get(i).toString() +"\n");
        }
        
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        
        searchResultPanel.add(scroll);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();

    }
    
    
    
 // Use steamdb file to display games   (temp function) 
    //        String all = null;
    //        try {
    //            all = new Scanner(new File("steamdb2.1.txt")).useDelimiter("\\A").next();
    //        } catch (FileNotFoundException e) {
    //            e.printStackTrace();
    //        }
    //        JTextArea textArea = new JTextArea(10, 20);
    //        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    //        textArea.setText(all);
    //        textArea.setLineWrap(true);
    //        textArea.setWrapStyleWord(true);
    //        textArea.setCaretPosition(0);
    //        contentPane.add(scroll);


}

