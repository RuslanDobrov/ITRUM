package ru.itrum.springMVC.task01.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itrum.springMVC.task01.models.views.Views;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @JsonView(Views.UserSummary.class)
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonView(Views.UserSummary.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.UserSummary.class)
    @Email(message = "Invalid email address")
    @Column(name = "email")
    private String email;

    @JsonView(Views.UserDetails.class)
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;

        if (!Objects.equals(name, user.name)) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
