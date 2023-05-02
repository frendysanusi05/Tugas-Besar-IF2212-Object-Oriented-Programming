import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class GUIDriver {

    public static void main(String[] args) {

        MasterGame mg = new MasterGame();
        GUI gui = new GUI(mg);

        // Mouse listener for background image
        MouseListener bgListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        };

        // Set up main background
        gui.createMainBackground();

        // Set up background 1
        gui.createBackground(1, "src\\resource\\background.png", bgListener);

        // Set up background 2
        gui.createBackground(2, "src\\resource\\background.png", bgListener);

        // Set up attribute field
        gui.attributeField();

        // Show main background
        gui.bgPanel[0].setVisible(true);
    }
}