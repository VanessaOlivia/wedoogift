package com.chodaton.wedoogift.service.user;

import com.chodaton.wedoogift.mapper.UserMapper;
import com.chodaton.wedoogift.model.dto.user.UserDto;
import com.chodaton.wedoogift.model.entity.user.UserIdentity;
import com.chodaton.wedoogift.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper mapper;

    public UserIdentity createUser(UserDto userDto){
        log.info("Creation de compte pour l'utilisateur {} ", userDto);
        return this.userRepository.save(mapper.toDao(userDto));
    }

    public Optional<UserIdentity> getUserById(Integer userId){
        return this.userRepository.findById(userId);
    }

}
