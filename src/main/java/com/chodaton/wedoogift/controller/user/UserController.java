package com.chodaton.wedoogift.controller.user;

import com.chodaton.wedoogift.mapper.UserMapper;
import com.chodaton.wedoogift.model.dto.user.UserDto;
import com.chodaton.wedoogift.model.dto.user.UserDepositDto;
import com.chodaton.wedoogift.service.user.UserDepositService;
import com.chodaton.wedoogift.service.user.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "gladyapi_acces")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDepositService userDepositService;

    @Autowired
    UserMapper mapper;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto){
        return mapper.toDto(this.userService.createUser(userDto));
    }

    @GetMapping("/{userId}")
    public List<UserDepositDto> getAllUserDeposit(@PathVariable Integer userId){
        return this.userDepositService.getAllUserDeposit(userId).stream()
                .map(mapper::toDto).toList();
    }

    @GetMapping("/deposit/{userDepositId}/balance")
    public Double getBalanceByDeposit(@PathVariable Integer userDepositId){
        return userDepositService.calculateBalance(userDepositId);
    }


}
