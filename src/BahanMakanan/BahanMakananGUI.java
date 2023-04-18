package BahanMakanan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BahanMakananGUI extends JFrame {
    private JLabel label;
    private JPanel panel;
    private JButton beliButton;
    private JComboBox<String> bahanComboBox;
    private JTextArea detailTextArea;

    private BahanMakanan bahanMakanan;

    public BahanMakananGUI() {
        super("Bahan Makanan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        label = new JLabel("Pilih bahan makanan:");
        bahanComboBox = new JComboBox<>(new String[] {"Nasi", "Kentang", "Ayam", "Sapi", "Wortel", "Bayam", "Kacang", "Susu"});
        detailTextArea = new JTextArea();
        beliButton = new JButton("Beli");
        beliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                beliBahanMakanan();
            }
        });

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(bahanComboBox, BorderLayout.CENTER);
        panel.add(beliButton, BorderLayout.SOUTH);

        add(panel, BorderLayout.NORTH);
        add(detailTextArea, BorderLayout.CENTER);

        setVisible(true);
    }

    private void beliBahanMakanan() {
        String bahanMakananStr = (String) bahanComboBox.getSelectedItem();
        bahanMakanan = new BahanMakanan(bahanMakananStr);

        String detail = "Nama: " + bahanMakanan.getNama() + "\n"
                + "Harga: " + bahanMakanan.getHarga() + "\n"
                + "Kekenyangan: " + bahanMakanan.getKekenyangan() + "\n";
        detailTextArea.setText(detail);

        JOptionPane.showMessageDialog(null, "Terima kasih telah membeli " + bahanMakanan.getNama() + " seharga " + bahanMakanan.getHarga() + "!");
    }

    public static void main(String[] args) {
        BahanMakananGUI gui = new BahanMakananGUI();
    }
}