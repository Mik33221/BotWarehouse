package project.model;

import java.util.concurrent.ThreadLocalRandom;

public class Robot implements GridEntity {
    private static int count = 0;
    private int id;
    private Cord cord;
    private WarehouseGrid warehouseGrid;
    private int gridWidth;
    private int gridHeight;
    private boolean carrying;
    private Item carriedItem;
    private Cord target;

    private Cord[] moveSet = {
            new Cord(0, 1),   // Up
            new Cord(0, -1),  // Down
            new Cord(-1, 0),  // Left
            new Cord(1, 0)    // Right
    };

    public Robot(WarehouseGrid warehouseGrid) {
        this.id = count++;
        this.gridWidth = warehouseGrid.getWidth();
        this.gridHeight = warehouseGrid.getHeight();
        carrying = false;
        carriedItem = null;
    }

    public void setDestination(Cord target) {
        this.target = target;
    }

    public Cord randomNextMove(){
        Cord randomMove = moveSet[ThreadLocalRandom.current().nextInt(moveSet.length)];
        return randomMove;
    }

    public void permormRandomStep(){
        Cord nextStep = randomNextMove();
        Cord nextPosition = this.cord.add(nextStep);
        if (nextPosition.getX() >= 0 && nextPosition.getX() < gridWidth &&
                nextPosition.getY() >= 0 && nextPosition.getY() < gridHeight) {
            this.cord = nextPosition;
        }
    }

    public Cord findNextMove(){
        // TODO: Pathfinding that should ignore everything, just pick next step
        return new Cord(-1,-1);
    }

    public void performStep() {
        Cord nextStep = findNextMove();
        if (nextStep.getX() >= 0 && nextStep.getY() >= 0) {
            cord = nextStep;
        }
    }

    public void setCord(Cord cord) {
        this.cord = cord;
    }

    public Cord getCord() {
        return cord;
    }

    public int getID(){
        return id;
    }
}
