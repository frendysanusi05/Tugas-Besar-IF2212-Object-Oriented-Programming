import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> items = new HashMap<String, Integer>();

    public Inventory() {
    }

    public boolean isEmpty() {
        return items.isEmpty();
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
        if (isItemTypeInInventory("Furniture")) {
            printSpecificItem("Furniture");
        } 

        if (isItemTypeInInventory("Bahan Makanan")) {
            printSpecificItem("Bahan Makanan");
        }

        if (isItemTypeInInventory("Masakan")) {
            printSpecificItem("Masakan");
        }
    }

    public boolean isItemTypeInInventory(String typeName) {
        String[] daftarFurniture = {"Kasur Single", "Kasur Queen Size", "Kasung King Size", "Toilet", "Kompor Gas", "Kompor Listrik", "Meja dan Kursi", "Jam", "Komputer", "TV", "Shower"};
        String[] daftarBahanMakanan = {"Nasi", "Kentang", "Ayam", "Sapi", "Wortel", "Bayam", "Kacang", "Susu"};
        String[] daftarMasakan = {"Nasi Ayam", "Nasi Kari", "Susu Kacang", "Tumis Sayur", "Bistik"};

        if (typeName == "Furniture") {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                for (String furniture : daftarFurniture) {
                    if (entry.getKey().equals(furniture)) {
                        return true;
                    }
                }
            }
        } else if (typeName == "Bahan Makanan") {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                for (String bahanMakanan : daftarBahanMakanan) {
                    if (entry.getKey().equals(bahanMakanan)) {
                        return true;
                    }
                }
            }
        } else if (typeName == "Masakan") {
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                for (String masakan : daftarMasakan) {
                    if (entry.getKey().equals(masakan)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void printSpecificItem (String typeName) {
        //print specific item in item
        System.out.printf("Item %s di dalam inventory:\n", typeName);
        String[] daftarFurniture = {"Kasur Single", "Kasur Queen Size", "Kasung King Size", "Toilet", "Kompor Gas", "Kompor Listrik", "Meja dan Kursi", "Jam", "Komputer", "TV", "Shower"};
        String[] daftarBahanMakanan = {"Nasi", "Kentang", "Ayam", "Sapi", "Wortel", "Bayam", "Kacang", "Susu"};
        String[] daftarMasakan = {"Nasi Ayam", "Nasi Kari", "Susu Kacang", "Tumis Sayur", "Bistik"};

        if (typeName.equals("Furniture")) {
            System.out.println("+-------------------------+----------+");
            System.out.println("| Furniture               | Jumlah   |");
            System.out.println("+-------------------------+----------+");
        } else if (typeName.equals("Bahan Makanan")) {
            System.out.println("+-------------------------+----------+");
            System.out.println("| Bahan Makanan           | Jumlah   |");
            System.out.println("+-------------------------+----------+");
        } if (typeName.equals("Masakan")) {
            System.out.println("+-------------------------+----------+");
            System.out.println("| Masakan                 | Jumlah   |");
            System.out.println("+-------------------------+----------+");
        }

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (typeName.equals("Furniture")) {
                for (String furniture : daftarFurniture) {
                    if (entry.getKey().equals(furniture)) {
                        System.out.printf("| %-23s | %-8d |\n", entry.getKey(), entry.getValue());
                        System.out.println("+-------------------------+----------+");
                    }
                }
            } else if (typeName.equals("Bahan Makanan")) {
                for (String bahanMakanan : daftarBahanMakanan) {
                    if (entry.getKey().equals(bahanMakanan)) {
                        System.out.printf("| %-23s | %-8d |\n", entry.getKey(), entry.getValue());
                        System.out.println("+-------------------------+----------+");
                    }
                }
            } else if (typeName.equals("Masakan")) {
                for (String masakan : daftarMasakan) {
                    if (entry.getKey().equals(masakan)) {
                        System.out.printf("| %-23s | %-8d |\n", entry.getKey(), entry.getValue());
                        System.out.println("+-------------------------+----------+");
                    }
                }
            }
        }
        System.out.println();
    } 
}
