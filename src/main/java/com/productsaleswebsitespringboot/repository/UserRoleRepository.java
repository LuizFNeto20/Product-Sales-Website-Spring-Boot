package com.productsaleswebsitespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productsaleswebsitespringboot.model.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{
    UserRole findByRole(String role);
}
