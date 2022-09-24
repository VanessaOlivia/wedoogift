package com.chodaton.wedoogift.repository.user;

import com.chodaton.wedoogift.model.entity.user.UserIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserIdentity, Integer> {
}