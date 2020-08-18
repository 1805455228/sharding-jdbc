package com.example.shardingjdbc.controller;

import com.example.shardingjdbc.entity.User;
import com.example.shardingjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author zhanglei
 * Created by zhanglei on 2020/8/18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public String createUser() {
        User user = new User(22, "test", 18, LocalDateTime.now().toString());
        userService.addUser(user);
        return "success";
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }
}
