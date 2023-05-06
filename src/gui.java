import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import world.World;

public class gui implements ActionListener {
    
    public static void main(String[] args) {
        System.out.println("\033[1;32m");
        System.out.println("     :::       ::: :::::::::: :::        ::::::::   ::::::::    :::   :::   ::::::::::      ::::::::::: ::::::::"); 
        System.out.println("    :+:       :+: :+:        :+:       :+:    :+: :+:    :+:  :+:+: :+:+:  :+:                 :+:    :+:    :+::"); 
        System.out.println("   +:+       +:+ +:+        +:+       +:+        +:+    +:+ +:+ +:+:+ +:+ +:+                 +:+    +:+    +:+");  
        System.out.println("  +#+  +:+  +#+ +#++:++#   +#+       +#+        +#+    +:+ +#+  +:+  +#+ +#++:++#            +#+    +#+    +:+");   
        System.out.println(" +#+ +#+#+ +#+ +#+        +#+       +#+        +#+    +#+ +#+       +#+ +#+                 +#+    +#+    +#+");    
        System.out.println("#+#+# #+#+#  #+#        #+#       #+#    #+# #+#    #+# #+#       #+# #+#                 #+#    #+#    #+#");     
        System.out.println("###   ###   ########## ########## ########   ########  ###       ### ##########          ###     ########\n");
        System.out.println("\033[1;36m");
        System.out.println(" ________  ___  _____ ______                  ________  ___       ___  ________  ___  _________    ___    ___ ___       ");
        System.out.println("|\\   ____\\|\\  \\|\\   _ \\  _   \\               |\\   __  \\|\\  \\     |\\  \\|\\   ____\\|\\  \\|\\___   ___\\ |\\  \\  /  /|\\  \\      ");
        System.out.println("\\ \\  \\___|\\ \\  \\ \\  \\\\\\__\\ \\  \\  ____________\\ \\  \\|\\  \\ \\  \\    \\ \\  \\ \\  \\___|\\ \\  \\|___ \\  \\_| \\ \\  \\/  / | \\  \\     ");
        System.out.println(" \\ \\_____  \\ \\  \\ \\  \\\\|__| \\  \\|\\____________\\ \\   ____\\ \\  \\    \\ \\  \\ \\  \\    \\ \\  \\   \\ \\  \\   \\ \\    / / \\ \\  \\    ");
        System.out.println("  \\|____|\\  \\ \\  \\ \\  \\    \\ \\  \\|____________|\\ \\  \\___|\\ \\  \\____\\ \\  \\ \\  \\____\\ \\  \\   \\ \\  \\   \\/  /  /   \\ \\_\\   ");
        System.out.println("    ____\\_\\  \\ \\__\\\\__\\    \\ \\__\\              \\ \\__\\    \\ \\_______\\ \\__\\ \\_______\\ \\__\\   \\ \\__\\__/  / /      \\|__|   ");
        System.out.println("   |\\_________\\|__|\\|__|     \\|__|               \\|__|     \\|_______|\\|__|\\|_______|\\|__|    \\|__|\\___/ /           ___ ");
        System.out.println("   \\|_________|                                                                                  \\|___|/           |\\__\\");
        System.out.println("                                                                                                                   \\|__|\n");
        System.out.println("\033[0m");
        System.out.println("Silahkan pilih menu dibawah ini:");                                                                                                                                        
        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.println("3. Help");
        System.out.println("4. Exit");

        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // tambahkan background
        ImageIcon imageIcon = new ImageIcon("src/res/menu_background.png"); // ganti dengan path file gambar yang diinginkan
        JLabel background = new JLabel(imageIcon);
        background.setBounds(0, 0, 800, 600); // ganti dengan ukuran gambar yang digunakan

        JLabel label = new JLabel();
        label.setIcon(imageIcon);
        label.setLayout(new GridBagLayout());


        // tambahkan button
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton helpButton = new JButton("Help");
        JButton exitButton = new JButton("Exit");
        
        // atur jarak antara button
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 10, 0, 10);
        c.anchor = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 5;
        label.add(newGameButton, c);

        c.gridx = 0;
        c.gridy = 6;
        label.add(loadGameButton, c);

        c.gridx = 0;
        c.gridy = 7;
        label.add(helpButton, c);

        c.gridx = 0;
        c.gridy = 8;
        label.add(exitButton, c);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        panel.add(label, c);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // aksi yang akan dilakukan ketika tombol New Game ditekan
                try {
                    frame.setVisible(false);
                    Main.newGameSim();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                
            }
        });

        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // aksi yang dijalankan ketika tombol Load Game ditekan
                try {
                    frame.setVisible(false);
                    World world = Main.load();
                    Main.playSim(world);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // aksi yang dijalankan ketika tombol Help ditekan
                try {
                    System.out.println("Help");
                    Main.help();                    
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // aksi yang dijalankan ketika tombol Exit ditekan
                System.exit(0); // keluar dari program
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
