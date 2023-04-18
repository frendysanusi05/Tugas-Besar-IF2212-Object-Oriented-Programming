package BahanMakanan;
public class BahanMakananDriver {
    public static void main(String[] args) {
        BahanMakanan nasi = new BahanMakanan("Nasi");
        System.out.println("Bahan makanan: " + nasi.getNama());
        System.out.println("Harga: " + nasi.getHarga() + " ribu rupiah");
        System.out.println("Kekenyangan: " + nasi.getKekenyangan());

        BahanMakanan ayam = new BahanMakanan("Ayam");
        System.out.println("Bahan makanan: " + ayam.getNama());
        System.out.println("Harga: " + ayam.getHarga() + " ribu rupiah");
        System.out.println("Kekenyangan: " + ayam.getKekenyangan());

        BahanMakanan sayur = new BahanMakanan("Bayam");
        System.out.println("Bahan makanan: " + sayur.getNama());
        System.out.println("Harga: " + sayur.getHarga() + " ribu rupiah");
        System.out.println("Kekenyangan: " + sayur.getKekenyangan());

        BahanMakanan susu = new BahanMakanan("Susu");
        System.out.println("Bahan makanan: " + susu.getNama());
        System.out.println("Harga: " + susu.getHarga() + " ribu rupiah");
        System.out.println("Kekenyangan: " + susu.getKekenyangan());

        BahanMakanan mie = new BahanMakanan("Mie");
        System.out.println("Bahan makanan: " + mie.getNama());
    }
}