package com.chodaton.wedoogift.repository.company;

import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyAccountRepository extends JpaRepository<CompanyAccount, Integer> {
    Optional<CompanyAccount> findFirstByCompanyId(Integer id);

}