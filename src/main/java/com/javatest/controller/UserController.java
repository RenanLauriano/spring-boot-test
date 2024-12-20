package com.javatest.controller;

import com.javatest.domain.user.User;
import com.javatest.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(
            @RequestParam(defaultValue="0", name = "from") int offset,
            @RequestParam(defaultValue="100") int to) {
        int limit = (to - offset) + 1;
        if(offset > 0)
            offset -= 1;
        logger.info("Call received at /users. Parameters - From: {} - To: {}", offset, to);
        return userService.getUsers(limit, offset);
    }
}
