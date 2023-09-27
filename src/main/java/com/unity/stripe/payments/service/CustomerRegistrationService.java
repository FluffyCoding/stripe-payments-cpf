package com.unity.stripe.payments.service;

import com.unity.stripe.payments.entity.Customer;

public interface CustomerRegistrationService {

    void saveCustomer(Customer customer);

    Boolean isEmailExist(String email);


}
