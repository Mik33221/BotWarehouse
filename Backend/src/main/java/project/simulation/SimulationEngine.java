package project.simulation;

import project.model.Robot;
import project.model.WarehouseGrid;

public class SimulationEngine implements Runnable {
    private WarehouseGrid grid;
    private OrderService orderService;
    private boolean running = true;


    public SimulationEngine(WarehouseGrid grid) {
        this.grid = grid;
    }

    @Override
    public void run() {
        while (running) {
            tick();
            //broadcastState();

            try {
                Thread.sleep(1000); // simulate tick rate (e.g., 10 per second)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // preserve interrupt flag
                break;
            }
        }
    }

    public void tick() {
        for (Robot robot : grid.getRobots()) {
            robot.permormRandomStep();
        }
    }

    public void broadcastState() {
        // TODO: wyślij stan do klientów
    }
}
