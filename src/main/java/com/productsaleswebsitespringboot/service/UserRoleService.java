package com.productsaleswebsitespringboot.service;

import java.util.List;

import com.productsaleswebsitespringboot.model.UserRole;

public interface UserRoleService {
    
    public UserRole getRoleById(Long id);

    public UserRole getRole(String role);

    public List<UserRole> getAllRoles();

}
