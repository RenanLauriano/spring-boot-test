package com.javatest.domain.randomuser;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseUserList {
    private List<ResponseUser> results;
}
