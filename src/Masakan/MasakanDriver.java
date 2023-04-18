package Masakan;
import java.util.Arrays;
public class MasakanDriver {
    public static void main(String[] args) {
        Masakan masakan1 = new Masakan("Nasi Ayam");
        System.out.println("Nama Masakan: " + masakan1.getName());
        System.out.println("Daftar Bahan Makanan: " + Arrays.toString(masakan1.getDaftarBahanMakanan()));
        System.out.println("Kekenyangan: " + masakan1.getKekenyangan());
        masakan1.addKekenyangan(5);
        System.out.println("Kekenyangan setelah dimakan: " + masakan1.getKekenyangan());

        Masakan masakan2 = new Masakan("Bistik");
        System.out.println("Nama Masakan: " + masakan2.getName());
        System.out.println("Daftar Bahan Makanan: " + Arrays.toString(masakan2.getDaftarBahanMakanan()));
        System.out.println("Kekenyangan: " + masakan2.getKekenyangan());
        masakan2.addKekenyangan(10);
        System.out.println("Kekenyangan setelah dimakan: " + masakan2.getKekenyangan());

        Masakan masakan3 = new Masakan("Sop Buntut");
        System.out.println("Nama Masakan: " + masakan3.getName());
        System.out.println("Daftar Bahan Makanan: " + Arrays.toString(masakan3.getDaftarBahanMakanan()));
        System.out.println("Kekenyangan: " + masakan3.getKekenyangan());
    }
}