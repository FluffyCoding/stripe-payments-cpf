package com.unity.stripe.payments.dao;

import com.unity.stripe.payments.entity.UserAuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthGroupRepository extends JpaRepository<UserAuthGroup, Long> {

    List<UserAuthGroup> findByEmail(String email);


}
