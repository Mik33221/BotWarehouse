package project.model.DTO;

import project.model.Cord;

public class RobotDTO {
    private String id;
    private Cord cord;
    private boolean carryingItem;

    public RobotDTO() {
    }

    public RobotDTO(String id, Cord cord, boolean carryingItem) {
        this.id = id;
        this.cord = cord;
        this.carryingItem = carryingItem;
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

    public boolean isCarryingItem() {
        return carryingItem;
    }

    public void setCarryingItem(boolean carryingItem) {
        this.carryingItem = carryingItem;
    }
}
