package com.javatest.domain.user;

import com.javatest.domain.user.location.Location;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gender;

    @Embedded
    private Name name;

    @Embedded
    private Location location;

    private String email;

    @Embedded
    private Login login;

    @Embedded
    private DOB dob;

    @Embedded
    private Registered registered;

    private String phone;
    private String cell;

    @Embedded
    private Picture picture;

    private String nat;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> idDetails = new HashMap<>();
}
