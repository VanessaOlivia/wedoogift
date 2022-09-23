package com.chodaton.wedoogift.service.user;

import com.chodaton.wedoogift.exception.UserDepositNotFound;
import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.repository.user.UserDepositRepository;
import com.chodaton.wedoogift.util.Constant;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserDepositService {

    @Autowired
    UserDepositRepository userDepositRepository;

    public UserDeposit saveUserDeposit(UserDeposit userDeposit){
       return userDepositRepository.save(userDeposit);
    }

    public Double calculateBalance(Integer userDepositId) throws UserDepositNotFound {
        UserDeposit userDeposit = this.userDepositRepository.findById(userDepositId)
                .orElseThrow(()->new UserDepositNotFound(userDepositId));
        Double balance = 0.0;
        LocalDate expirationDepositDate = null;
        if(Constant.GIFT.equals(userDeposit.getDepositType())){
            expirationDepositDate = userDeposit.getDepositDate().plusDays(Constant.EXPIRATION_GIFT_DELAY);
        }
        else if(Constant.MEAL.equals(userDeposit.getDepositType())){
            Integer depositYear = userDeposit.getDepositDate().getYear();
            LocalDate tempDepositDate = LocalDate.of(depositYear + 1, 2, 1 );
            //En fonction du type d'année (bissextile ou non) le dernier jour de fevrier peut varier
            Integer febLengthDay = tempDepositDate.lengthOfMonth();
            expirationDepositDate = tempDepositDate.withDayOfMonth(febLengthDay);
        }
        if(ObjectUtils.isNotEmpty(expirationDepositDate)
                && expirationDepositDate.isAfter(LocalDate.now())){
            balance = userDeposit.getAmount();
        }
        return balance;
    }

    public List<UserDeposit> getAllUserDeposit(Integer userId){
        return this.userDepositRepository.findByUserId(userId);
    }

}
