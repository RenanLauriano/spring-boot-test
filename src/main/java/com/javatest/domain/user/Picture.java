package com.javatest.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Picture {
    @Column(name = "picture_large")
    private String large;

    @Column(name = "picture_medium")
    private String medium;

    @Column(name = "picture_thumbnail")
    private String thumbnail;
}