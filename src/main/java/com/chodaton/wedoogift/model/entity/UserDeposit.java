package com.chodaton.wedoogift.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "user_deposit")
public class UserDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_code", referencedColumnName = "code")
    private User userCode;

    @Column(name = "deposit_type", length = 10)
    private String depositType;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "deposit_date")
    private LocalDate depositDate;


}