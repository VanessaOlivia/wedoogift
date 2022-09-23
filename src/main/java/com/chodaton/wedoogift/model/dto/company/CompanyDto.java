package com.chodaton.wedoogift.model.dto.company;

import com.chodaton.wedoogift.model.entity.company.Company;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Company} entity
 */
@Data
public class CompanyDto implements Serializable {
    private final Integer id;
    private final String code;
    private final String name;
}