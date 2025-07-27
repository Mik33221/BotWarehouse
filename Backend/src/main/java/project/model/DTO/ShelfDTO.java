package project.model.DTO;

import project.model.Cord;

public class ShelfDTO {
    private String id;
    private Cord cord;
    private boolean occupied;

    public ShelfDTO() {
    }

    public ShelfDTO(String id, Cord cord, boolean occupied) {
        this.id = id;
        this.cord = cord;
        this.occupied = occupied;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cord getCord() {
        return cord;
    }

    public void setCord(Cord cord) {
        this.cord = cord;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
