package project.model.DTO;

import java.util.List;

public class GridDTO {
    private int width;
    private int height;
    private List<RobotDTO> robots;
    private List<ShelfDTO> shelves;

    public GridDTO() {
    }

    public GridDTO(int width, int height, List<RobotDTO> robots, List<ShelfDTO> shelves) {
        this.width = width;
        this.height = height;
        this.robots = robots;
        this.shelves = shelves;
    }

    public List<RobotDTO> getRobots() {
        return robots;
    }

    public void setRobots(List<RobotDTO> robots) {
        this.robots = robots;
    }

    public List<ShelfDTO> getShelves() {
        return shelves;
    }

    public void setShelves(List<ShelfDTO> shelves) {
        this.shelves = shelves;
    }
}
