package com.axr.lxt.controller.user;

import com.axr.lxt.mapper.UserMapper;
import com.axr.lxt.pojo.User;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/user/")
    public List<User> getAllUser() {
//        System.out.println(userMapper.selectList(null));
        return userMapper.selectList(null);

    }

    @GetMapping("/user/{id}/")
    public User getById(@PathVariable Integer id) {
        return userMapper.selectById(id);
    }
}
