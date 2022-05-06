package com.unity.stripe.payments.service.implementation;

import com.unity.stripe.payments.dao.CustomerRepository;
import com.unity.stripe.payments.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

   public Customer findCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email).orElse(null);
    }

}
