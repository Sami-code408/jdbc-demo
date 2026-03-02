package com.example.jdbc_demo.controller;

import com.example.jdbc_demo.entity.User;
import com.example.jdbc_demo.constant.RequestMappingConstants;
import com.example.jdbc_demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RequestMappingConstants.API)
public class UserController {
    private final UserService userService;

    public UserController (UserService  userService) {
        this.userService = userService;
    }

    @PostMapping(RequestMappingConstants.ADD_USER)
    public User create(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @GetMapping(RequestMappingConstants.GET_ALL_USERS)
    public List<User> getAll() {
        return userService.getAllUsers();
    }


    @GetMapping(RequestMappingConstants.GET_USER)
    public User getById(@PathVariable("id") String id) {
        return userService.getById(id).orElse(null);
    }


    @PutMapping(RequestMappingConstants.UPDATE_USER)
    public User update(@PathVariable("id") String id,
                               @RequestBody User user) {
       user.setId(id);
        return userService.saveUser(user);
    }


    @DeleteMapping(RequestMappingConstants.DELETE_USER)
    public String delete(@PathVariable String id) {
        userService.deleteById(id);
        return "Deleted Successfully";
    }

    @GetMapping("/user")
    public List<User> getUser(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber
    ) {


        return userService.findUser(id, email, phoneNumber);
    }
}
