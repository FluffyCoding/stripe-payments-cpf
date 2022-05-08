package com.unity.stripe.payments.secure;

import com.unity.stripe.payments.dao.AuthGroupRepository;
import com.unity.stripe.payments.dao.UserRegistrationRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserRegistrationRepository userRepository;
    private final AuthGroupRepository authGroupRepository;

    public AppUserDetailService(UserRegistrationRepository userRepository,
                                AuthGroupRepository authGroupRepository) {
        this.userRepository = userRepository;
        this.authGroupRepository = authGroupRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("Cannot Find Username " + email);
        }
        var authGroup = authGroupRepository.findByEmail(email);
        return new AppUserDetail(user, authGroup);
    }
}
