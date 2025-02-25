package com.alten.sahim.back.service.impl;

import com.alten.sahim.back.dao.UserDao;
import com.alten.sahim.back.entity.User;
import com.alten.sahim.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() throws IOException {
        return userDao.findAll();
    }

    public Optional<User> getUserByEmail(String email) throws IOException {
        return userDao.findByEmail(email);
    }

    public User createUser(User user) throws IOException {
        return userDao.save(user);
    }
}