package com.javatest.domain.user.location;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
class Coordinates {
    private String latitude;
    private String longitude;
}