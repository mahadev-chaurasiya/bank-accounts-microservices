package com.md.bank.accounts.service.impl;

import com.md.bank.accounts.Constant.ConstantUtils;
import com.md.bank.accounts.dto.AccountsDTO;
import com.md.bank.accounts.dto.CustomerDTO;
import com.md.bank.accounts.entity.AccountsEntity;
import com.md.bank.accounts.entity.CustomerEntity;
import com.md.bank.accounts.exception.CustomerAlreadyExistsException;
import com.md.bank.accounts.exception.ResourceNotFoundException;
import com.md.bank.accounts.mapper.AccountsMapper;
import com.md.bank.accounts.mapper.CustomerMapper;
import com.md.bank.accounts.repository.AccountRepository;
import com.md.bank.accounts.repository.CustomerRepository;
import com.md.bank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     *
     *@param customerDTO - CustomerDTO Object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = CustomerMapper.mapToCustomerEntity(customerDTO, new CustomerEntity());
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given Mobile Number");
        }
        CustomerEntity entity = customerRepository.save(customerEntity);
        accountRepository.save(createNewAccount(entity));
    }


    private AccountsEntity createNewAccount(CustomerEntity customerEntity){
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setCustomerId(customerEntity.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        accountsEntity.setBankAccountNumber(randomAccountNumber);
        accountsEntity.setAccountType(ConstantUtils.SAVING);
        accountsEntity.setBranchAddress(ConstantUtils.ADDRESS);
        return accountsEntity;
    }

    @Override
    public CustomerDTO fetchAccount(String mobileNumber) {
        CustomerEntity customerEntity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
        );
        AccountsEntity accountsEntity = accountRepository.findByCustomerId(customerEntity.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account","customerId", mobileNumber)
        );
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customerEntity, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDto(accountsEntity,new AccountsDTO()));
        return customerDTO;
    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if (accountsDTO !=null){
            AccountsEntity accountsEntity = accountRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    ()-> new ResourceNotFoundException("Account","accountNumber", accountsDTO.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccountsEntity(accountsDTO, accountsEntity);
            accountsEntity = accountRepository.save(accountsEntity);
            Long customerId = accountsEntity.getCustomerId();
            CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(
                    ()-> new ResourceNotFoundException("Customer","customerId", customerId.toString())
            );
            CustomerMapper.mapToCustomerEntity(customerDTO, customerEntity);
            customerRepository.save(customerEntity);
            isUpdated = true;

        }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        CustomerEntity customerEntity = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customerEntity.getCustomerId());
        customerRepository.deleteById(customerEntity.getCustomerId());
        return true;
    }

}
