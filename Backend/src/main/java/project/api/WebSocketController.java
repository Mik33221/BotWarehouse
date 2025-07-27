package project.api;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import project.model.DTO.GridDTO;
import project.model.WarehouseGrid;

@Controller
public class WebSocketController {
    private WarehouseGrid warehouse;

    public WebSocketController(WarehouseGrid warehouse) {
        this.warehouse = warehouse;
    }

    @MessageMapping("/state")
    @SendTo("/topic/grid")
    public GridDTO sendCurrentState() {
        return warehouse.getGridDTO();
    }
}
