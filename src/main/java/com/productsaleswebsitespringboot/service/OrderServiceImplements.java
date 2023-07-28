package com.productsaleswebsitespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.productsaleswebsitespringboot.model.Orders;
import com.productsaleswebsitespringboot.repository.OrderRepository;

@Service
public class OrderServiceImplements implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void deleteOrder(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public Orders getOrderById(Long id) {
        Optional<Orders> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new IllegalArgumentException(id + " não encontrado");
        }
    }

    @Override
    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public void updateOrder(Orders order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
    }

    @Override
    public List<Orders> getAllOrder(Sort sort) {
        List<Orders> orderList = orderRepository.findAll(sort);
        return orderList;
    }

}
