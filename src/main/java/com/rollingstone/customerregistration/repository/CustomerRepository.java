package com.rollingstone.customerregistration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rollingstone.customerregistration.model.Customer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Long>
{

    @Query("select s from Customer s where s.userName = :userName")
    Customer findByUserName(@Param("userName") String userName);

}
