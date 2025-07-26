package project.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");   // frontend listens at /topic/grid
        config.setApplicationDestinationPrefixes("/app");       // frontend sends to /app/state
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")      // Main endpoint
                .setAllowedOriginPatterns("*")  // Allow all domains
                .withSockJS();                  // Fallback to HTTP
    }
}
