package project.model;

import java.util.List;

public class Shelf {
    private int id;
    private int x, y;
    private List<Item> items;

    public boolean hasItem(String type) {
        return items.stream().anyMatch(item -> item.getType().equals(type));
    }

    public Item takeItem(String type) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            if (item.getType().equals(type)) {
                items.remove(i);
                return item;
            }
        }
        return null;
    }

    // Gettery/settery
}
