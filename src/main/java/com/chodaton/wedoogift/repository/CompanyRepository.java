package com.chodaton.wedoogift.repository;

import com.chodaton.wedoogift.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}