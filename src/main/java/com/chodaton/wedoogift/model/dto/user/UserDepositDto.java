package com.chodaton.wedoogift.model.dto.user;

import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link UserDeposit} entity
 */
@Data
public class UserDepositDto implements Serializable {
    private final Integer id;
    private final UserDto user;
    private final String depositType;
    private final Double amount;
    private final LocalDate depositDate;
}