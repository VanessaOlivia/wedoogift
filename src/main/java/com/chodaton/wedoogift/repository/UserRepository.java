package com.chodaton.wedoogift.repository;

import com.chodaton.wedoogift.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}