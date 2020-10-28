package com.feazesa.projection.replay;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
@Log4j2
@RestController
public class Replay {
    private final EventProcessingConfiguration configuration;

    @PostMapping("/admin/replay")
    public void replay() {
        log.warn("Replay started.");
        final var name = "com.feazesa.projection.handler";
        configuration.eventProcessor(name, TrackingEventProcessor.class).ifPresent(processor -> {
            processor.shutDown();
            processor.resetTokens();
            processor.start();
        });
    }

    @Value
    public static class Progress {
        private long current;
        private long tail;

        public BigDecimal getProgress() {
            return BigDecimal.valueOf(current, 2).divide(BigDecimal.valueOf(tail, 2));
        }
    }
}
