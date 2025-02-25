package com.alten.sahim.back.service;

import com.alten.sahim.back.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    public List<User> getAllUsers() throws IOException ;

    public Optional<User> getUserByEmail(String email) throws IOException ;

    public User createUser(User user) throws IOException ;
}