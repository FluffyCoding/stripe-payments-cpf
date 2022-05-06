package com.unity.stripe.payments.service.implementation;

import com.unity.stripe.payments.dao.CustomerRepository;
import com.unity.stripe.payments.dao.UserRegistrationRepository;
import com.unity.stripe.payments.entity.User;
import com.unity.stripe.payments.service.UserRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationServiceImple implements UserRegistrationService {

    private final CustomerRepository customerRepository;
    private final UserRegistrationRepository userRepository;

    public UserRegistrationServiceImple(CustomerRepository customerRepository,
                                        UserRegistrationRepository userRepository) {

        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }


}
