import Edible;

package Masakan;
public class Masakan implements Edible {
    public String nama;
    public String[] daftarBahanMakanan;
    public int kekenyangan;

    public Masakan(String nama) {
        this.nama = nama;
        switch (nama) {
            case "Nasi Ayam":
                this.daftarBahanMakanan = new String[]{"Nasi", "Ayam"};
                this.kekenyangan = 16;
                break;
            case "Nasi Kari":
                this.daftarBahanMakanan = new String[]{"Nasi", "Kentang", "Wortel", "Sapi"};
                this.kekenyangan = 30;
                break;
            case "Susu Kacang":
                this.daftarBahanMakanan = new String[]{"Susu", "Kacang"};
                this.kekenyangan = 5;
                break;
            case "Tumis Sayur":
                this.daftarBahanMakanan = new String[]{"Wortel", "Bayam"};
                this.kekenyangan = 5;
                break;
            case "Bistik":
                this.daftarBahanMakanan = new String[]{"Kentang", "Sapi"};
                this.kekenyangan = 22;
                break;
            default:
                throw new IllegalArgumentException("Masakan tidak ditemukan.");
        }
    }

    @Override
    public String getName() {
        return this.nama;
    }

    @Override
    public int getKekenyangan() {
        return this.kekenyangan;
    }

    public String[] getDaftarBahanMakanan() {
        return this.daftarBahanMakanan;
    }

    public void addKekenyangan(int kekenyangan) {
        this.kekenyangan += kekenyangan;
    }
}