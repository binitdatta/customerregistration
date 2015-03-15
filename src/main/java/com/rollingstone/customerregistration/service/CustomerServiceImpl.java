package com.rollingstone.customerregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rollingstone.customerregistration.model.Customer;
import com.rollingstone.customerregistration.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService
{

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer save(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public boolean findByLogin(String userName, String password)
    {
        Customer cust = customerRepository.findByUserName(userName);

        if (cust != null && cust.getPassword().equals(password))
        {
            return true;
        }

        return false;
    }

    public boolean findByUserName(String userName)
    {
        Customer stud = customerRepository.findByUserName(userName);

        if (stud != null)
        {
            return true;
        }

        return false;
    }

}
