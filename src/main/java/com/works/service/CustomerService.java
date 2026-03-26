package com.works.service;

import com.works.entity.Customer;
import com.works.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerRepository customerRepository;

    public Customer register(Customer customer){
        return customerRepository.save(customer);
    }


}
