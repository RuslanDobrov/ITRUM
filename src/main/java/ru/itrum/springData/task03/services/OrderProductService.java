package ru.itrum.springData.task03.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import ru.itrum.springData.task03.models.Customer;
import ru.itrum.springData.task03.models.Order;
import ru.itrum.springData.task03.models.Product;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class OrderProductService {

    private final OrderService orderService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final TransactionTemplate transactionTemplate;

    public OrderProductService(OrderService orderService,
                               ProductService productService,
                               CustomerService customerService,
                               TransactionTemplate transactionTemplate) {
        this.orderService = orderService;
        this.productService = productService;
        this.customerService = customerService;
        this.transactionTemplate = transactionTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order placeOrder(Long customerId, Set<Long> productIds) {
        return transactionTemplate.execute(status -> {
            try {
                Customer customer = customerService.findById(customerId)
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

                Set<Product> products = productService.findByIdIn(productIds);
                BigDecimal totalAmount = calculateTotalAmount(products);

                if (customer.getBalance().compareTo(totalAmount) < 0) {
                    throw new IllegalArgumentException("Insufficient balance");
                }

                Order order = new Order();
                order.setCustomer(customer);
                order.setProducts(products);
                order.setTotalAmount(totalAmount);

                customer.setBalance(customer.getBalance().subtract(totalAmount));

                for (Product product : products) {
                    productService.decreaseProductQuantity(product);
                }

                order = orderService.save(order);
                customerService.save(customer);
                productService.saveAll(products);

                return order;
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e;
            }
        });
    }

    private BigDecimal calculateTotalAmount(Set<Product> products) {
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (Product product : products) {
            totalAmount = totalAmount.add(product.getPrice());
        }

        return totalAmount;
    }
}
