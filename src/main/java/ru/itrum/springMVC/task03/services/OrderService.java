package ru.itrum.springMVC.task03.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itrum.springMVC.task03.models.Order;
import ru.itrum.springMVC.task03.repositories.OrderRepository;

import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId);
    }
}
