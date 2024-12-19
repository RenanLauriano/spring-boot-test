package com.javatest.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Registered {
    @Column(name = "registered_date")
    private String date;

    @Column(name = "registered_age")
    private int age;
}
