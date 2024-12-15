package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @QueryMapping
    public UserDTO findUserById(@Argument Long id) {
        return userService.findUserById(id);
    }

    @MutationMapping
    public UserDTO saveUser(@Argument UserDTO user) {
        return userService.saveUser(user);
    }

    @MutationMapping
    public UserDTO updateUser(@Argument Long id, @Argument UserDTO user) {
        return userService.updateUser(id, user);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Long id) {
        return userService.deleteUser(id);
    }

    @QueryMapping
    public UserDTO findUserByEmail(@Argument String email) {
        return userService.findByEmail(email);
    }
}


