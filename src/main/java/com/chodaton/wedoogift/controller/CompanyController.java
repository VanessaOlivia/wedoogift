package com.chodaton.wedoogift.controller;

import com.chodaton.wedoogift.model.dto.CompanyDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController {

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        return null;
    }

}
