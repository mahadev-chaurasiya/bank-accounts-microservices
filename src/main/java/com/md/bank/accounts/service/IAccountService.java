package com.md.bank.accounts.service;


import com.md.bank.accounts.dto.CustomerDTO;

public interface IAccountService {
    /**
     *
     *@param customerDTO - CustomerDTO Object
    */
    void createAccount(CustomerDTO customerDTO);

    /**
     *
     *@param mobileNumber - Input Mobile Number
     *@return Accounts Details based on a given Mobile number
     */
    CustomerDTO fetchAccount(String mobileNumber);

    /**
     *
     *@param customerDTO - CustomerDTO object
     *@return boolean indicating if the update of account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    /**
     *
     *@param mobileNumber - Input Mobile Number
     *@return Accounts Details based on a given Mobile number
     */
    boolean deleteAccount(String mobileNumber);
}
