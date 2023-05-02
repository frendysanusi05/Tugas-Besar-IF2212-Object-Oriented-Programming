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

//import package

public class GUI {
    //background
    public MasterGame mg;
    private JFrame window;
    private JPanel bgPanel[] = new JPanel[10];
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
        mg.load();
        createMainField();
        generateScreen();
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

    public void createMainBackground(){
        bgPanel[0] = new JPanel();
        bgPanel[0].setBounds(0, 0, 1000, 800);
        bgPanel[0].setBackground(Color.black);
        bgPanel[0].setLayout(null);
        window.add(bgPanel[0]);

        bgLabel[0] = new JLabel();
        bgLabel[0].setBounds(0, 0, 1000, 800);

        ImageIcon bgIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("src\\img\\background\\main.png")).getImage()
                .getScaledInstance(1000, 800, Image.SCALE_SMOOTH));
        bgLabel[0].setIcon(bgIcon);
        bgPanel[0].add(bgLabel[0]);
        bgPanel[0].setVisible(false);
    }

    public void createBackground(int bgNum, String bgPath, MouseListener mouse) {
        bgPanel[0] = new JPanel();
        bgPanel[0].setBounds(0, 0, 700, 700);
        bgPanel[0].setBackground(null);
        bgPanel[0].setLayout(null);
        window.add(bgPanel[0]);

        bgLabel[0] = new JLabel();
        bgLabel[0].setBounds(0, 0, 700, 700);
        ImageIcon bgIcon = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource("src\\img\\background\\main.png")).getImage()
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
        attributeItem(50, 30, "jam.png");
        jamText = new JLabel(mg.world.getTime());
        jamText.setBounds(50 + 70, 15, 200, 50);
        jamText.setBackground(null);
        jamText.setForeground(Color.white);
        jamText.setFont(new Font("2", Font.PLAIN, 24));
        attributePanel.add(jamText);
        hariText = new JLabel("Hari ke-" + mg.world.getHari());
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
        attributeItem(50, 160, "kerja.png");
        createObjek(attributePanel, 50, 160, 50, 50, "kerja.png", new String[] { "Bekerja", "Ganti Pekerjaan" }, -1);
        pekerjaanText = new JLabel();
        pekerjaanText.setBounds(50 + 70, 160, 200, 50);
        pekerjaanText.setBackground(null);
        pekerjaanText.setForeground(Color.white);
        pekerjaanText.setFont(new Font("4", Font.PLAIN, 24));
        attributePanel.add(pekerjaanText);

        // add uang
        attributeItem(50, 220, "cash.png");
        uangText = new JLabel("80");
        uangText.setBounds(50 + 70, 220, 200, 50);
        uangText.setBackground(null);
        uangText.setForeground(Color.white);
        uangText.setFont(new Font("5", Font.PLAIN, 24));
        attributePanel.add(uangText);

        // add mood
        attributeItem(50, 280, "mood.png");
        moodText = new JLabel("80");
        moodText.setBounds(50 + 70, 280, 200, 50);
        moodText.setBackground(null);
        moodText.setForeground(Color.white);
        moodText.setFont(new Font("6", Font.PLAIN, 24));
        attributePanel.add(moodText);

        // add kesehatan
        attributeItem(50, 340, "life.png");
        kesehatanText = new JLabel("80");
        kesehatanText.setBounds(50 + 70, 340, 200, 50);
        kesehatanText.setBackground(null);
        kesehatanText.setForeground(Color.white);
        kesehatanText.setFont(new Font("7", Font.PLAIN, 24));
        attributePanel.add(kesehatanText);

        // add kekenyangan
        attributeItem(50, 400, "kekenyangan.png");
        kekenyanganText = new JLabel("80");
        kekenyanganText.setBounds(50 + 70, 400, 200, 50);
        kekenyanganText.setBackground(null);
        kekenyanganText.setForeground(Color.white);
        kekenyanganText.setFont(new Font("Book Antique", Font.PLAIN, 24));
        attributePanel.add(kekenyanganText);

        // add inventory
        createObjek(attributePanel, 85, 475, 50, 50, "inventory.png", new String[] { "View Inventory" }, -1);

        // add shop
        createObjek(attributePanel, 160, 475, 50, 50, "shop.png", new String[] { "Go to Store" }, -1);

        // Save
        customButton(attributePanel, 50, 560, 200, 40, "Save", 24, "save");
        customButton(attributePanel, 50, 610, 200, 40, "Main Menu", 24, "start");

        textPanel.setVisible(false);
        attributePanel.setVisible(false);
    }

    public void attributeItem(int x, int y, String path) {
        ImageIcon image = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource(path)).getImage()
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

    public void createObjek(int bgNum, int x, int y, int width, int height, String objPath, String[] actions,
            String posisi) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[actions.length];
        for (int i = 0; i < menuItem.length; i++) {
            menuItem[i] = new JMenuItem(actions[i]);
            menuItem[i].addActionListener(mg.actionHandler);
            menuItem[i].setActionCommand(actions[i]);
            popMenu.add(menuItem[i]);
        }

        JLabel obj = new JLabel();
        obj.setBounds(x, y, width, height);

        ImageIcon objImage = new ImageIcon(getClass().getClassLoader().getResource(objPath));
        if (posisi.equals("h")) {
            objImage = rotate(objImage, 90);
        }
        obj.setIcon(objImage);

        obj.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    popMenu.show(obj, e.getX(), e.getY());
                }
            }
        });

        bgPanel[bgNum].add(obj);
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
                menuItem[i].addActionListener(mg.actionHandler);
                menuItem[i].setActionCommand(index + "%" + command[0]);
            } else {
                menuItem[i] = new JMenuItem(actions[i]);
                menuItem[i].addActionListener(mg.actionHandler);
                menuItem[i].setActionCommand(index + "%" + actions[i]);
            }
            popMenu.add(menuItem[i]);
        }

        JLabel obj = new JLabel();
        obj.setBounds(x, y, width, height);
        ImageIcon objImage = new ImageIcon(new ImageIcon(getClass().getClassLoader().getResource(objPath)).getImage()
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
        btn.addActionListener(mg.actionHandler);
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
        btn.addActionListener(mg.actionHandler);
        btn.setActionCommand(command);
        btn.setFont(new Font("Helvetica", Font.BOLD, fontSize));
        panel.add(btn, 0);
    }

    public void generateRoom(Ruangan r, int bgNum) {
        for (int i = 1; i <= r.getDaftarObjek().size(); i++) {
            ObjekNonMakanan o = r.getObjek(i - 1);
            int x = 50 + (int) Math.round(o.getTitik().getX() * 100);
            int y = 50 + (int) Math.round(o.getTitik().getY() * 100);
            int width, height;
            if ("v".equals(o.getPosisi())) {
                height = (int) Math.round(o.getPanjang() * 100);
                width = (int) Math.round(o.getLebar() * 100);
            } else {
                width = (int) Math.round(o.getPanjang() * 100);
                height = (int) Math.round(o.getLebar() * 100);
            }
            String filename = o.getNamaObjek() + ".png";
            String[] action = new String[1];
            action = o.getAksi();

            createObjek(bgNum, x, y, width, height, filename, action, o.getPosisi());
        }
    }

    public void createRoom(int bgNum, int x, int y, Ruangan ruangan, int index) {
        // POP MENU
        JPopupMenu popMenu = new JPopupMenu();
        JMenuItem menuItem[] = new JMenuItem[2];
        menuItem[1] = new JMenuItem("View Room");
        menuItem[1].addActionListener(mg.actionHandler);
        menuItem[1].setActionCommand(index + "%" + "View Room");
        popMenu.add(menuItem[1]);

        JLabel obj = new JLabel();
        obj.setBounds(x, y, 80, 80);
        obj.setForeground(Color.white);
        obj.setBackground(new Color(13, 161, 136));
        obj.setOpaque(true);
        obj.setBorder(BorderFactory.createLineBorder(Color.black));
        obj.setText("<html><div style='text-align: center;'>Ruang " + ruangan.getNama() + "</div></html>");
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

    public void generateHome(Rumah rumah, int bgNum) {
        List<Point> listPoint = new ArrayList<Point>();
        for (int i = 0; i < rumah.getDaftarRuangan().size(); i++) {
            Point point = new Point(310, 310);
            Ruangan currRuangan = rumah.getDaftarRuangan().get(i);
            if (i != 0) {
                if (currRuangan.getAtas() != null) {
                    int index = rumah.getDaftarRuangan().indexOf(currRuangan.getAtas());
                    point.setX(listPoint.get(index).getX());
                    point.setY(listPoint.get(index).getY() + 90);
                } else if (currRuangan.getBawah() != null) {
                    int index = rumah.getDaftarRuangan().indexOf(currRuangan.getBawah());
                    point.setX(listPoint.get(index).getX());
                    point.setY(listPoint.get(index).getY() - 90);
                } else if (currRuangan.getKanan() != null) {
                    int index = rumah.getDaftarRuangan().indexOf(currRuangan.getKanan());
                    point.setX(listPoint.get(index).getX() - 90);
                    point.setY(listPoint.get(index).getY());
                } else if (currRuangan.getKiri() != null) {
                    int index = rumah.getDaftarRuangan().indexOf(currRuangan.getKiri());
                    point.setX(listPoint.get(index).getX() + 90);
                    point.setY(listPoint.get(index).getY());
                }
            }
            createRoom(bgNum, point.getX(), point.getY(), currRuangan, i);
            listPoint.add(i, point);
        }
    }

    public void refreshRoom(Ruangan currRuangan) {
        mg.gui.bgPanel[3].removeAll();
        mg.routing.showScreen(3);
        if (mg.getCurrentSim().getRumah() != null && mg.getCurrentSim().getRumah().getDaftarRuangan().contains(currRuangan) ) {
            mg.gui.createObjek(bgPanel[3], 650, 600, 40, 40, "edit.png", new String[] { "Edit Room" }, -1);
        }
        mg.gui.createObjek(bgPanel[3], 650, 650, 40, 40, "back.png", new String[] { "Back to Home", "Back to World" },
                -1);
        mg.gui.generateRoom(currRuangan, 3);
        mg.gui.bgPanel[3].add(mg.gui.bgLabel[3]);
        mg.gui.bgPanel[3].revalidate();
        mg.gui.bgPanel[3].repaint();
    }

    public void refreshHome(Rumah currRumah) {
        mg.gui.bgPanel[4].removeAll();
        mg.routing.showScreen(4);
        if (mg.getCurrentSim().getRumah() != null && currRumah.getNama().equals(mg.getCurrentSim().getRumah().getNama())) {
            mg.gui.createObjek(bgPanel[4], 650, 600, 40, 40, "upgrade.png", new String[] { "Upgrade House" }, -1);
        }
        mg.gui.createObjek(bgPanel[4], 650, 650, 40, 40, "back.png", new String[] { "Back to World" }, -1);
        mg.gui.generateHome(currRumah, 4);
        mg.gui.bgPanel[4].add(mg.gui.bgLabel[4]);
        mg.gui.bgPanel[4].revalidate();
        mg.gui.bgPanel[4].repaint();
    }

    public void inventoryPopUp() {
        popInventory = new JFrame("Inventory");
        popInventory.setSize(400, 250);
        popInventory.getContentPane().setBackground(Color.white);
        popInventory.setResizable(false);
        popInventory.setVisible(false);

        Object[][] data = new Object[mg.getCurrentSim().getInventory().getData().size()][3];
        int i = 0;
        for (InventoryItem item : mg.getCurrentSim().getInventory().getData()) {
            data[i] = new Object[] { item.getNamaBarang(), item.getKategori(), item.getJumlah() };
            i++;
        }

        JTable jt = new JTable(data, new String[] { "Nama Barang", "Kategori", "Jumlah" });
        jt.setEnabled(false);
        JScrollPane js = new JScrollPane(jt);
        popInventory.add(js);
    }

    public void generateScreen() {
        // Start
        createBackground(0, "start.png");
        startButton(0, 150, 500, 700, 40, "Click Here to Start");

        // Main Menu
        createBackground(1, "main_menu.png");
        if (mg.world.getDaftarSim().isEmpty()) {
            customButton(bgPanel[1], 300, 300, 400, 50, "Create New Sim", 32, "new-sim");
            customButton(bgPanel[1], 300, 400, 400, 50, "Exit Game", 32, "exit");
        } else {
            customButton(bgPanel[1], 300, 250, 400, 50, "Choose Sim", 32, "choose-sim");
            customButton(bgPanel[1], 300, 350, 400, 50, "Create New Sim", 32, "new-sim");
            customButton(bgPanel[1], 300, 450, 400, 50, "Exit Game", 32, "exit");
        }

        // World
        createBackground(2, "world.png", new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (mg.getCurrentSim().getRumah() == null) {
                    if (e.getX() > 30 && e.getX() < 670 && e.getY() > 30 && e.getY() < 670) {
                        createObjek(bgPanel[2], e.getX(), e.getY(), 20, 20, "rumahku.png",
                                new String[] { "" }, -1);
                        bgPanel[2].repaint();
                        String homeName = JOptionPane.showInputDialog(mg.gui.bgPanel[1], "What is your home name?",
                                "Input Home homeName", JOptionPane.PLAIN_MESSAGE);
                        if (homeName != null && homeName.replaceAll(" ", "").length() > 0) {
                            if (mg.world.addRumah(new Point((e.getX() - 30) / 10, (e.getY() - 30) / 10), homeName)) {
                                mg.getCurrentSim()
                                        .setRumah(mg.world.getDaftarRumah().get(mg.world.getDaftarRumah().size() - 1));
                                bgPanel[2].remove(0);
                                createObjek(bgPanel[2], e.getX(), e.getY(), 20, 20, "rumahku.png",
                                        new String[] { "View Home%" + homeName }, mg.world.getDaftarRumah().size() - 1);
                                mg.gui.messagText.setText("Rumah berhasil di bangun, Selamat bermain");
                            } else {
                                JOptionPane.showMessageDialog(null, "Nama sudah digunakan!");
                                bgPanel[2].remove(0);
                            }
                        } else {
                            bgPanel[2].remove(0);
                        }
                        bgPanel[2].repaint();
                    }
                }
            }
        });

        // Rumah
        createBackground(4, "home.jpg", null);

        // Ruangan
        createBackground(3, "ruangan.png", null);
    }
    //testing background public static void main string args
   
    

    
}
