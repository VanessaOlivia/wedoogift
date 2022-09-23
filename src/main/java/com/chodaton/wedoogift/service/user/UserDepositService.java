package com.chodaton.wedoogift.service.user;

import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.repository.user.UserDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDepositService {

    @Autowired
    UserDepositRepository userDepositRepository;

    public UserDeposit createUserDeposit(UserDeposit userDeposit){
       return userDepositRepository.save(userDeposit);
    }

}
