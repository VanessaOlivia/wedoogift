package com.chodaton.wedoogift.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.chodaton.wedoogift.model.entity.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Integer id;
    private final String code;
    private final String firstName;
    private final String lastName;
}