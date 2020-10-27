package com.feazesa;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserSavedEvent {
        private final String userId;
        private final String name;
        private final String lastName;
        private final String age;
        private final String arrival;
        private final String nationality;
}
