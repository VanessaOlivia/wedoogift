package com.chodaton.wedoogift.model.entity.company;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "company_code", length = 10)
    @NonNull
    private String companyCode;
    @Column(name = "company_name")
    @NonNull
    private String companyName;
}