//package com.feazesa;
//
//import com.feazesa.Events.UserSavedEvent;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.axonframework.commandhandling.CommandHandler;
//import org.axonframework.eventsourcing.EventSourcingHandler;
//import org.axonframework.modelling.command.AggregateIdentifier;
//import org.axonframework.modelling.command.AggregateLifecycle;
//import org.axonframework.spring.stereotype.Aggregate;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//@Aggregate
//@AllArgsConstructor(onConstructor = @__(@Autowired))
//@NoArgsConstructor
//public class UserAggregate {
//
//    @AggregateIdentifier
//    private String userId;
//
//    @CommandHandler
//    public UserAggregate(UserSavedCommand command) {
//        AggregateLifecycle.apply(
//                new UserSavedEvent(
//                        command.getUserId(),
//                        command.getName(),
//                        command.getLastName(),
//                        command.getAge(),
//                        command.getArrival(),
//                        command.getNationality()
//                ));
//        System.out.println("command = " + command);
//    }
//
//    @EventSourcingHandler
//    public void on(UserSavedEvent event) {
//        this.userId = event.getUserId();
//    }
//}
//
