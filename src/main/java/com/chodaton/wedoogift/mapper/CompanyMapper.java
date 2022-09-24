package com.chodaton.wedoogift.mapper;

import com.chodaton.wedoogift.model.dto.company.CompanyAccountDto;
import com.chodaton.wedoogift.model.dto.company.CompanyDto;
import com.chodaton.wedoogift.model.entity.company.Company;
import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toDao(CompanyDto companyDto){
       return new Company(companyDto.getId(), companyDto.getCode(), companyDto.getName());
    }

    public CompanyDto toDto(Company company){
        return new CompanyDto(company.getId(), company.getCompanyCode(), company.getCompanyName());
    }

    public CompanyAccount toDao(Company company, Double amount){
        return new CompanyAccount(company, amount);
    }

    public CompanyAccountDto toDto(CompanyAccount companyAccount){
        return new CompanyAccountDto(companyAccount.getId(), toDto(companyAccount.getCompany()),
                 companyAccount.getAmount() );
    }


}
