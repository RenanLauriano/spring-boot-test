package com.javatest.domain.user.location;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@ToString
class Timezone {
    @Column(name = "timezone_offset")
    private String offset;

    @Column(name = "timezone_description")
    private String description;
}
