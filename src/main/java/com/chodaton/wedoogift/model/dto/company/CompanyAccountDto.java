package com.chodaton.wedoogift.model.dto.company;

import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link CompanyAccount} entity
 */
@Data
public class CompanyAccountDto implements Serializable {
    private final Integer id;
    private final CompanyDto company;
    private final Double amount;
}