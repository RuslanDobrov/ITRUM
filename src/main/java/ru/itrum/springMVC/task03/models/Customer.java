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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @NotNull(message = "Name is mandatory")
    @NotEmpty(message = "Name is should be not empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters long")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name is mandatory")
    @NotEmpty(message = "Last name is should be not empty")
    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters long")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Last name is mandatory")
    @NotEmpty(message = "Last name is should be not empty")
    @Email(message = "Email must be valid")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Contact number is mandatory")
    @NotEmpty(message = "Contact number is should be not empty")
    @Pattern(regexp = "^\\+\\d{11}$", message = "Contact number must starts with '+' and contains 11 numbers")
    @Column(name = "contact_number")
    private String contactNumber;

    public Customer(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }
}
