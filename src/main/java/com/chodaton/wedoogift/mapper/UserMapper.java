package com.chodaton.wedoogift.mapper;

import com.chodaton.wedoogift.model.dto.user.UserDto;
import com.chodaton.wedoogift.model.entity.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDao(UserDto userDto){
       return new User(userDto.getId(), userDto.getCode(), userDto.getFirstName(),
               userDto.getLastName());
    }

    public UserDto toDto(User user){
        return new UserDto(user.getId(), user.getCode(), user.getFirstName(),
                user.getLastName());
    }

}
