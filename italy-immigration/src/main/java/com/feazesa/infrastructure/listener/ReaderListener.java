package com.feazesa.infrastructure.listener;

import com.feazesa.infrastructure.factory.URLReaderFactory;
import com.feazesa.infrastructure.factory.UserFactory;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Component
@Log4j2
public class ReaderListener implements ApplicationListener<ApplicationReadyEvent> {

    private final UserFactory userFactory;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        userFactory.process();
    }
}
