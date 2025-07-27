package project.model;

import project.model.DTO.GridDTO;
import project.model.DTO.RobotDTO;
import project.model.DTO.ShelfDTO;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public GridDTO getGridDTO(){

        List<RobotDTO> robotDTOs = robots.stream()
                .map(robot -> new RobotDTO(robot.getId(), robot.getCord(), robot.isCarrying()))
                .collect(Collectors.toList());

        List<ShelfDTO> shelfDTOs = shelves.stream()
                .map(shelf -> new ShelfDTO(shelf.getId(), shelf.getCord(), shelf.hasAnyItem()))
                .collect(Collectors.toList());

        return new GridDTO(width, height, robotDTOs, shelfDTOs);
    }
}
