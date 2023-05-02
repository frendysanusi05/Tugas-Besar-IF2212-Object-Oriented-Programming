// package

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.util.stream.Collectors;

import src.Utama.*;
import src.Utama.Ruangan;

public class GUI {
    //background
    public MasterGame mg;
    private JFrame window;
    JPanel bgPanel[] = new JPanel[10];
    private JLabel bgLabel[] = new JLabel[10];
    public JPanel attributePanel;
    public JPanel textPanel;
    public JTextArea messagText;
    public JLabel jamText;
    public JLabel hariText;
    public JLabel nameText;
    public JLabel pekerjaanText;
    public JLabel uangText;
    public JLabel moodText;
    public JLabel kesehatanText;
    public JLabel kekenyanganText;
    public JFrame popInventory;

    public GUI(MasterGame mg) {
        this.mg = mg;
        //mg.load();
        createMainField();
        //generateScreen();
        attributeField();
        window.setVisible(true);
    }

    public void createMainField(){
        window = new JFrame();
        window.setSize(1000, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setResizable(false);
    }

    public void createMainBackground(int bgNum, String bgPath){
        bgPanel[0] = new JPanel();
        bgPanel[0].setBounds(0, 0, 1000, 800);
        bgPanel[0].setBackground(Color.black);
        bgPanel[0].setLayout(null);
        window.add(bgPanel[0]);

        bgLabel[0] = new JLabel();
        bgLabel[0].setBounds(0, 0, 1000, 800);

        ImageIcon bgIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("/Resource/background.png")).getImage()
                .getScaledInstance(1000, 800, Image.SCALE_SMOOTH));
        bgLabel[0].setIcon(bgIcon);
        bgPanel[0].add(bgLabel[0]);
        bgPanel[0].setVisible(false);
    }

    public void createBackground(int bgNum, String bgPath ,MouseListener mouse) {
        bgPanel[0] = new JPanel();
        bgPanel[0].setBounds(0, 0, 700, 700);
        bgPanel[0].setBackground(null);
        bgPanel[0].setLayout(null);
        window.add(bgPanel[0]);

        bgLabel[0] = new JLabel();
        bgLabel[0].setBounds(0, 0, 700, 700);
        ImageIcon bgIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("/Resource/background.png")).getImage()
                .getScaledInstance(700, 700, Image.SCALE_SMOOTH));
        bgLabel[0].setIcon(bgIcon);
        bgLabel[0].addMouseListener(mouse);
        bgPanel[0].add(bgLabel[0]);
        bgPanel[0].setVisible(false);
    }

    public void attributeField() {
        textPanel = new JPanel();
        textPanel.setBounds(0, 700, 700, 100);
        textPanel.setBackground(Color.white);
        textPanel.setLayout(null);
        window.add(textPanel);

        messagText = new JTextArea();
        messagText.setBounds(10, 10, 680, 90);
        messagText.setBackground(null);
        messagText.setWrapStyleWord(true);
        messagText.setLineWrap(true);
        messagText.setEditable(false);
        messagText.setForeground(Color.black);
        messagText.setFont(new Font("ini belom tau", Font.PLAIN, 24));
        textPanel.add(messagText);

        attributePanel = new JPanel();
        attributePanel.setBounds(700, 0, 300, 800);
        attributePanel.setBackground(new Color(163, 115, 42, 255));
        attributePanel.setLayout(null);
        window.add(attributePanel);

        // add jam
        attributeItem(50, 30, "/Resource/jam.png");
        //jamText = new JLabel(mg.lock.getTime());
        jamText.setBounds(50 + 70, 15, 200, 50);
        jamText.setBackground(null);
        jamText.setForeground(Color.white);
        jamText.setFont(new Font("2", Font.PLAIN, 24));
        attributePanel.add(jamText);
        //hariText = new JLabel("Hari ke-" + mg.world.getHari());
        hariText.setBounds(50 + 70, 55, 200, 30);
        hariText.setBackground(null);
        hariText.setForeground(Color.white);
        hariText.setFont(new Font("B3", Font.PLAIN, 16));
        attributePanel.add(hariText);

        // add nama
        nameText = new JLabel();
        nameText.setBounds(50, 100, 270, 50);
        nameText.setBackground(null);
        nameText.setForeground(Color.white);
        nameText.setFont(new Font("3", Font.PLAIN, 24));
        attributePanel.add(nameText);

        // add pekerjaan
        attributeItem(50, 160, "upgrade.png");
        createObjek(attributePanel, 50, 160, 50, 50, "upgrade.png", new String[] { "Bekerja", "Ganti Pekerjaan" }, -1);
        pekerjaanText = new JLabel();
        pekerjaanText.setBounds(50 + 70, 160, 200, 50);
        pekerjaanText.setBackground(null);
        pekerjaanText.setForeground(Color.white);
        pekerjaanText.setFont(new Font("4", Font.PLAIN, 24));
        attributePanel.add(pekerjaanText);

        // add uang
        attributeItem(50, 220, "/Resource/cash.png");
        uangText = new JLabel("80");
        uangText.setBounds(50 + 70, 220, 200, 50);
        uangText.setBackground(null);
        uangText.setForeground(Color.white);
        uangText.setFont(new Font("5", Font.PLAIN, 24));
        attributePanel.add(uangText);

        // add mood
        attributeItem(50, 280, "/Resource/mood.png");
        moodText = new JLabel("80");
        moodText.setBounds(50 + 70, 280, 200, 50);
        moodText.setBackground(null);
        moodText.setForeground(Color.white);
        moodText.setFont(new Font("6", Font.PLAIN, 24));
        attributePanel.add(moodText);

        // add kesehatan
        attributeItem(50, 340, "/Resource/life.png");
        kesehatanText = new JLabel("80");
        kesehatanText.setBounds(50 + 70, 340, 200, 50);
        kesehatanText.setBackground(null);
        kesehatanText.setForeground(Color.white);
        kesehatanText.setFont(new Font("7", Font.PLAIN, 24));
        attributePanel.add(kesehatanText);

        // add kekenyangan
        attributeItem(50, 400, "/Resource/kekenyangan.png");
        kekenyanganText = new JLabel("80");
        kekenyanganText.setBounds(50 + 70, 400, 200, 50);
        kekenyanganText.setBackground(null);
        kekenyanganText.setForeground(Color.white);
        kekenyanganText.setFont(new Font("Book Antique", Font.PLAIN, 24));
        attributePanel.add(kekenyanganText);

        // add inventory
        createObjek(attributePanel, 85, 475, 50, 50, "/Resource/inventory.png", new String[] { "View Inventory" }, -1);

        // add shop
        createObjek(attributePanel, 160, 475, 50, 50, "/Resource/shop.png", new String[] { "Go to Store" }, -1);

        // Save
        customButton(attributePanel, 50, 560, 200, 40, "Save", 24, "save");
        customButton(attributePanel, 50, 610, 200, 40, "Main Menu", 24, "start");

        textPanel.setVisible(false);
        attributePanel.setVisible(false);
    }

    public void attributeItem(int x, int y, String path) {
        ImageIcon image = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("/Resource/background.png")).getImage()
                .getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        JLabel icon = new JLabel();
        icon.setBounds(x, y, 50, 50);
        icon.setIcon(image);
        icon.setBackground(null);
        attributePanel.add(icon);
    }

    public ImageIcon rotate(ImageIcon icon, double degrees) {
        // Get the buffered image from the ImageIcon
        BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        // Calculate the new size of the image based on the angle of rotation
        double radians = Math.toRadians(degrees);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int newWidth = (int) Math.round(icon.getIconWidth() * cos + icon.getIconHeight() * sin);
        int newHeight = (int) Math.round(icon.getIconWidth() * sin + icon.getIconHeight() * cos);

        // Create a new image
        BufferedImage rotatedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        g2d = rotatedImage.createGraphics();
        // Calculate the "anchor" point around which the image will be rotated
        int x = (newWidth - icon.getIconWidth()) / 2;
        int y = (newHeight - icon.getIconHeight()) / 2;
        // Transform the origin point around the anchor point
        AffineTransform at = new AffineTransform();
        at.setToRotation(radians, x + (icon.getIconWidth() / 2), y + (icon.getIconHeight() / 2));
        at.translate(x, y);
        g2d.setTransform(at);
        // Paint the original image
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // Create a new ImageIcon from the rotated image
        return new ImageIcon(rotatedImage);
    }

    public void createObjek(JPanel panel, int x, int y, int width, int height, String objPath, String[] actions,
            int index) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[actions.length];
        for (int i = 0; i < menuItem.length; i++) {
            if (actions[i].contains("View Home")) { // khusus rumah
                String[] command = actions[i].split("%");
                menuItem[i] = new JMenuItem("View " + command[1]);
                //menuItem[i].addActionListener(mg.actionHandler);
                menuItem[i].setActionCommand(index + "%" + command[0]);
            } else {
                menuItem[i] = new JMenuItem(actions[i]);
                //menuItem[i].addActionListener(mg.actionHandler);
                menuItem[i].setActionCommand(index + "%" + actions[i]);
            }
            popMenu.add(menuItem[i]);
        }

        JLabel obj = new JLabel();
        obj.setBounds(x, y, width, height);
        ImageIcon objImage = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("/Resource/inventory.png")).getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH));
        obj.setIcon(objImage);
        obj.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }
        });
        panel.add(obj, 0);
    }

    public void startButton(int bgNum, int x, int y, int width, int height, String text) {
        JButton btn = new JButton();

        btn.setBounds(x, y, width, height);
        btn.setText(text);
        btn.setBorder(null);
        btn.setBackground(null);
        btn.setForeground(Color.white);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        //btn.addActionListener(mg.actionHandler);
        btn.setActionCommand("start");
        btn.setFont(new Font("Helvetica", Font.BOLD, 30));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(true);
                btn.setBackground(new Color(0, 0, 0, 200));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setContentAreaFilled(false);
            }
        });

        bgPanel[bgNum].add(btn, 0);
    }

    public void customButton(JPanel panel, int x, int y, int width, int height, String text, int fontSize,
            String command) {
        JButton btn = new JButton();
        btn.setBounds(x, y, width, height);
        btn.setText(text);
        btn.setForeground(new Color(7, 168, 104));
        btn.setFocusPainted(false);
        //btn.addActionListener(mg.actionHandler);
        btn.setActionCommand(command);
        btn.setFont(new Font("Helvetica", Font.BOLD, fontSize));
        panel.add(btn, 0);
    }

    public void createRoom(int bgNum, int x, int y, Ruangan ruangan, int index) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[2];
        menuItem[1] = new JMenuItem("View Room");
        //menuItem[1].addActionListener(mg.actionHandler);
        menuItem[1].setActionCommand(index + "%" + "View Room");
        popMenu.add(menuItem[1]);

        JLabel obj = new JLabel();
        obj.setBounds(x, y, 80, 80);
        obj.setForeground(Color.white);
        obj.setBackground(new Color(13, 161, 136));
        obj.setOpaque(true);
        obj.setBorder(BorderFactory.createLineBorder(Color.black));
        obj.setText("<html><div style='text-align: center;'>Ruang " + ruangan.getNamaRuangan() + "</div></html>");
        obj.setFont(new Font("Helvetica", Font.BOLD, 16));
        obj.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        bgPanel[bgNum].add(obj, 0);
    }
    public static void main(String[] args) {
        MasterGame mg = new MasterGame();
        new GUI(mg);
    }
}

