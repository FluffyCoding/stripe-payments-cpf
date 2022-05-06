package com.unity.stripe.payments.dao;

import com.unity.stripe.payments.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegistrationRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
}
