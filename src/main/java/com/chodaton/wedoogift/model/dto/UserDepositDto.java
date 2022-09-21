package com.chodaton.wedoogift.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.chodaton.wedoogift.model.entity.UserDeposit} entity
 */
@Data
public class UserDepositDto implements Serializable {
    private final Integer id;
    private final UserDto userDto;
    private final String depositType;
    private final Double amount;
    private final LocalDate depositDate;
}