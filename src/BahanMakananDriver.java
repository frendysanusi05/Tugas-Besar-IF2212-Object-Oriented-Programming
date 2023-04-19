public class BahanMakananDriver {
    public static void main(String[] args) {
        try {
            BahanMakanan nasi = new BahanMakanan("Nasi");
            System.out.println("Nama Bahan: " + nasi.getNama() + ", Harga: " + nasi.getHarga() + ", Kekenyangan: " + nasi.getKekenyangan());
            BahanMakanan kacang = new BahanMakanan("Kacang");
            System.out.println("Nama Bahan: " + kacang.getNama() + ", Harga: " + kacang.getHarga() + ", Kekenyangan: " + kacang.getKekenyangan());
            BahanMakanan telur = new BahanMakanan("Telur"); // akan melempar Exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}