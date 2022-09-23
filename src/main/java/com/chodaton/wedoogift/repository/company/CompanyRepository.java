package com.chodaton.wedoogift.repository.company;

import com.chodaton.wedoogift.model.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}