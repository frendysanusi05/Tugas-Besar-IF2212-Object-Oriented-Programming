public class FurnitureDriver {
    public static void main(String[] args) {
        Furniture kasurSingle = new Furniture("Kasur Single");
        System.out.println("Nama furniture: " + kasurSingle.nama);
        System.out.println("Panjang: " + kasurSingle.getPanjang());
        System.out.println("Lebar: " + kasurSingle.getLebar());
        System.out.println("Harga: " + kasurSingle.getHarga());
        System.out.println("Aksi: " + kasurSingle.getAksi());
        
        Furniture mejaKursi = new Furniture("Meja dan Kursi");
        System.out.println("Nama furniture: " + mejaKursi.nama);
        System.out.println("Panjang: " + mejaKursi.getPanjang());
        System.out.println("Lebar: " + mejaKursi.getLebar());
        System.out.println("Harga: " + mejaKursi.getHarga());
        System.out.println("Aksi: " + mejaKursi.getAksi());
        
        Furniture objekTidakTersedia = new Furniture("Lemari");
    }
}