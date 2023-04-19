public class FurnitureDriver {
    public static void main(String[] args) {
        try {
            Furniture kasurSingle = new Furniture("Kasur Single");
            System.out.println("Nama: " + kasurSingle.nama);
            System.out.println("Panjang: " + kasurSingle.panjang);
            System.out.println("Lebar: " + kasurSingle.lebar);
            System.out.println("Harga: " + kasurSingle.harga);
            System.out.println("Aksi: " + kasurSingle.aksi);
            Furniture obeng = new Furniture("Obeng");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}