package com.productsaleswebsitespringboot.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.productsaleswebsitespringboot.model.UserRole;
import com.productsaleswebsitespringboot.repository.UserRoleRepository;

@Component
public class UserRoleData implements CommandLineRunner{
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        String[] papeis = { "ADMIN", "USER"};

        for (String roleString : papeis) {
            UserRole userRole = userRoleRepository.findByRole(roleString);
            if (userRole == null) {
                userRole = new UserRole(roleString);
                userRoleRepository.save(userRole);
            }
        }
    }
}
