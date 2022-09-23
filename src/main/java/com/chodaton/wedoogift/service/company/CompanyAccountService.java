package com.chodaton.wedoogift.service.company;

import com.chodaton.wedoogift.exception.CompanyAccountNotFound;
import com.chodaton.wedoogift.exception.CompanyNotFound;
import com.chodaton.wedoogift.exception.InsufficientBalanceException;
import com.chodaton.wedoogift.exception.UserNotFound;
import com.chodaton.wedoogift.mapper.CompanyMapper;
import com.chodaton.wedoogift.model.entity.company.Company;
import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import com.chodaton.wedoogift.model.entity.user.User;
import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.repository.company.CompanyAccountRepository;
import com.chodaton.wedoogift.service.user.UserDepositService;
import com.chodaton.wedoogift.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CompanyAccountService {

    @Autowired
    CompanyAccountRepository companyAccountRepository;
    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;

    @Autowired
    UserDepositService userDepositService;

    @Autowired
    CompanyMapper mapper;

    public CompanyAccount saveAccount(Integer companyId, Double amount) throws CompanyNotFound {
        Optional<CompanyAccount> existingAccount = this.getAccountByCompanyId(companyId);
        CompanyAccount companyAccount;
        if(existingAccount.isPresent()){
            companyAccount = existingAccount.get();
            companyAccount.setAmount(existingAccount.get().getAmount()+ amount);
        }
        else {
            Company existingCompany = this.companyService.getCompanyById(companyId);
            companyAccount = mapper.toDao(existingCompany, amount);
        }
        return this.companyAccountRepository.save(companyAccount);
    }

    public Optional<CompanyAccount> getAccountByCompanyId(Integer companyId){
        return this.companyAccountRepository.findFirstByCompanyId(companyId);
    }


    @Transactional
    public CompanyAccount donateUserDeposit(Integer companyId, Integer userId, String depositType,
                                         Double donationAmount) throws  CompanyAccountNotFound, InsufficientBalanceException, UserNotFound {

        CompanyAccount account = this.getAccountByCompanyId(companyId)
                .orElseThrow(() -> new CompanyAccountNotFound(companyId));
        if(account.getAmount() < donationAmount) {
            throw new InsufficientBalanceException(account.getAmount());
        }
        User user = this.userService.getUserById(userId).orElseThrow(() -> new UserNotFound(userId));
        UserDeposit userDeposit = new UserDeposit(user, depositType, donationAmount, LocalDate.now());
        userDeposit.setCompanyAccount(account); // facultatif !!!
        this.userDepositService.saveUserDeposit(userDeposit);

        //On met à jour le solde du compte de l'entreprise en déduisant le don effectué
        account.setAmount(account.getAmount() - donationAmount);
        return this.companyAccountRepository.save(account);
    }
}
