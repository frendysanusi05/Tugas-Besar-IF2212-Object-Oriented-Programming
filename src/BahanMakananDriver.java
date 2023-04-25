public class BahanMakananDriver {
    public static void main(String[] args) {
        try {
            BahanMakanan nasi = new BahanMakanan("Nasi");
            System.out.println("Nama Bahan: " + nasi.getName() + ", Harga: " + nasi.getHarga() + ", Kekenyangan: " + nasi.getKekenyangan());
            BahanMakanan kacang = new BahanMakanan("Kacang");
            System.out.println("Nama Bahan: " + kacang.getName() + ", Harga: " + kacang.getHarga() + ", Kekenyangan: " + kacang.getKekenyangan());
            BahanMakanan telur = new BahanMakanan("Telur"); // akan melempar Exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}