package com.chodaton.wedoogift.repository.user;

import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDepositRepository extends JpaRepository<UserDeposit, Integer> {
}