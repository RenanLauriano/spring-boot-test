package com.javatest.domain.randomuser;


import com.javatest.domain.user.DOB;
import com.javatest.domain.user.Login;
import com.javatest.domain.user.Name;
import com.javatest.domain.user.Picture;
import com.javatest.domain.user.Registered;
import com.javatest.domain.user.location.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseUser {
    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private DOB dob;
    private Registered registered;
    private String phone;
    private String cell;
    private Picture picture;
    private String nat;
    private Map<String, String> idDetails = new HashMap<>();
}

