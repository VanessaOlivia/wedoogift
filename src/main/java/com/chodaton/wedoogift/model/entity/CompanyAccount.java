package com.chodaton.wedoogift.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "company_account")
public class CompanyAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_code", referencedColumnName = "code")
    private Company companyCode;

    @Column(name = "deposit_type", length = 10)
    private String depositType;

    @Column(name = "amount", length = 10)
    private String amount;

}