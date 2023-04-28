public class InventoryDriver {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItem("Ayam");
        inventory.addItem("Bunga");
        inventory.addItem("Toilet");
        System.out.println();
        inventory.printItem();
        System.out.println();
        System.out.println("Furniture:");
        inventory.printSpecificItem("Furniture");
        System.out.println();
        System.out.println("Bahan Makanan:");
        inventory.printSpecificItem("Bahan Makanan");
        System.out.println();
        System.out.println("Masakan:");
        inventory.printSpecificItem("Masakan");
    }
}
