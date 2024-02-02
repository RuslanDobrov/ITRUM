package ru.itrum.springMVC.task01.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itrum.springMVC.task01.models.views.Views;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @JsonView(Views.UserDetails.class)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(Views.UserDetails.class)
    @Column(name = "product")
    private String product;

    @JsonView(Views.UserDetails.class)
    @Column(name = "total_amount")
    private double totalAmount;

    @JsonView(Views.UserDetails.class)
    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Order(String product, double totalAmount, String status) {
        this.product = product;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        if (Double.compare(order.totalAmount, totalAmount) != 0) return false;
        if (!Objects.equals(product, order.product)) return false;
        return Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = product != null ? product.hashCode() : 0;
        temp = Double.doubleToLongBits(totalAmount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
