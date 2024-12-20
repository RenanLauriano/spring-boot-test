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
public class Name {
    @Column(name = "name_title")
    private String title;

    @Column(name = "name_first")
    private String first;

    @Column(name = "name_last")
    private String last;
}