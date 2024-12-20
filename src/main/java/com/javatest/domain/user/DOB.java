package com.javatest.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class DOB {
    @Column(name = "dob_date")
    private String date;

    @Column(name = "dob_age")
    private int age;
}
