package ru.itrum.springData.task03.services;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import ru.itrum.springData.task03.models.Customer;
import ru.itrum.springData.task03.models.Order;
import ru.itrum.springData.task03.models.Product;
import ru.itrum.springData.task03.repositories.CustomerRepository;
import ru.itrum.springData.task03.repositories.OrderRepository;
import ru.itrum.springData.task03.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class OrderProductService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final TransactionTemplate transactionTemplate;

    public OrderProductService(OrderRepository orderRepository,
                               ProductRepository productRepository,
                               CustomerRepository customerRepository,
                               TransactionTemplate transactionTemplate) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.transactionTemplate = transactionTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Order placeOrder(Long customerId, Set<Long> productIds) {
        return transactionTemplate.execute(status -> {
            try {
                Customer customer = customerRepository.findById(customerId)
                        .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

                Set<Product> products = productRepository.findByIdIn(productIds);
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
                    int remainingQuantity = product.getQuantity() - 1;
                    if (remainingQuantity < 0) {
                        throw new IllegalArgumentException("Insufficient quantity for product: " + product.getId());
                    }
                    product.setQuantity(remainingQuantity);
                }

                order = orderRepository.save(order);
                customerRepository.save(customer);
                productRepository.saveAll(products);

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
