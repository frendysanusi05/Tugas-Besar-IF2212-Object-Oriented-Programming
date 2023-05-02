package Main;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class Loading extends JFrame {
    public Loading(){
        // Create ImageIcon objects from image files
        ImageIcon bigLogo = new ImageIcon("Res/background.png");
        ImageIcon smallLogo = new ImageIcon("Res/background.png");


        // Create a border object
        Border borderWelcomingLabel = BorderFactory.createLineBorder(Color.GREEN, 3);

        // Create a new JLabel object and set its properties
        JLabel welcomingLabel = new JLabel();
        welcomingLabel.setText("Loading...");
        welcomingLabel.setIcon(bigLogo);
        welcomingLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomingLabel.setVerticalTextPosition(JLabel.TOP);
        welcomingLabel.setForeground(new Color(0x000000));
        welcomingLabel.setFont(welcomingLabel.getFont().deriveFont(20.0f));
        welcomingLabel.setBackground(Color.WHITE);
        welcomingLabel.setOpaque(true);
        welcomingLabel.setBorder(borderWelcomingLabel);
        welcomingLabel.setVerticalAlignment(JLabel.CENTER);
        welcomingLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create a JProgressBar object and set its properties
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); // Set the progress bar to indeterminate mode
        progressBar.setPreferredSize(new Dimension(300, 30)); // Set the size of the progress bar
        progressBar.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Set the border of the progress bar

        // Create a JPanel object to contain the welcomingLabel and progressBar
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.add(welcomingLabel, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.SOUTH);

        // Set the icon and background color of the frame
        this.setIconImage(smallLogo.getImage());
        this.setTitle("Loading...");
        this.getContentPane().setBackground(new Color(0x000000));

        // Set the properties of the frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(768, 768);

        // Set the frame to be centered
        this.setLocationRelativeTo(null);

        this.setVisible(true);

        // Add the panel to the frame
        this.add(panel);
    }

    public static void main(String[] args) {
        new Loading();
    }
}
