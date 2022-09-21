package com.chodaton.wedoogift.repository;

import com.chodaton.wedoogift.model.entity.UserDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDepositRepository extends JpaRepository<UserDeposit, Integer> {
}