package com.example.jdbc_demo.dao;

import com.example.jdbc_demo.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAllUsers();
    int save(User user);

    int update(User user);

    User findById(String id);

    int deleteById(String id);




    List<User> findAll();
    List<User>  findUser(String id, String email, String phone);

  //  int deleteAll();
}
