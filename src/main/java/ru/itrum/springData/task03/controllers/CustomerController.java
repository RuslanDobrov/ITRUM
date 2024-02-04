package ru.itrum.springData.task03.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itrum.springData.task03.models.Customer;
import ru.itrum.springData.task03.models.Order;
import ru.itrum.springData.task03.services.CustomerService;
import ru.itrum.springData.task03.services.OrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(CustomerService customerService, OrderService orderService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAll() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        return optionalCustomer.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.save(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer existingCustomer = optionalCustomer.get();
            modelMapper.map(updatedCustomer, existingCustomer);
            existingCustomer.setId(id);
            return new ResponseEntity<>(customerService.save(existingCustomer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            customerService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomer(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            List<Order> orders = orderService.getOrdersByCustomer(id);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getCustomerBalance(@PathVariable Long id) {
        Optional<Customer> optionalCustomer = customerService.findById(id);
        if (optionalCustomer.isPresent()) {
            return new ResponseEntity<>(optionalCustomer.get().getBalance(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
