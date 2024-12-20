package com.javatest.domain.user.location;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
class Coordinates {
    private String latitude;
    private String longitude;
}