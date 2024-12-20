package com.javatest.controller;

import com.javatest.domain.user.User;
import com.javatest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(
            @RequestParam(defaultValue="0", name = "from") int offset,
            @RequestParam(defaultValue="100") int to) {
        int limit = (to - offset) + 1;
        offset -= 1;
        return userService.getUsers(limit, offset);
    }
}
