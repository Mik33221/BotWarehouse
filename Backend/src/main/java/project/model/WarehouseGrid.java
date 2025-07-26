package project.model;

import java.util.ArrayList;

public class WarehouseGrid {
    private int width;
    private int height;
    private ArrayList<Shelf> shelves;
    private ArrayList<Robot> robots;

    public WarehouseGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.shelves = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    public Shelf addShelf(){
        Shelf shelf = new Shelf();
        shelves.add(shelf);
        return shelf;
    }

    public Robot addRobot(){
        Robot robot = new Robot(this);
        robots.add(robot);
        return robot;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public ArrayList<Shelf> getShelves() {
        return shelves;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
