import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;

/*
 * Starting GUI for game catalog
 * 
 */
public class Display extends JFrame {

    private JPanel contentPane;
    private JScrollBar scrollbar;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Display frame = new Display();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Display() {      // Suggestion: take array of Game objects as arg and get game instances variables to display info

        // Content pane setup
        setTitle("GameCheck");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 182, 193));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Scrollbar setup
        scrollbar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
        JLabel scrolling = new JLabel();
        class MyAdjustmentListener implements AdjustmentListener {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                scrolling.setText("Slider's position is " + e.getValue());
                repaint();
            }
        }
        scrollbar.addAdjustmentListener(new MyAdjustmentListener());
        getContentPane().add(scrolling);
        getContentPane().add(scrollbar, BorderLayout.EAST);
        
        // Setup display grid
        GridLayout grid = new GridLayout(100, 600);

        // Print title
        JLabel title = new JLabel("GameCheck");
        title.setForeground(Color.RED);
        title.setFont(new Font("Helvetica", Font.PLAIN, 24));
        contentPane.add(title, BorderLayout.NORTH);

        




    }

}
