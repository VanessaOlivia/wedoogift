package com.chodaton.wedoogift.service.company;

import com.chodaton.wedoogift.exception.CompanyNotFound;
import com.chodaton.wedoogift.mapper.CompanyMapper;
import com.chodaton.wedoogift.model.dto.company.CompanyDto;
import com.chodaton.wedoogift.model.entity.company.Company;
import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.repository.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyMapper mapper;

    public Company createCompany(CompanyDto companyDto){
        return this.companyRepository.save(mapper.toDao(companyDto));
    }

    public Company getCompanyById(Integer companyId){
        return this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFound(companyId));
    }

}
