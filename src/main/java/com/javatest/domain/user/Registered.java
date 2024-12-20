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
public class Registered {
    @Column(name = "registered_date")
    private String date;

    @Column(name = "registered_age")
    private int age;
}
