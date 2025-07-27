package project.model.DTO;

import project.model.Cord;

public class ShelfDTO {
    private int id;
    private int x;
    private int y;
    private boolean occupied;

    public ShelfDTO(int id, Cord cord, boolean occupied) {
        this.id = id;
        this.x = cord.getX();
        this.y = cord.getY();
        this.occupied = occupied;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isOccupied() {
        return occupied;
    }
}