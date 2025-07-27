package project.model;

import java.util.ArrayList;

public class Shelf implements GridEntity {
    private static int count = 0;
    private int id;
    private Cord cord;
    private ArrayList<Item> items;

    public Shelf() {
        this.id = count++;
        this.items = new ArrayList<Item>();
    }

    public boolean hasAnyItem(){
        return items.size() > 0;
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

    public int getId(){
        return id;
    }

    public void setCord(Cord cord) {
        this.cord = cord;
    }

    public Cord getCord() {
        return cord;
    }
}
