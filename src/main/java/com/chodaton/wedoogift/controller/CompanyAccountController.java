package com.chodaton.wedoogift.controller;

import com.chodaton.wedoogift.model.dto.CompanyAccountDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company-account")
public class CompanyAccountController {

    @PostMapping
    public CompanyAccountDto createCompanyAccount(@RequestBody CompanyAccountDto companyAccountDto){
        return null;
    }

    @PutMapping
    public CompanyAccountDto giveUserDeposit() {
        return null;
    }

}
