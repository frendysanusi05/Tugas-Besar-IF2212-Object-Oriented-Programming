
public class BahanMakanan {
    public String nama;
    public int harga;
    public int kekenyangan;

    public BahanMakanan(String nama) throws Exception{
        switch (nama) {
            case "Nasi":
                this.nama = "Nasi";
                this.harga = 5;
                this.kekenyangan = 5;
                break;
            case "Kentang":
                this.nama = "Kentang";
                this.harga = 3;
                this.kekenyangan = 4;
                break;
            case "Ayam":
                this.nama = "Ayam";
                this.harga = 10;
                this.kekenyangan = 8;
                break;
            case "Sapi":
                this.nama = "Sapi";
                this.harga = 12;
                this.kekenyangan = 15;
                break;
            case "Wortel":
                this.nama = "Wortel";
                this.harga = 3;
                this.kekenyangan = 2;
                break;
            case "Bayam":
                this.nama = "Bayam";
                this.harga = 3;
                this.kekenyangan = 2;
                break;
            case "Kacang":
                this.nama = "Kacang";
                this.harga = 2;
                this.kekenyangan = 2;
                break;
            case "Susu":
                this.nama = "Susu";
                this.harga = 2;
                this.kekenyangan = 1;
                break;
            default:
                throw new Exception("Bahan Makanan tidak ditemukan!");
        }
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public void addKekenyangan(int kekenyangan) {
        this.kekenyangan += kekenyangan;
    }
}