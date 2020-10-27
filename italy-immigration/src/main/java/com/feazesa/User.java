package com.feazesa;


import lombok.Builder;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateLifecycle;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class User {
    @Id
    private String userId;
    private String name;
    private String lastName;
    private String age;
    private String arrival;
    private String nationality;

    @CommandHandler
    public User(UserSavedCommand userSavedCommand) {

        AggregateLifecycle.apply(
                new UserSavedEvent(
                        userSavedCommand.getUserId(),
                        userSavedCommand.getName(),
                        userSavedCommand.getLastName(),
                        userSavedCommand.getAge(),
                        userSavedCommand.getArrival(),
                        userSavedCommand.getNationality()
                ));
        System.out.println("command = " + userSavedCommand);
    }
}
