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
public class Picture {
    @Column(name = "picture_large")
    private String large;

    @Column(name = "picture_medium")
    private String medium;

    @Column(name = "picture_thumbnail")
    private String thumbnail;
}