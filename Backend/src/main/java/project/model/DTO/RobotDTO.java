package project.model.DTO;

import project.model.Cord;

public class RobotDTO {
    private int id;
    private int x;
    private int y;
    private boolean carrying;

    public RobotDTO(int id, Cord cord, boolean carrying) {
        this.id = id;
        this.x = cord.getX();
        this.y = cord.getY();
        this.carrying = carrying;
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

    public boolean isCarrying() {
        return carrying;
    }
}