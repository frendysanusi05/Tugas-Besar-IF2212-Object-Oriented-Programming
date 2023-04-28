import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MasakanGUI extends JFrame {

    private JTable masakanTable;

    public MasakanGUI() {
        // Set judul frame
        setTitle("Daftar Masakan");

        // Set ukuran frame
        setSize(500, 300);

        // Tabel daftar masakan
        masakanTable = new JTable();

        // Model tabel daftar masakan
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nama Masakan");
        model.addColumn("Daftar Bahan Makanan");
        model.addColumn("Kekenyangan");

        // Tambahkan masakan ke dalam tabel
        try {
            Masakan nasiAyam = new Masakan("Nasi Ayam");
            Object[] nasiAyamRow = {nasiAyam.getName(), String.join(", ", nasiAyam.getDaftarBahanMakanan()), nasiAyam.getKekenyangan()};
            model.addRow(nasiAyamRow);

            Masakan nasiKari = new Masakan("Nasi Kari");
            Object[] nasiKariRow = {nasiKari.getName(), String.join(", ", nasiKari.getDaftarBahanMakanan()), nasiKari.getKekenyangan()};
            model.addRow(nasiKariRow);

            Masakan susuKacang = new Masakan("Susu Kacang");
            Object[] susuKacangRow = {susuKacang.getName(), String.join(", ", susuKacang.getDaftarBahanMakanan()), susuKacang.getKekenyangan()};
            model.addRow(susuKacangRow);

            Masakan tumisSayur = new Masakan("Tumis Sayur");
            Object[] tumisSayurRow = {tumisSayur.getName(), String.join(", ", tumisSayur.getDaftarBahanMakanan()), tumisSayur.getKekenyangan()};
            model.addRow(tumisSayurRow);

            Masakan bistik = new Masakan("Bistik");
            Object[] bistikRow = {bistik.getName(), String.join(", ", bistik.getDaftarBahanMakanan()), bistik.getKekenyangan()};
            model.addRow(bistikRow);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        masakanTable.setModel(model);

        // Tambahkan tabel ke dalam JScrollPane
        JScrollPane scrollPane = new JScrollPane(masakanTable);

        // Tambahkan JScrollPane ke dalam frame
        add(scrollPane);
    }

    public static void main(String[] args) {
        // Buat objek MasakanGUI
        MasakanGUI gui = new MasakanGUI();

        // Tampilkan frame
        gui.setVisible(true);
    }
}