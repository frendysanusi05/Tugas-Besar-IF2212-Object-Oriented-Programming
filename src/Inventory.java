import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> items = new HashMap<String, Integer>();

    public Inventory() {
    }

    public void addItem(String item) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + 1);
        } else {
            items.put(item, 1);
        }
    }

    public void decreaseItem(String item, int amount) {
        if (items.containsKey(item)) {
            if (items.get(item) > amount) {
                items.put(item, items.get(item) - amount);
            } else {
                items.remove(item);
            }
        } else {
            System.out.println("Item not found");
        }
    }

    public boolean containsItem(String item) {
        return items.containsKey(item);
    }

    public void printItem() {
        //print item in item
        System.out.println("Item di dalam inventory : ");
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println();
    }

    public void printSpecificItem (String typeName) {
        //print specific item in item
        System.out.printf("Item %s di dalam inventory:\n", typeName);
        String[] daftarFurniture = {"Kasur Single", "Kasur Quuen Size", "Kasung King Size", "Toilet", "Kompor Gas", "Kompor Listrik", "Meja dan Kursi", "Jam"};
        String[] daftarBahanMakanan = {"Nasi", "Kentang", "Ayam", "Sapi", "Wortel", "Bayam", "Kacang", "Susu"};
        String[] daftarMasakan = {"Nasi Ayam", "Nasi Kari", "Susu Kacang", "Tumis Sayur", "Bistik"};

        int i = 1;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (typeName == "Furniture") {
                for (String furniture : daftarFurniture) {
                    if (entry.getKey().equals(furniture)) {
                        System.out.println(i + ". " + entry.getKey() + ", jumlah: " + entry.getValue());
                        i++;
                    }
                }
            } else if (typeName == "Bahan Makanan") {
                for (String bahanMakanan : daftarBahanMakanan) {
                    if (entry.getKey().equals(bahanMakanan)) {
                        System.out.println(i + ". " + entry.getKey() + ", jumlah: " + entry.getValue());
                        i++;
                    }
                }
            } else if (typeName == "Masakan") {
                for (String masakan : daftarMasakan) {
                    if (entry.getKey().equals(masakan)) {
                        System.out.println(i + ". " + entry.getKey() + ", jumlah: " + entry.getValue());
                        i++;
                    }
                }
            }
        }
        System.out.println();
    } 
}
