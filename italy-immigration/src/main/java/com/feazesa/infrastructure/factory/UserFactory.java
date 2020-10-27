package com.feazesa.infrastructure.factory;

import com.feazesa.User;
import com.feazesa.UserSavedCommand;
import com.feazesa.infrastructure.utils.FactoryUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.axonframework.messaging.unitofwork.DefaultUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.h2.api.Aggregate;
import org.h2.engine.UserAggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserFactory {

    private URLReaderFactory urlReaderFactory;
    private FactoryUtils factoryUtils;
    private List<String> personData;
    @Getter
    private List<User> users;

    public void process() {
        users = new ArrayList<>();
        urlReaderFactory.urls()
                .forEach(this::extractDataFromPage);
    }

    public void extractDataFromPage(String url) {
        log.info("extracting info from {}", url);
        log.info("...:::::INI:::::...");
        urlReaderFactory.readContentOfURL(url)
                .map(String::strip)
                .filter(factoryUtils::matchPartsOfHTMLFileToExtractUserInfo)
                .skip(5)  // header can be ignored
                .forEach(this::bufferLinesToMapToUser);

        log.info("...:::::END:::::...");
    }

    public void bufferLinesToMapToUser(String line) {
        //every five lines contains information about one person
        if (personData.size() < 5) {
            personData.add(line);
        } else {
            mapToUser(personData);
            //initialize a new user buffer
            personData = new ArrayList<>();
            personData.add(line);
        }

    }

    public void mapToUser(List<String> lines) {
        final var name = factoryUtils.getUserInfoFromStringLine(lines.get(0));
        final var lastName = factoryUtils.getUserInfoFromStringLine(lines.get(1));
        final var age = factoryUtils.getUserInfoFromStringLine(lines.get(2));
        final var arrival = factoryUtils.getUserInfoFromStringLine(lines.get(3));
        final var nationality = factoryUtils.getUserInfoFromStringLine(lines.get(4));


        final var userSavedCommand = UserSavedCommand.builder()
                .userId(UUID.randomUUID().toString())
                .name(name)
                .lastName(lastName)
                .age(age)
                .arrival(arrival)
                .nationality(nationality)
                .build();

        new User(userSavedCommand);
    }

}
