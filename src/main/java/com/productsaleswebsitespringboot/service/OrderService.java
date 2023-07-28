package com.productsaleswebsitespringboot.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.productsaleswebsitespringboot.model.Orders;

public interface OrderService {

    public void deleteOrder(Long id);

    public Orders getOrderById(Long id);

    public Orders saveOrder(Orders order);

    public void updateOrder(Orders order);

    public List<Orders> getAllOrder(Sort sort);
}
