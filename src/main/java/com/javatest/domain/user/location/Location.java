package com.javatest.domain.user.location;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
public class Location {
    private String streetNumber;
    private String streetName;
    private String city;
    private String state;
    private String country;
    private String postcode;

    @Embedded
    private Coordinates coordinates;

    @Embedded
    private Timezone timezone;
}
