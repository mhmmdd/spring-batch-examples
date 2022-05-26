package io.spring.batch.database.domain;

import lombok.Value;

import java.util.Date;

@Value
public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
}
