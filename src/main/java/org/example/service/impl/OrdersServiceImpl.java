package org.example.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.OrdersDto;
import org.example.entity.Orders;
import org.example.repository.OrderRepository;
import org.example.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public Boolean addOrder(OrdersDto orderDto) {
        Orders orders=objectMapper.convertValue(orderDto, Orders.class);
        Orders savedOrders=orderRepository.save(orders);
        return savedOrders.getId() != null;
    }

    @Override
    public Orders updateOrders(Long id, OrdersDto ordersDto) {
        return null;
    }

    @Override
    public Boolean deleteOrders(Long id) {
        return null;
    }
    @Override
    public List<OrdersDto> getAllOrdersByCustomer(String customerName) {
        return null;
    }
}
