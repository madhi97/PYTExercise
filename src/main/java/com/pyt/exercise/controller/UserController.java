package com.pyt.exercise.controller;

import com.pyt.exercise.model.UserModel;
import com.pyt.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUserById/{user_id}")
    public Object getUserById(@PathVariable int user_id){
        return userService.getUserById(user_id);
    }

    @GetMapping("/getUserByTier/{tier}")
    public List<UserModel> getUserByTier(@PathVariable String tier){
        return userService.getUsersByTier(tier);
    }





}
