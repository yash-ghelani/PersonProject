package com.qa.PersonProject.entities;

import lombok.Data;

@Data
public class PersonDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private int age;

    public PersonDTO() {
        super();
    }
}
