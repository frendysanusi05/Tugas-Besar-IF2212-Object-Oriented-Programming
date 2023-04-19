public class MasakanDriver {
    public static void main(String[] args) {
        try {
            Masakan nasiAyam = new Masakan("Nasi Ayam");
            System.out.println(nasiAyam.getName() + " memiliki kekenyangan " + nasiAyam.getKekenyangan());
            System.out.println("Daftar bahan makanan:");
            for (String bahan : nasiAyam.getDaftarBahanMakanan()) {
                System.out.println("- " + bahan);
            }
            System.out.println();

            Masakan nasiKari = new Masakan("Nasi Kari");
            System.out.println(nasiKari.getName() + " memiliki kekenyangan " + nasiKari.getKekenyangan());
            System.out.println("Daftar bahan makanan:");
            for (String bahan : nasiKari.getDaftarBahanMakanan()) {
                System.out.println("- " + bahan);
            }
            System.out.println();

            Masakan susuKacang = new Masakan("Susu Kacang");
            System.out.println(susuKacang.getName() + " memiliki kekenyangan " + susuKacang.getKekenyangan());
            System.out.println("Daftar bahan makanan:");
            for (String bahan : susuKacang.getDaftarBahanMakanan()) {
                System.out.println("- " + bahan);
            }
            System.out.println();

            Masakan tumisSayur = new Masakan("Tumis Sayur");
            System.out.println(tumisSayur.getName() + " memiliki kekenyangan " + tumisSayur.getKekenyangan());
            System.out.println("Daftar bahan makanan:");
            for (String bahan : tumisSayur.getDaftarBahanMakanan()) {
                System.out.println("- " + bahan);
            }
            System.out.println();

            Masakan bistik = new Masakan("Bistik");
            System.out.println(bistik.getName() + " memiliki kekenyangan " + bistik.getKekenyangan());
            System.out.println("Daftar bahan makanan:");
            for (String bahan : bistik.getDaftarBahanMakanan()) {
                System.out.println("- " + bahan);
            }
            System.out.println();

            Masakan tidakAda = new Masakan("Tidak Ada");
            System.out.println(tidakAda.getName() + " memiliki kekenyangan " + tidakAda.getKekenyangan());
            System.out.println("Daftar bahan makanan:");
            for (String bahan : tidakAda.getDaftarBahanMakanan()) {
                System.out.println("- " + bahan);
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}