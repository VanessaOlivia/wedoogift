package com.chodaton.wedoogift.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.chodaton.wedoogift.model.entity.CompanyAccount} entity
 */
@Data
public class CompanyAccountDto implements Serializable {
    private final Integer id;
    private final CompanyDto companyDto;
    private final String depositType;
    private final String amount;
}