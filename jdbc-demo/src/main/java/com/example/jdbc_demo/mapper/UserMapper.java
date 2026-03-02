package com.example.jdbc_demo.mapper;

import com.example.jdbc_demo.Entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper implements RowMapper<User> {
    public Object[] toParams(User user) {
        return new Object[]{
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
              //  user.getPassword(),
                user.getAddress(),
                user.getUserStatus()
        };
    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setEmail(rs.getString("email"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setPhoneNumber(rs.getString("phone_number"));
       // user.setPassword(rs.getString("password"));
        user.setAddress(rs.getString("address"));
        user.setUserStatus(rs.getString("user_status"));
        return user;
    }
}