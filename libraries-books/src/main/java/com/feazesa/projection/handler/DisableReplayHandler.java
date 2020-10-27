package com.feazesa.projection.handler;

import com.feazesa.event.LibraryCreatedEvent;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DisableReplayHandler {

    @DisallowReplay
    @EventHandler
    public void disableReplayForThisHandler(LibraryCreatedEvent event) {
        log.warn(" :O Event {} should not be replayed!!", event);
    }
}
