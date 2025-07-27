package project.model.DTO;

import java.util.List;

public class GridDTO {
    private int width;
    private int height;
    private List<RobotDTO> robots;
    private List<ShelfDTO> shelves;

    public GridDTO(int width, int height, List<RobotDTO> robots, List<ShelfDTO> shelves) {
        this.width = width;
        this.height = height;
        this.robots = robots;
        this.shelves = shelves;
    }

    public List<RobotDTO> getRobots() {
        return robots;
    }

    public List<ShelfDTO> getShelves() {
        return shelves;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
