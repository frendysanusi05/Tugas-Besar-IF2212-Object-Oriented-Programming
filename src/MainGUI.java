import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class MainGUI {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Simplicity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,500);

        /* Create panel at bottom */
        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Enter Input");
        JTextField textField1 = new JTextField(20);
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");

        /* Action listener */
        send.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                String input = textField1.getText();
                
            }  
        });
        
        /* Components added using flow layout */
        panel1.add(label1);
        panel1.add(textField1);
        panel1.add(send);
        panel1.add(reset);

        /* Text area at the center */
        JTextArea textArea1 = new JTextArea();

        /* Adding components to the frame */
        frame.getContentPane().add(BorderLayout.SOUTH, panel1);
        frame.getContentPane().add(BorderLayout.CENTER, textArea1);

        frame.setVisible(true);

        Main.main(args);
    }
}
