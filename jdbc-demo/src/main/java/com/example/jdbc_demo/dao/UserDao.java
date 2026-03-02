package com.example.jdbc_demo.dao;

import com.example.jdbc_demo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
    int save(User user);

    int update(User user);

    User findById(String id);

    int deleteById(String id);

    List<User> findAll();
    List<User>  findUser(String id, String email, String phone);

}
