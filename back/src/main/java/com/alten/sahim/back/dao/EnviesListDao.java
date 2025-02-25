package com.alten.sahim.back.dao;

import com.alten.sahim.back.entity.EnviesList;
import com.alten.sahim.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnviesListDao extends JpaRepository<EnviesList, Long> {
    EnviesList findByUser(User user);
}