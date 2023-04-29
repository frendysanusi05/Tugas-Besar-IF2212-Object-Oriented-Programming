public class FurnitureDriver {
    public static void main(String[] args) {
        try {
            Furniture kasurSingle = new Furniture("Kasur Single");
            System.out.println("Nama: " + kasurSingle.getNama());
            System.out.println("Panjang: " + kasurSingle.getPanjang());
            System.out.println("Lebar: " + kasurSingle.getLebar());
            System.out.println("Harga: " + kasurSingle.getHarga());
            System.out.println("Aksi: " + kasurSingle.getAksi());
            Furniture obeng = new Furniture("Obeng");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
