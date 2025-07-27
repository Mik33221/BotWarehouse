package project.api;

import project.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public Order placeOrder(@RequestBody List<String> itemTypes) {
        // TODO: Order recieving
        return null;
    }
}
