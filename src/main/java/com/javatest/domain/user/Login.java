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
public class Login {
    @Column(name = "login_uuid")
    private String uuid;

    @Column(name = "login_username")
    private String username;

    @Column(name = "login_password")
    private String password;

    @Column(name = "login_salt")
    private String salt;

    @Column(name = "login_md5")
    private String md5;

    @Column(name = "login_sha1")
    private String sha1;

    @Column(name = "login_sha256")
    private String sha256;
}
