package com.chodaton.wedoogift.controller;

import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.service.user.UserDepositService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlMergeMode.MergeMode.MERGE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SqlGroup({
        @Sql(scripts = {"/user-company-account-insert.sql"}, executionPhase = BEFORE_TEST_METHOD),
        @Sql(scripts = {"/clean-all.sql"}, executionPhase = AFTER_TEST_METHOD)
})
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
 class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserDepositService userDepositService;

    @Test
    void givenUserAndCompanyAccount_whenDonation_thenReturnStatusOKAndAccountBalance() throws Exception {
        this.mockMvc.perform(post("/company/1/deposit-donation/1")
                        //.contentType(APPLICATION_JSON)
                        .param("depositType", "gift")
                        .param("amount", "120.0")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.amount").value("59880.0"));

        List<UserDeposit> userDepositList = userDepositService.getAllUserDeposit(1);
        Assertions.assertFalse(userDepositList.isEmpty());
        Assertions.assertEquals(120.0, userDepositList.get(0).getAmount());
    }

    @SqlMergeMode(MERGE)
    @Sql(statements = "INSERT INTO user_deposit(id,user_code, deposit_type, amount, deposit_date) VALUES (2,'T01','gift',450.0,'2022-12-14')")
    @Test
    void givenNotExpiredGiftDeposit_whenGetBalance_thenReturnNonNullValue() throws Exception {
        this.mockMvc.perform(get("/user/deposit/2/balance")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("450.0"));
    }

    @SqlMergeMode(MERGE)
    @Sql(statements = "INSERT INTO user_deposit(id,user_code, deposit_type, amount, deposit_date) VALUES (3,'T01','gift',300.0,'2021-06-25')")
    @Test
    void givenExpiredGiftDeposit_whenGetBalance_thenReturnNullValue() throws Exception {
        this.mockMvc.perform(get("/user/deposit/3/balance")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("0.0"));
    }

    @SqlMergeMode(MERGE)
    @Sql(statements = "INSERT INTO user_deposit(id,user_code, deposit_type, amount, deposit_date) VALUES (4,'T01','meal',2300.0,'2022-01-01')")
    @Test
    void givenNotExpiredMealDeposit_whenGetBalance_thenReturnNonNullValue() throws Exception {
        this.mockMvc.perform(get("/user/deposit/4/balance")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("2300.0"));
    }

    @SqlMergeMode(MERGE)
    @Sql(statements = "INSERT INTO user_deposit(id,user_code, deposit_type, amount, deposit_date) VALUES (5,'T01','meal',300.0,'2021-12-14')")
    @Test
    void givenExpiredMealDeposit_whenGetBalance_thenReturnNullValue() throws Exception {
        this.mockMvc.perform(get("/user/deposit/5/balance")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("0.0"));
    }

}
