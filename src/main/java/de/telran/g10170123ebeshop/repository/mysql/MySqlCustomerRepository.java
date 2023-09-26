package de.telran.g10170123ebeshop.repository.mysql;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.repository.interfaces.CustomerRepository;

import java.util.List;

public class MySqlCustomerRepository implements CustomerRepository {

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer getById(int id) {
        return null;
    }

    @Override
    public void add(String name) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void addToCartById(int customerId, int productId) {

    }

    @Override
    public void deleteFromCart(int customerId, int productId) {

    }

    @Override
    public void clearCart(int customerId) {

    }
}