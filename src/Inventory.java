import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> items = new HashMap<String, Integer>();

    public Inventory() {
        items = null;
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
        System.out.println("Item in inventory : ");
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void printSpecificItem (String typeName) {
        //print specific item in item
        System.out.println("Item in inventory : ");
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (entry.getKey().contains(typeName)) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    } 
}
