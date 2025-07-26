package project.model;

public class Cord {
    private int x;
    private int y;

    public Cord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cord add(Cord other) {
        return new Cord(this.x + other.x, this.y + other.y);
    }

    public Cord subtract(Cord other) {
        return new Cord(this.x - other.x, this.y - other.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
