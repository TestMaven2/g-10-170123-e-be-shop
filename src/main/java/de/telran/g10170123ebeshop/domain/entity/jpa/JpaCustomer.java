package de.telran.g10170123ebeshop.domain.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class JpaCustomer implements Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @OneToOne(mappedBy = "customer")
    private JpaCart cart;

    public JpaCustomer() {
    }

    public JpaCustomer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public JpaCart getCart() {
        return cart;
    }

    public void setCart(JpaCart cart) {
        this.cart = cart;
    }
}