import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FurnitureGUI extends JFrame {
    private ArrayList<Furniture> furnitureList = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    private JButton buyButton;
    private JTextField quantityField;
    private int selectedRow = -1;
    private int totalHarga = 0;

    public FurnitureGUI() {
        // initialize furniture list
        try {
            furnitureList.add(new Furniture("Kasur Single"));
            furnitureList.add(new Furniture("Kasur Queen Size"));
            furnitureList.add(new Furniture("Kasur King Size"));
            furnitureList.add(new Furniture("Toilet"));
            furnitureList.add(new Furniture("Kompor Gas"));
            furnitureList.add(new Furniture("Kompor Listrik"));
            furnitureList.add(new Furniture("Meja dan Kursi"));
            furnitureList.add(new Furniture("Jam"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set up table model and table
        String[] columnNames = {"Nama", "Panjang", "Lebar", "Harga", "Aksi"};
        tableModel = new DefaultTableModel(columnNames, 0);
        for (Furniture furniture : furnitureList) {
            Object[] rowData = {furniture.getNama(), furniture.getPanjang(), furniture.getLebar(), furniture.getHarga(), furniture.getAksi()};
            tableModel.addRow(rowData);
        }
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = table.getSelectedRow();
        });

        // set up buy button and quantity field
        buyButton = new JButton("Beli");
        quantityField = new JTextField(5);

        // add action listener to buy button
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedRow != -1) {
                    int quantity = Integer.parseInt(quantityField.getText());
                    int harga = (int) tableModel.getValueAt(selectedRow, 3);
                    totalHarga += harga * quantity;
                    System.out.println("Beli " + quantity + " " + (String) tableModel.getValueAt(selectedRow, 0) + " berhasil ditambahkan ke keranjang!");
                    System.out.println("Total harga: " + totalHarga);
                } else {
                    System.out.println("Pilih furniture yang ingin dibeli!");
                }
            }
        });

        // set up panels and add components
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setPreferredSize(new Dimension(400, 300));
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buyPanel = new JPanel();
        buyPanel.add(new JLabel("Jumlah: "));
        buyPanel.add(quantityField);
        buyPanel.add(buyButton);

        getContentPane().add(tablePanel, BorderLayout.NORTH);
        getContentPane().add(buyPanel, BorderLayout.CENTER);

        // set up frame
        setTitle("Daftar Furniture");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FurnitureGUI();
    }
}