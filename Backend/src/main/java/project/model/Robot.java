package project.model;

public class Robot implements GridEntity {
    private static int count = 0;
    private int id;
    private Cord cord;
    private boolean carrying;
    private Item carriedItem;
    private Cord target;

    public Robot() {
        this.id = count++;
        carrying = false;
        carriedItem = null;
    }

    public void setDestination(Cord target) {
        this.target = target;
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
