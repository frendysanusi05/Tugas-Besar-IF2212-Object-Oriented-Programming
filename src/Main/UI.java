package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class UI {

    GameManager gm;

    JFrame window;
    public JTextArea messageText;
    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];

    public UI(GameManager gm){
        this.gm = gm;

        createMainField();
        
        //generateScreen();
        window.setVisible(true);
    }

    public void createMainField(){
        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
    }

    public void createBackground(int bgNum, String bgFileName){
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50, 50, 700, 350);
        bgPanel[bgNum].setBackground(null);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[1]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 700, 350);

        ImageIcon bgIcon = new ImageIcon("Res/background.png");
        bgLabel[bgNum].setIcon(bgIcon);
    }

    public void createBackground(int bgNum, String bgPath, MouseListener mouse) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(0, 0, 700, 700);
        bgPanel[bgNum].setBackground(null);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0, 0, 700, 700);
        ImageIcon bgIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("Res/background.png")).getImage()
                .getScaledInstance(700, 700, Image.SCALE_SMOOTH));
        bgLabel[bgNum].setIcon(bgIcon);
        bgLabel[bgNum].addMouseListener(mouse);
        bgPanel[bgNum].add(bgLabel[bgNum]);
        bgPanel[bgNum].setVisible(false);
    }
    public void createObject(int bgNum, int objx, int objy, int width, int height, String objName, String ChoiceName){
        // create pop menu
        JPopupMenu popMenu = new JPopupMenu();
        // create pop menu items
        JMenuItem menuItem[] = new JMenuItem[4];
        menuItem[1] = new JMenuItem("Shop");
        //menuItem[1].addActionListener(gm.aHandler);
        menuItem[1].setActionCommand(ChoiceName);
        popMenu.add(menuItem[1]);
        
        // create object
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objx, objy, width, height);

        ImageIcon objectIcon = new ImageIcon("Res/background.png");
        objectLabel.setIcon(objectIcon);

        objectLabel.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)){
                    popMenu.show(objectLabel, e.getX(), e.getY());
                }
            }
            public void mouseReleased(MouseEvent e) {
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
        });
        
        bgPanel[bgNum].add(objectLabel);
        bgPanel[bgNum].add(bgLabel[bgNum]);
    }

    public void customButton(JPanel panel, int x, int y, int width, int height, String text, int fontSize,
            String command) {
        JButton btn = new JButton();
        btn.setBounds(x, y, width, height);
        btn.setText(text);
        btn.setForeground(new Color(7, 168, 104));
        btn.setFocusPainted(false);
        //btn.addActionListener(gm.actionHandler);
        btn.setActionCommand(command);
        btn.setFont(new Font("Helvetica", Font.BOLD, fontSize));
        panel.add(btn, 0);
    }

    //public void generateScreen(){}
}