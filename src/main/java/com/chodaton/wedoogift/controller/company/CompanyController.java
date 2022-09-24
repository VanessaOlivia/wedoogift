package com.chodaton.wedoogift.controller.company;

import com.chodaton.wedoogift.exception.UnknownDepositType;
import com.chodaton.wedoogift.mapper.CompanyMapper;
import com.chodaton.wedoogift.model.dto.company.CompanyAccountDto;
import com.chodaton.wedoogift.model.dto.company.CompanyDto;
import com.chodaton.wedoogift.model.entity.company.Company;
import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import com.chodaton.wedoogift.service.company.CompanyAccountService;
import com.chodaton.wedoogift.service.company.CompanyService;
import com.chodaton.wedoogift.util.Constant;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@SecurityRequirement(name = "gladyapi_acces")
@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyAccountService companyAccountService;

    @Autowired
    CompanyMapper mapper;

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        Company savedCompany = companyService.createCompany(companyDto);
        return mapper.toDto(savedCompany);
    }

    @PostMapping("/{companyId}/account-creation")
    public CompanyAccountDto createCompanyAccount(@PathVariable Integer companyId, @RequestParam Double amount){
        CompanyAccount accountCreated = this.companyAccountService.saveAccount(companyId, amount);
        return mapper.toDto(accountCreated);
    }

    @PostMapping("/{companyId}/deposit-donation/{userId}")
    public CompanyAccountDto createUserDeposit(@PathVariable Integer companyId, @PathVariable Integer userId,
                                               @RequestParam String depositType, @RequestParam Double amount)  {

      if(!Arrays.asList(Constant.GIFT, Constant.MEAL).contains(depositType)) throw new UnknownDepositType();
        return mapper.toDto(this.companyAccountService.donateUserDeposit(companyId, userId, depositType, amount));
    }


}
