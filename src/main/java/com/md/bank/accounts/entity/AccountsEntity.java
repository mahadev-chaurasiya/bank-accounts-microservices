package com.md.bank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "ACCOUNTS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AccountsEntity extends BaseEntity{
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Id
    @Column(name = "account_number")
    private Long bankAccountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;
}
