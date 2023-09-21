package de.telran.g10170123ebeshop.repository.interfaces;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAll();

    Customer getById(int id);

    void add(String name);

    void delete(int id);

    void addToCartById(int customerId, int productId);

    void deleteFromCart(int customerId, int productId);

    void clearCart(int customerId);
}