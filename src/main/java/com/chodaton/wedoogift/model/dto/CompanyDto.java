package com.chodaton.wedoogift.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.chodaton.wedoogift.model.entity.Company} entity
 */
@Data
public class CompanyDto implements Serializable {
    private final Integer id;
    private final String code;
    private final String name;
}