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
@Table(name = "user_gift")
public class UserGift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_code", referencedColumnName = "code")
    private User userCode;

    @Column(name = "gift_type", length = 10)
    private String giftType;

    @Column(name = "amount", length = 10)
    private String amount;

    @Column(name = "gift_date")
    private LocalDate giftDate;


}