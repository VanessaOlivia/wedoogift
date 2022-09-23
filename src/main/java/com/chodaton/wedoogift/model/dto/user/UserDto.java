package com.chodaton.wedoogift.model.dto.user;

import com.chodaton.wedoogift.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
@AllArgsConstructor
public class UserDto implements Serializable {
    private final Integer id;
    private final String code;
    private final String firstName;
    private final String lastName;

    @Override
    public String toString(){
        return String.format("code: %s , firstName: %s , lastName: %s", code, firstName, lastName);
    }

}