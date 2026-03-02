package com.example.jdbc_demo.dao;

import com.example.jdbc_demo.entity.User;
import com.example.jdbc_demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {


    private JdbcTemplate jdbcTemplate;
    private UserMapper userMapper;

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public int save(User user) {
        String sql = "INSERT INTO users (id, email, first_name, last_name, phone_number, password, address, user_status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, userMapper.toParams(user));
    }

    @Override
    public int update(User user) {
        String sql = "UPDATE user SET email=?, first_name=?, last_name=?, phone_number=?, password=?, address=?, user_status=? WHERE id=? ";
        return jdbcTemplate.update(sql, new UserMapper());
    }

    @Override
    public User findById(String id) {
        String sql = "SELECT * FROM users WHERE id=? OR where email=? OR first_name=?";
        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    BeanPropertyRowMapper.newInstance(User.class),
                    id
            );
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(String id) {
        String sql = "UPDATE users SET user_status = 'INACTIVE' WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, email, first_name, last_name, phone_number, address, user_status FROM users";
        return jdbcTemplate.query(sql, userMapper);
    }


    @Override
    public List<User> findUser(String id, String email, String phone) {

        StringBuilder sql = new StringBuilder("SELECT id, email, first_name, last_name, phone_number, address, user_status FROM users");
        boolean hasCondition = false;


        if (id != null) {
            sql.append(" WHERE id = ?");
            hasCondition = true;
        }
        if (email != null) {
            sql.append(hasCondition ? " AND" : " WHERE").append(" email = ?");
            hasCondition = true;
        }
        if (phone != null) {
            sql.append(hasCondition ? " AND" : " WHERE").append(" phone_number = ?");
        }


        List<Object> params = new ArrayList<>();
        if (id != null) params.add(id);
        if (email != null) params.add(email);
        if (phone != null) params.add(phone);

        return jdbcTemplate.query(sql.toString(), userMapper, params.toArray());
    }
}
