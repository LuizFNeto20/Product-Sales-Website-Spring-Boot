package com.productsaleswebsitespringboot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.productsaleswebsitespringboot.model.UserRole;
import com.productsaleswebsitespringboot.model.Users;

public class UserDetail implements UserDetails {

    private Users user;

    public UserDetail(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRole> roles = user.getUserRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : roles) {
            SimpleGrantedAuthority sga = new SimpleGrantedAuthority(role.getRole());
            authorities.add(sga);
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public String getImage() {
        return user.getImage();
    }

    public Long getId() {
        return user.getId();
    }
}
