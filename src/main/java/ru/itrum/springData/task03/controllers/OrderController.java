package ru.itrum.springData.task03.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itrum.springData.task03.models.Order;
import ru.itrum.springData.task03.models.Product;
import ru.itrum.springData.task03.services.OrderProductService;
import ru.itrum.springData.task03.services.OrderService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderProductService orderProductService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderProductService orderProductService, OrderService orderService, ModelMapper modelMapper) {
        this.orderProductService = orderProductService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getAll() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        return optionalOrder.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Order> create(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order updatedOrder) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            modelMapper.map(updatedOrder, existingOrder);
            existingOrder.setId(id);
            return new ResponseEntity<>(orderService.save(existingOrder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (optionalOrder.isPresent()) {
            orderService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderProductService.placeOrder(order.getCustomer().getId(),
                    order.getProducts().stream().map(Product::getId).collect(Collectors.toSet()));
            return new ResponseEntity<>(createdOrder, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
