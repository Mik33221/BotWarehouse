package project.simulation;

import project.api.WebSocketController;
import project.model.Robot;
import project.model.WarehouseGrid;

public class SimulationEngine implements Runnable {
    private WarehouseGrid warehouse;
    private OrderService orderService;
    private boolean running = true;
    private WebSocketController webSocket;


    public SimulationEngine(WarehouseGrid warehouse) {
        this.warehouse = warehouse;
        this.webSocket = new WebSocketController(warehouse);
    }

    @Override
    public void run() {
        while (running) {
            tick();
            broadcastState();

            try {
                Thread.sleep(1000); // simulate tick rate (e.g., 10 per second)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // preserve interrupt flag
                break;
            }
        }
    }

    public void tick() {
        for (Robot robot : warehouse.getRobots()) {
            robot.permormRandomStep();
        }
    }

    public void broadcastState() {
        webSocket.sendCurrentState();
    }
}
