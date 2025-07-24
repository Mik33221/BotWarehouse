package project.api;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/state")
    @SendTo("/topic/grid")
    public String sendCurrentState() {
        // TODO: zwróć aktualny stan całości aplikacji jako JSON
        return "{}";
    }
}
