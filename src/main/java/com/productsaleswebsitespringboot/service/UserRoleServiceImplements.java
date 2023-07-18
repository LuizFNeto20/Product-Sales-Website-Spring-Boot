package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.UserRole;
import com.productsaleswebsitespringboot.repository.UserRoleRepository;

@Service
public class UserRoleServiceImplements implements UserRoleService{

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole getRoleById(Long id) {
        Optional<UserRole> role = userRoleRepository.findById(id);
        if (role.isPresent()) {
            return role.get();
        } else {
            throw new IllegalArgumentException(id + " não encontrado");
        }
    }

    @Override
    public UserRole getRole(String role) {
        UserRole ur = userRoleRepository.findByRole(role);
        return ur;
    }

    @Override
    public List<UserRole> getAllRoles() {
        List<UserRole> roles = userRoleRepository.findAll();
        return roles;
    }
    
}
