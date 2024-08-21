package org.example.service;

import org.example.dto.OrdersDto;
import org.example.entity.Orders;

import java.util.List;

public interface OrdersService {
    Orders updateOrders(Long id, OrdersDto ordersDto);
    Boolean deleteOrders(Long id);
    Boolean addOrder(OrdersDto orderDto);
    List<OrdersDto> getAllOrdersByCustomer(String customerName);
}
