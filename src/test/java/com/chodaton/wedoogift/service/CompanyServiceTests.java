package com.chodaton.wedoogift.service;

import com.chodaton.wedoogift.exception.CompanyAccountNotFound;
import com.chodaton.wedoogift.exception.InsufficientBalanceException;
import com.chodaton.wedoogift.exception.UserNotFound;
import com.chodaton.wedoogift.model.entity.company.Company;
import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import com.chodaton.wedoogift.model.entity.user.User;
import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.repository.company.CompanyAccountRepository;
import com.chodaton.wedoogift.repository.company.CompanyRepository;
import com.chodaton.wedoogift.service.company.CompanyAccountService;
import com.chodaton.wedoogift.service.company.CompanyService;
import com.chodaton.wedoogift.service.user.UserDepositService;
import com.chodaton.wedoogift.service.user.UserService;
import com.chodaton.wedoogift.util.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;


@SpringBootTest(properties = "spring.main.lazy-initialization=true")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
 class CompanyServiceTests {

    @Mock
    CompanyService companyService;
   @InjectMocks
    CompanyAccountService companyAccountService;
    @Mock
    UserService userService;

   @Captor
   ArgumentCaptor<UserDeposit> userDepositArgumentCaptor;

   @Captor
   ArgumentCaptor<CompanyAccount> companyAccountArgumentCaptor;

   @Mock
   UserDepositService userDepositService;

    @Mock
    CompanyAccountRepository companyAccountRepository;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
   void givenNotExistingCompanyAccount_whenDonateUserDeposit_thenThrowNotFound() throws CompanyAccountNotFound {
      when(companyAccountRepository.findFirstByCompanyId(1)).thenReturn(Optional.empty());
      Assertions.assertThrows(CompanyAccountNotFound.class,
              () -> this.companyAccountService.donateUserDeposit(1, 1, Constant.MEAL, 200.0));
   }

   @Test
   void givenInsufficientBalance_whenDonateUserDeposit_thenThrowException() throws InsufficientBalanceException {
      CompanyAccount companyAccount = new CompanyAccount(getCompany(), 50.0);
       when(companyAccountRepository.findFirstByCompanyId(1)).thenReturn(Optional.of(companyAccount));
      Assertions.assertThrows(InsufficientBalanceException.class,
              () -> this.companyAccountService.donateUserDeposit(1, 1, Constant.MEAL, 200.0));
   }

   @Test
   void givenNotExistingUser_whenDonateUserDeposit_thenThrowNotFound() throws UserNotFound {
      CompanyAccount companyAccount = new CompanyAccount(getCompany(), 5000.0);
      when(companyAccountRepository.findFirstByCompanyId(1)).thenReturn(Optional.of(companyAccount));
      when(this.userService.getUserById(1)).thenReturn(Optional.empty());
      Assertions.assertThrows(UserNotFound.class,
              () -> this.companyAccountService.donateUserDeposit(1, 1, Constant.MEAL, 200.0));
   }

   @Test
   void givenExistingUserAndAccount_whenDonateUserDeposit_thenReturnCompanyBalance() throws UserNotFound {
      CompanyAccount companyAccount = new CompanyAccount(getCompany(), 5000.0);
      when(companyAccountRepository.findFirstByCompanyId(1)).thenReturn(Optional.of(companyAccount));
      when(this.userService.getUserById(1)).thenReturn(Optional.of(getUser()));

      this.companyAccountService.donateUserDeposit(1, 1, Constant.MEAL, 200.0);

      Mockito.verify(this.userDepositService).saveUserDeposit(userDepositArgumentCaptor.capture());
      UserDeposit userDeposit = userDepositArgumentCaptor.getValue();
      Assertions.assertEquals(200.0, userDeposit.getAmount());
      Mockito.verify(this.companyAccountRepository).save(companyAccountArgumentCaptor.capture());
      CompanyAccount updatedAccount = companyAccountArgumentCaptor.getValue();
      Assertions.assertEquals(4800.0, updatedAccount.getAmount());
    }

   private Company getCompany(){
      return new Company(1,"C01", "GLADY");
   }

   private User getUser(){
      return new User(1,"A01", "Vanessa", "Chodaton");
   }

}
