package com.xyz.user.controller;

import com.xyz.user.VO.ResponseTempleteVO;
import com.xyz.user.entity.User;
import com.xyz.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.info("inside createUser");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTempleteVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("inside getUserWithDepartment");

        return userService.findbyUserId(userId);
    }
}
