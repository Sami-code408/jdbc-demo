package com.example.jdbc_demo.service;



import com.example.jdbc_demo.exceptions.CustomMessageException;
import com.example.jdbc_demo.entity.User;
import com.example.jdbc_demo.dao.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {


    private UserDao userDao;

    public User saveUser(User user) {
        if (user.getId() == null || user.getId().isEmpty()) {
            user.setId(UUID.randomUUID().toString());
        } else {
            Optional<User> existing = getById(user.getId());
            if (existing.isPresent()) {
                throw new CustomMessageException(
                        "User Id already exists",
                        HttpStatus.CONFLICT
                );
            }
        }

        int rows = userDao.save(user);
        if (rows > 0) {
            return user;
        } else {
            throw new CustomMessageException(
                    "Failed to save user",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }

    public Optional<User> getById(String id) {
        User user = userDao.findById(id);
        return Optional.ofNullable(user);
    }

    public void deleteById(String id) {
        int rows = userDao.deleteById(id);
        if (rows == 0) {
            throw new CustomMessageException(
                    "User not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    public List<User>  findUser(String id, String email, String phoneNumber) {
        return userDao.findUser(id, email, phoneNumber);
    }

}
