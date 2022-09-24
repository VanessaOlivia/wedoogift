package com.chodaton.wedoogift.mapper;

import com.chodaton.wedoogift.model.dto.user.UserDepositDto;
import com.chodaton.wedoogift.model.dto.user.UserDto;
import com.chodaton.wedoogift.model.entity.user.UserIdentity;
import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserIdentity toDao(UserDto userDto){
       return new UserIdentity(userDto.getId(), userDto.getCode(), userDto.getFirstName(),
               userDto.getLastName());
    }

    public UserDto toDto(UserIdentity user){
        return new UserDto(user.getId(), user.getUserCode(), user.getFirstName(),
                user.getLastName());
    }

    public UserDeposit toDao(UserDepositDto userDepositDto){
        UserDeposit userDeposit = new UserDeposit(toDao(userDepositDto.getUser()),
                userDepositDto.getDepositType(), userDepositDto.getAmount(),
                userDepositDto.getDepositDate());
        userDeposit.setId(userDeposit.getId());
        return userDeposit;
    }

    public UserDepositDto toDto(UserDeposit user){
        return new UserDepositDto(user.getId(), toDto(user.getUser()),
                user.getDepositType(), user.getAmount(),
                user.getDepositDate());
    }

}
