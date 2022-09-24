package com.chodaton.wedoogift.controller;

import com.chodaton.wedoogift.model.entity.user.UserDeposit;
import com.chodaton.wedoogift.service.user.UserDepositService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(properties = "spring.main.lazy-initialization=true")
 class CompanyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserDepositService userDepositService;

    @Sql(value = "/user-company-account-insert.sql", executionPhase = BEFORE_TEST_METHOD)
    @Test
    void givenUserAndCompanyAccount_whenDonation_thenReturnStatusOKAndAccountBalance() throws Exception {
        this.mockMvc.perform(post("/company/1/deposit-donation/1")
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
}
