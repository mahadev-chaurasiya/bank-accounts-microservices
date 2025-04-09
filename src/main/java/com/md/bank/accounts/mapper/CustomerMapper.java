package com.md.bank.accounts.mapper;

import com.md.bank.accounts.dto.CustomerDTO;
import com.md.bank.accounts.entity.CustomerEntity;

public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(CustomerEntity customerEntity, CustomerDTO customerDTO){
        customerDTO.setName(customerEntity.getName());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setMobileNumber(customerEntity.getMobileNumber());
        return customerDTO;
    }

    public static CustomerEntity mapToCustomerEntity(CustomerDTO customerDTO, CustomerEntity customerEntity){
        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setMobileNumber(customerDTO.getMobileNumber());
        return customerEntity;
    }
}
