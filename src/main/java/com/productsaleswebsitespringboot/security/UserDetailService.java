package com.productsaleswebsitespringboot.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Users;
import com.productsaleswebsitespringboot.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findByLogin(username);

        if (user != null) {
            UserDetail userDetail = new UserDetail(user);
            return userDetail;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
