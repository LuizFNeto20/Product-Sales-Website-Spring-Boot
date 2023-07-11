package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Supplier;
import com.productsaleswebsitespringboot.repository.SupplierRepository;

@Service
public class SupplierServiceImplements implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepository.delete(supplier);
    }

    @Override
    public Supplier getSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return supplier.get();
        } else {
            throw new IllegalArgumentException(id + " não encontrado");
        }
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers(Sort sort) {
        List<Supplier> supplierList = supplierRepository.findAll(sort);
        return supplierList;
    }

}
