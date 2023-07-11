package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Supplier;

public interface SupplierService {

    public void deleteSupplier(Long id);

    public Supplier getSupplierById(Long id);

    public Supplier saveSupplier(Supplier supplier);

    public void updateSupplier(Supplier supplier);

    public List<Supplier> getAllSuppliers(Sort sort);

}
