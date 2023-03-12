import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.*;

/**
 * Starting GUI for game catalog
 * 
 * @author Jack McMaken
 * 
 */
public class Display extends JFrame {
    
    private ArrayList<Game> games;
    private JPanel contentPane;
    //private JScrollBar scrollbar;
    private String input;
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
        JPanel submitQuestionsPanel = new JPanel();
        JLabel searchQuestion = new JLabel("What game would you like to search for?");
        JTextField search = new JTextField(35);
        JButton submitQuestion = new JButton("Search");
        getRootPane().setDefaultButton(submitQuestion);
        
//        search.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                System.out.println("The entered text is: " + search.getText());
//            }
//        });
        
//        search.addKeyListener(new KeyAdapter() {
//            public void keyReleased(KeyEvent event) { 
//                        String content = search.getText();
//                        if (!content.equals("")) {
//                            submitQuestion.setEnabled(true);
//                        } else {
//                            submitQuestion.setEnabled(false);
//                        }
//                    }
//        });
        
        submitQuestion.addActionListener(e -> showResults(search.getText()));
        
        submitQuestionsPanel.add(searchQuestion, BorderLayout.CENTER);
        submitQuestionsPanel.add(search, BorderLayout.AFTER_LAST_LINE);
        submitQuestionsPanel.add(submitQuestion);
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
        
        // Setup and show panel for search results
//        searchResultPanel.setDoubleBuffered(true);
        Game selectedGame = null;
        for (Game g : games) {
            if (g.getName().toLowerCase().equals(s.toLowerCase())) {
                selectedGame = g;
                break;
            }
        }
        System.out.println(games.get(0).getName());
        
        JLabel displayGame;
        if (selectedGame != null) {
            ArrayList<String> genres = GameCheckDriver.convertGenres(selectedGame.getGenres());
            displayGame = new JLabel(selectedGame.getName() + "     " + selectedGame.getRating() + "%     " + genres.toString());
        } else {
            displayGame = new JLabel("Your search couldn't be found.");
        }
        
        //JPanel contentPane2 = new JPanel();
        searchResultPanel.add(displayGame);
        //contentPane.add(searchResultPanel, BorderLayout.CENTER);
//        contentPane.repaint();
//        SwingUtilities.updateComponentTreeUI(this);
//        setVisible(false);
//        dispose();
//        setVisible(true);
        searchResultPanel.revalidate();
        searchResultPanel.repaint();
        
        System.out.println(input);
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Main creates and invokes runnable
     * 
     * @param args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Display frame = new Display();
                    frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    

}

