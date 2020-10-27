package com.feazesa.projection.replayer;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
@Log4j2
public class ReplayerListener implements ApplicationListener<ApplicationReadyEvent> {

    private final Replayer replayer;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        replayer.replay();
    }

}
