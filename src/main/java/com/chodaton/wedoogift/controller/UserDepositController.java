package com.chodaton.wedoogift.controller;

import com.chodaton.wedoogift.model.dto.UserDepositDto;
import com.chodaton.wedoogift.model.entity.UserDeposit;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("user-deposit")
public class UserDepositController {

    @PostMapping
    public UserDepositDto createUserDeposit(@RequestBody UserDeposit userDeposit){
        return null;
    }

    @GetMapping("/{userDepositId}")
    public UserDepositDto getUserDeposit(@RequestParam String userDepositId){
        return null;
    }

    @PutMapping("/{userDepositId}/{expenseAmount}")
    public UserDepositDto updateUserDepositAmount(@RequestParam String userDepositId, @RequestParam Double expenseAmount){
        return null;
    }

}
