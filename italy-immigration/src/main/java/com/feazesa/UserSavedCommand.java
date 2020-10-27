package com.feazesa;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
@Getter
@ToString
public class UserSavedCommand  {

    private String userId;
    private String name;
    private String lastName;
    private String age;
    private String arrival;
    private String nationality;

}

