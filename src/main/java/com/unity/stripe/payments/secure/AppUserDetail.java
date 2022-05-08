package com.unity.stripe.payments.secure;

import com.unity.stripe.payments.entity.User;
import com.unity.stripe.payments.entity.UserAuthGroup;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AppUserDetail implements UserDetails {

    private final User user;
    private final List<UserAuthGroup> authGroup;

    public AppUserDetail(User user, List<UserAuthGroup> authGroup) {
        this.user = user;
        this.authGroup = authGroup;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (authGroup == null) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        authGroup.forEach(groups ->
                grantedAuthorities.add(new SimpleGrantedAuthority(groups.getUserRole())));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
