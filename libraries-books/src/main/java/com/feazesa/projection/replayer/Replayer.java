package com.feazesa.projection.replayer;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@AllArgsConstructor
@Component
@Log4j2
public class Replayer {
    private final EventProcessingConfiguration configuration;

    public void replay() {
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
