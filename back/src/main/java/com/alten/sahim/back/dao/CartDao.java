package com.alten.sahim.back.dao;

import com.alten.sahim.back.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {
    Cart findByUser(User user);
}