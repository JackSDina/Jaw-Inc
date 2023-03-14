import java.awt.BorderLayout;
import java.awt.Font;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Starting GUI for game catalog
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
    public Display(ArrayList<Game> games) {      // Suggestion: take array of Game objects as arg and get game instances variables to display info
        this.games = games;

        // Content pane setup
        setTitle("GameCheck");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setDoubleBuffered(true);
        contentPane.setForeground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);


        // Print title
        JLabel title = new JLabel("GameCheck");
        title.setForeground(Color.RED);
        title.setFont(new Font("Helvetica", Font.PLAIN, 24));
        contentPane.add(title, BorderLayout.NORTH);


        // Setup and show panel for search query
        submitQuestionsPanel = new JPanel();
        submitQuestionsPanel.setPreferredSize(new Dimension(50, 50));
        submitQuestionsPanel.setBackground(new Color(196, 165, 165));
        JLabel searchQuestion = new JLabel("What game would you like to search for?");
        JTextField search = new JTextField(35);
        JButton submitQuestion = new JButton("Search");
        JButton sortRating = new JButton("Sort all by rating");
        JButton sortName = new JButton("Sort all by name");
        //submitQuestion.setFocusPainted(false);
        getRootPane().setDefaultButton(submitQuestion);



        submitQuestion.addActionListener(e -> showResults(search.getText()));
        sortRating.addActionListener(e -> displayRatingSort());
        sortName.addActionListener(e -> displayNameSort());

        submitQuestionsPanel.add(searchQuestion, BorderLayout.CENTER);
        submitQuestionsPanel.add(search, BorderLayout.AFTER_LAST_LINE);
        submitQuestionsPanel.add(submitQuestion);
        submitQuestionsPanel.add(sortName);
        submitQuestionsPanel.add(sortRating);
        contentPane.add(submitQuestionsPanel);
        searchResultPanel = new JPanel();
        contentPane.add(searchResultPanel, BorderLayout.AFTER_LAST_LINE);



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

    /*
     * Generates results after submit button pressed
     */
    private void showResults(String s) {
        input = s;

        //        searchResultPanel.revalidate();
        //        searchResultPanel.repaint();
        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();


        // Setup and show panel for search results
        Game selectedGame = null;
        for (Game g : games) {
            if (g.getName().toLowerCase().equals(input.toLowerCase())) {
                selectedGame = g;
                break;
            }
        }

        JLabel displayGame = new JLabel();  
        if (!input.equals("")) {
            if (selectedGame != null) {
                ArrayList<String> genres = GameCheckDriver.convertGenres(selectedGame.getGenres());
                displayGame = new JLabel(selectedGame.getName() + "     " + selectedGame.getRating() + "%     " + genres.toString());
            } else {
                displayGame = new JLabel("Your search couldn't be found.");
            }
        } else {
            displayGame = new JLabel("Please enter a search.");
        }
        displayGame.setFont(new Font("Helvetica", Font.PLAIN, 12));

        searchResultPanel.add(displayGame);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();



        System.out.println(input);            
    }

    private void displayRatingSort() {
        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
        
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        String listString = gameCheckDriver.gameArray.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
        ArrayList<Game> sorted = GameCheckDriver.sortRating(games);
        String all = "";
        for (int i = 0; i < sorted.size(); i++) {
            all += sorted.get(i).toString() +"\n";
        }
        
        textArea.setText(all);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        
        searchResultPanel.add(scroll);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();

    }
    
    private void displayNameSort() {
        searchResultPanel.removeAll();
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
        
        JTextArea textArea = new JTextArea(10, 40);
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        String listString = gameCheckDriver.gameArray.stream().map(Object::toString)
//                .collect(Collectors.joining(", "));
        textArea.setFont(new Font(Font.SERIF, Font.PLAIN, 14));
        ArrayList<Game> sorted = GameCheckDriver.sortName(games);
        String all = "";
        for (int i = 0; i < sorted.size(); i++) {
            all += sorted.get(i).toString() +"\n";
        }
        
        textArea.setText(all);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCaretPosition(0);
        
        searchResultPanel.add(scroll);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();

    }


}

