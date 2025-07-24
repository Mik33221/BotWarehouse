package project.model;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
    private static int count = 0;
    private int id;
    private int x, y;
    private List<Item> items;

    public Shelf(int x, int y) {
        this.id = count++;
        this.items = new ArrayList<Item>();
        this.x = x;
        this.y = y;
    }

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

    public int addItem(Item item) {
        items.add(item);
        return 1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getID(){
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
