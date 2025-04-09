package com.md.bank.accounts.mapper;

import com.md.bank.accounts.dto.AccountsDTO;
import com.md.bank.accounts.entity.AccountsEntity;

public class AccountsMapper {
    public static AccountsDTO mapToAccountsDto(AccountsEntity accountsEntity, AccountsDTO accountsDTO){
        accountsDTO.setAccountNumber(accountsEntity.getBankAccountNumber());
        accountsDTO.setAccountType(accountsEntity.getAccountType());
        accountsDTO.setBranchAddress(accountsEntity.getBranchAddress());
        return accountsDTO;
    }
    public static AccountsEntity mapToAccountsEntity(AccountsDTO accountsDTO, AccountsEntity accountsEntity){
        accountsEntity.setBankAccountNumber(accountsDTO.getAccountNumber());
        accountsEntity.setAccountType(accountsDTO.getAccountType());
        accountsEntity.setBranchAddress(accountsDTO.getBranchAddress());
        return accountsEntity;
    }

}
