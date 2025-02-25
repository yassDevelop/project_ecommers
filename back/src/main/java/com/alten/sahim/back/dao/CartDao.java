package com.alten.sahim.back.dao;

import com.alten.sahim.back.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}