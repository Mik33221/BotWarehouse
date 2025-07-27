package project.model;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.model.DTO.GridDTO;
import project.simulation.SimulationEngine;
import project.view.WarehousePrinter;

@SpringBootApplication
@RestController
public class BackendApplication {
    private static WarehouseGrid warehouse;
    private static WarehousePrinter printer;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        warehouse = new WarehouseGrid(6,6);
        printer = new WarehousePrinter();

        Robot r1 = warehouse.addRobot();
        Robot r2 = warehouse.addRobot();
        Shelf s1 = warehouse.addShelf();
        Shelf s2 = warehouse.addShelf();

        r1.setCord(new Cord(1,2));
        r2.setCord(new Cord(2,2));
        s1.setCord(new Cord(4,3));
        s2.setCord(new Cord(4,4));

        SimulationEngine simEngine = new SimulationEngine(warehouse);
        Thread simThread = new Thread(simEngine);
        simThread.start();
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/gridDTO")
    public GridDTO gridDTO() {
        return warehouse.getGridDTO();
    }

    @GetMapping("/grid")
    public String grid() {
        String out = printer.stringify(warehouse);
        return out;
    }
}