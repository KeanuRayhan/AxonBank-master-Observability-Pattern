package org.axonframework.samples.bank.config;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.eventhandling.EventBus;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("axonBuses") // The name "axonBuses" will appear in the health details
public class CustomAxonHealthIndicator implements HealthIndicator {

    private final CommandBus commandBus;
    private final EventBus eventBus;

    // Autowire the buses you want to check (removed QueryBus since it's not available in this version)
    public CustomAxonHealthIndicator(CommandBus commandBus, EventBus eventBus) {
        this.commandBus = commandBus;
        this.eventBus = eventBus;
    }

    @Override
    public Health health() {
        // Add logic to check the health of your component.
        // For this example, we'll just check if they are not null (i.e., injected).
        // In a real scenario, you might perform a lightweight operation or check a status flag.

        boolean commandBusUp = commandBus != null;
        boolean eventBusUp = eventBus != null;

        if (commandBusUp && eventBusUp) {
            return Health.up()
                         .withDetail("commandBus", "Available")
                         .withDetail("eventBus", "Available")
                         .build();
        } else {
            return Health.down()
                         .withDetail("commandBus", commandBusUp ? "Available" : "Unavailable")
                         .withDetail("eventBus", eventBusUp ? "Available" : "Unavailable")
                         .build();
        }
    }
}