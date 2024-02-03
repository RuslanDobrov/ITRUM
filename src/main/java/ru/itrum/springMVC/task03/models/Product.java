package ru.itrum.springMVC.task03.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name is should be not empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Description is mandatory")
    @NotEmpty(message = "Description is should be not empty")
    @Size(min = 2, message = "Description must be between 2 and 255 characters long")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Price is mandatory")
    @PositiveOrZero(message = "Price must be greater then 0")
    @Column(name = "price")
    private double price;

    @NotNull(message = "Quantity in stock is mandatory")
    @PositiveOrZero(message = "Quantity in stock must be greater then 0")
    @Column(name = "quantity_in_stock")
    private int quantityInStock;

    public Product(String name, String description, double price, int quantityInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }
}
