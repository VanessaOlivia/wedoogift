package com.chodaton.wedoogift.repository;

import com.chodaton.wedoogift.model.entity.CompanyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Integer> {
}