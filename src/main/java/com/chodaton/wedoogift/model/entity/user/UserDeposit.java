package com.chodaton.wedoogift.model.entity.user;

import com.chodaton.wedoogift.model.entity.company.CompanyAccount;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_deposit")
public class UserDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_code", referencedColumnName = "user_code")
    @NonNull
    private UserIdentity user;

    @Column(name = "deposit_type", length = 10)
    @NonNull
    private String depositType;

    @Column(name = "amount")
    @NonNull
    private Double amount;

    @Column(name = "deposit_date")
    @NonNull
    private LocalDate depositDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_account_id")
    private CompanyAccount companyAccount;

}