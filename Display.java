import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.util.Iterator;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
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
    private Font defaultFont = new Font("Helvetica", Font.PLAIN, 12);
    private int sortByCode = 0;

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
        setBounds(100, 100, 900, 500);
        //setResizable(false);
        contentPane = new JPanel();
        contentPane.setDoubleBuffered(true);
        contentPane.setForeground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        setLayout(new GridLayout());


        // Print title
        JLabel title = new JLabel("GameCheck");
        title.setForeground(new Color(148, 19, 19));
        title.setFont(new Font("Helvetica", Font.PLAIN, 24));
        //contentPane.add(title, BorderLayout.NORTH);


        // Setup and show panel for search query
        submitQuestionsPanel = new JPanel();
        //submitQuestionsPanel.setPreferredSize(new Dimension(25, 25));
        submitQuestionsPanel.setBackground(new Color(196, 165, 165));
        
        JLabel searchQuestion = new JLabel("Search for a game by name: ");
        searchQuestion.setFont(new Font("Helvetica", Font.BOLD, 15));
        JLabel publisherSearchOption = new JLabel("Publisher?");
        JLabel genreSearchOption = new JLabel("Genre?");
        JLabel platformSearchOption = new JLabel("Platform?");
        platformSearchOption.setFont(defaultFont);
        genreSearchOption.setFont(defaultFont);
        publisherSearchOption.setFont(defaultFont);
        
        
        JTextField search = new JTextField(20);
        JTextField publisher = new JTextField(15);
        JTextField genre = new JTextField(15);
        JTextField platform = new JTextField(15);
        JButton submitQuestion = new JButton("Search");
        submitQuestion.setFont(defaultFont);
        JButton submitParams = new JButton("Search by params");
        submitParams.setFont(defaultFont);
        JButton sortRating = new JButton("Sort all by rating");
        sortRating.setFont(defaultFont);
        JButton sortName = new JButton("Sort all by name");
        sortName.setFont(defaultFont);
        
        // Enables use of enter key to press submit button
        getRootPane().setDefaultButton(submitQuestion);

        // Add actionlisteners to buttons
        submitQuestion.addActionListener(e -> showResults(search.getText()));
        submitParams.addActionListener(e -> displayParamSearch(publisher.getText(), genre.getText(), platform.getText()));
        sortRating.addActionListener(e -> sortBy(0));
        sortName.addActionListener(e -> sortBy(1));
        
        // TODO: fix layout 💀💀💀💀💀💀💀💀
        submitQuestionsPanel.setLayout(new BoxLayout(submitQuestionsPanel, BoxLayout.PAGE_AXIS));
        
        submitQuestionsPanel.add(searchQuestion);
        submitQuestionsPanel.add(search);
        submitQuestionsPanel.add(submitQuestion);
        JLabel prompt = new JLabel("Or, filter games by these parameters:");
        prompt.setFont(new Font("Helvetica", Font.BOLD, 14));
        submitQuestionsPanel.add(prompt);
        submitQuestionsPanel.add(publisherSearchOption);
        submitQuestionsPanel.add(publisher);
        submitQuestionsPanel.add(genreSearchOption);
        submitQuestionsPanel.add(genre);
        submitQuestionsPanel.add(platformSearchOption);
        submitQuestionsPanel.add(platform);        
        submitQuestionsPanel.add(submitParams);
        JLabel sortInfo = new JLabel("Sort the list:");
        sortInfo.setFont(new Font("Helvetica", Font.BOLD, 14));
        submitQuestionsPanel.add(sortInfo);
        submitQuestionsPanel.add(sortName);
        submitQuestionsPanel.add(sortRating);
        
        
        //submitQuestionsPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        contentPane.add(submitQuestionsPanel, BorderLayout.NORTH);
        
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
        
//        boolean displayTable = true;
        
     
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
        
        // Set text based on result of search
        JTextArea displayGame = new JTextArea(23, 35);
        
        if (!input.isEmpty()) {
            // Search for game
            ArrayList<Game> selectedGames = GameCheckDriver.searchName(liveArray, input);
            if (!selectedGames.isEmpty()) {
                             
//                table = new JTable(model);
//                table.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
//                
//                model.addColumn("NAME");
//                model.addColumn("RATING");
//                model.addColumn("GENRES");              
                
                for (Game g : selectedGames) {
                    displayGame.append(g.toString() + "\n");
//                    model.addRow(new Object[] {g.getName(), g.getRating() + "%", g.getStringGenres()});
                }
            } else {
                displayGame.setText("Your search couldn't be found.");
                //displayTable = false;
            }
        } else {
            displayGame.setText("Please enter a search.");
            //            displayTable = false;
        }

        displayGame.setFont(new Font("Helvetica", Font.PLAIN, 14));
        displayGame.setEditable(false);
        displayGame.setLineWrap(true);
        displayGame.setWrapStyleWord(true);
        displayGame.setCaretPosition(0);
        searchResultPanel.add(new JScrollPane(displayGame, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.NORTH);

        //        if (displayTable) {
        //            table.setFillsViewportHeight(true);
        //            table.setRowHeight(80);
        //            searchResultPanel.add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.NORTH);
        //        } else {
        //            displayGame.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        //            displayGame.setEditable(false);
//            displayGame.setLineWrap(true);
//            displayGame.setWrapStyleWord(true);
//            displayGame.setCaretPosition(0);
//            searchResultPanel.add(new JScrollPane(displayGame, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.NORTH);
//        }
    
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
        
        JTextArea textArea = new JTextArea(23, 35);
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
            //finalList = GameCheckDriver.exclusiveTotalSearch(new ArrayList<Integer>(Arrays.asList(2)), inputArr, liveArray);
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
        //ArrayList<Game> sorted = GameCheckDriver.sortRating(finalList);
        
        if (finalList.isEmpty() && entered) {
            textArea.setText("No results found.");
        } else {
            ArrayList<Game> list = new ArrayList<Game>();
            list.addAll(finalList);
            if (sortByCode == 0) {
                list = GameCheckDriver.sortRating(list);
            } else {
                list = GameCheckDriver.sortName(list);
                
            }
//            Iterator<Game> finalListIterator = finalList.iterator();            
//            while(finalListIterator.hasNext()) {
//                textArea.append(finalListIterator.next().toString() + "\n");
//            }
            for (Game g : list) {
                textArea.append(g.toString() + "\n");
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
        
        JTextArea textArea = new JTextArea(23, 35);
        textArea.setFont(new Font("Helvetica", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //ArrayList<Game> sorted;
        if (code == 0) {
            liveArray = GameCheckDriver.sortRating(games);
            sortByCode = 0;
        } else {
            liveArray = GameCheckDriver.sortName(games);
            sortByCode = 1;
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

