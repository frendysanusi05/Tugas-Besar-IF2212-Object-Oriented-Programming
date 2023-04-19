import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class BahanMakananGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton beliButton;
    private JLabel totalLabel;
    private int total = 0;

    public BahanMakananGUI() {
        // Set up the frame
        setTitle("Daftar Bahan Makanan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Create the table model and add some columns
        model = new DefaultTableModel();
        model.addColumn("Nama");
        model.addColumn("Harga");
        model.addColumn("Kekenyangan");

        // Add the rows to the table model
        model.addRow(new Object[]{"Nasi", 5, 5});
        model.addRow(new Object[]{"Kentang", 3, 4});
        model.addRow(new Object[]{"Ayam", 10, 8});
        model.addRow(new Object[]{"Sapi", 12, 15});
        model.addRow(new Object[]{"Wortel", 3, 2});
        model.addRow(new Object[]{"Bayam", 3, 2});
        model.addRow(new Object[]{"Kacang", 2, 2});
        model.addRow(new Object[]{"Susu", 2, 1});

        // Create the table and add it to a scroll pane
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Create the buy button and add an action listener
        beliButton = new JButton("Beli");
        beliButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                for (int i = 0; i < selectedRows.length; i++) {
                    int harga = (int) model.getValueAt(selectedRows[i], 1);
                    total += harga;
                }
                totalLabel.setText("Total: Rp " + total);
                JOptionPane.showMessageDialog(null, "Terima kasih telah membeli!");
            }
        });

        // Create the total label
        totalLabel = new JLabel("Total: Rp " + total);

        // Add the components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(beliButton, BorderLayout.SOUTH);
        add(totalLabel, BorderLayout.NORTH);

        // Display the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new BahanMakananGUI();
    }
}