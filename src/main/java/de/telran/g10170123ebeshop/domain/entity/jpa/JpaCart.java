package de.telran.g10170123ebeshop.domain.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class JpaCart implements Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private JpaCustomer customer;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<JpaProduct> products;

    public JpaCart() {
    }

    public JpaCart(JpaCustomer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JpaCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(JpaCustomer customer) {
        this.customer = customer;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void setProducts(List<JpaProduct> products) {
        this.products = products;
    }

    @Override
    public void addProduct(Product product) {
        products.add(new JpaProduct(product.getId(), product.getName(), product.getPrice()));
    }

    @Override
    public double getTotalPrice() {
        return products.stream().mapToDouble(x -> x.getPrice()).sum();
    }

    @Override
    public double getAveragePrice() {
        if (products.isEmpty()) {
            return 0;
        }
        return getTotalPrice() / products.size();
    }

    @Override
    public void deleteProduct(int id) {
        products.removeIf(x -> x.getId() == id);
    }

    @Override
    public void clear() {
        products.clear();
    }
}