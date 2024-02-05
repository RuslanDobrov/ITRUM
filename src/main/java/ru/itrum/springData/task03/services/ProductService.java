package ru.itrum.springData.task03.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itrum.springData.task03.models.Product;
import ru.itrum.springData.task03.repositories.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public void saveAll(Set<Product> products) {
        productRepository.saveAll(products);
    }

    Set<Product> findByIdIn(Set<Long> productIds) {
        return productRepository.findByIdIn(productIds);
    }

    public void decreaseProductQuantity(Product product) {
        int remainingQuantity = product.getQuantity() - 1;
        if (remainingQuantity < 0) {
            throw new IllegalArgumentException("Insufficient quantity for product: " + product.getId());
        }
        product.setQuantity(remainingQuantity);
        save(product); // Обновляем продукт в базе данных
    }
}
