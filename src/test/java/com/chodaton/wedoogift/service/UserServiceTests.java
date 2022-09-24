package com.chodaton.wedoogift.service;

import com.chodaton.wedoogift.exception.UserDepositNotFound;
import com.chodaton.wedoogift.model.entity.user.UserIdentity;
import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.repository.user.UserDepositRepository;
import com.chodaton.wedoogift.service.user.UserDepositService;
import com.chodaton.wedoogift.util.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;


@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class UserServiceTests {

    @InjectMocks
    UserDepositService userDepositService;
    @Spy
    UserDepositRepository userDepositRepository;


    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenNotExistingUserDepositId_whenGetBalance_thenThrowNotFound() throws UserDepositNotFound {
        when(userDepositRepository.findById(1)).thenReturn(Optional.empty());
        Assertions.assertThrows(UserDepositNotFound.class, () -> {
            this.userDepositService.calculateBalance(1);
        });
    }

    @Test
    void givenNotExpiredGiftDeposit_whenGetBalance_thenReturnNonNullValue(){
        UserDeposit userDeposit = new UserDeposit(getUser(), Constant.GIFT,450.0, LocalDate.of(2021, 12,14) );
        when(userDepositRepository.findById(1)).thenReturn(Optional.of(userDeposit));
        Double balance = userDepositService.calculateBalance(1);
        Assertions.assertEquals(450.0, balance);
    }

    @Test
    void givenExpiredGiftDeposit_whenGetBalance_thenReturnNullValue(){
        UserDeposit userDeposit = new UserDeposit(getUser(), Constant.GIFT,300.0, LocalDate.of(2021, 6,25) );
        when(userDepositRepository.findById(1)).thenReturn(Optional.of(userDeposit));
        Double balance = userDepositService.calculateBalance(1);
        Assertions.assertEquals(0.0, balance);
    }

    @Test
    void givenNotExpiredMealDeposit_whenGetBalance_thenReturnNonNullValue(){
        UserDeposit userDeposit = new UserDeposit(getUser(), Constant.MEAL,2300.0, LocalDate.of(2022, 1,1) );
        when(userDepositRepository.findById(1)).thenReturn(Optional.of(userDeposit));
        Double balance = userDepositService.calculateBalance(1);
        Assertions.assertEquals(2300.0, balance);
    }


    @Test
    void givenExpiredMealDeposit_whenGetBalance_thenReturnNullValue(){
        UserDeposit userDeposit = new UserDeposit(getUser(), Constant.MEAL,300.0, LocalDate.of(2021, 12,14) );
        when(userDepositRepository.findById(1)).thenReturn(Optional.of(userDeposit));
        Double balance = userDepositService.calculateBalance(1);
        Assertions.assertEquals(0.0, balance);
    }

    private UserIdentity getUser(){
        return new UserIdentity(1,"A01", "Vanessa", "Chodaton");
    }

}
