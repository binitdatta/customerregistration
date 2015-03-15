package com.rollingstone.customerregistration.service;

import com.rollingstone.customerregistration.model.Customer;

public interface CustomerService
{
    Customer save(Customer customer);

    boolean findByLogin(String userName, String password);

    boolean findByUserName(String userName);
}
