package com.chodaton.wedoogift.controller;

import com.chodaton.wedoogift.model.dto.UserDto;
import com.chodaton.wedoogift.model.dto.UserDepositDto;
import com.chodaton.wedoogift.model.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping
    public UserDto createUser(@RequestBody User user){
        return null;
    }

    @GetMapping("/{userId}")
    public List<UserDepositDto> getAllUserDeposit(@RequestParam String userId){
        return null;
    }

}
