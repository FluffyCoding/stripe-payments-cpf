package com.unity.stripe.payments.secure;

import com.unity.stripe.payments.dao.AuthGroupRepository;
import com.unity.stripe.payments.dao.UserRegistrationRepository;
import com.unity.stripe.payments.entity.User;
import com.unity.stripe.payments.entity.UserAuthGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserManagementService {

    @Autowired
    UserRegistrationRepository userRepository;
    @Autowired
    AuthGroupRepository authGroupRepository;
//    @Autowired
//    BCryptPasswordEncoder passwordEncoder;

    Logger LOGGER = LoggerFactory.getLogger(getClass());

    public void saveUserDetail(User user, List<UserAuthGroup> authGroups) {
        user.setPassword(user.getPassword());
        user.setAuthGroups(authGroups);
        userRepository.save(user);
        for (UserAuthGroup authGroup : authGroups) {
            authGroup.setUser(user);
            authGroupRepository.save(authGroup);
        }

    }

    public User saveNewUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User findUserByUserEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserName(String email) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().contains(email)).findFirst().orElse(null);

    }


    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            LOGGER.error("Error while login ", e);
        }
    }


}
