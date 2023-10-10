package de.telran.g10170123ebeshop.service.jpa;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.domain.entity.jpa.JpaCart;
import de.telran.g10170123ebeshop.domain.entity.jpa.JpaCustomer;
import de.telran.g10170123ebeshop.repository.jpa.JpaCartRepository;
import de.telran.g10170123ebeshop.repository.jpa.JpaCustomerRepository;
import de.telran.g10170123ebeshop.repository.jpa.JpaProductRepository;
import de.telran.g10170123ebeshop.service.interfaces.CustomerService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaCustomerService implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpaCustomerService.class);

    @Autowired
    private JpaCustomerRepository repository;

    @Autowired
    private JpaCartRepository cartRepository;

    @Autowired
    private JpaProductRepository productRepository;

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Customer getById(int id) {
        LOGGER.info("Запрошен покупатель с идентификатором {}.", id);
        LOGGER.warn("Запрошен покупатель с идентификатором {}.", id);
        LOGGER.error("Запрошен покупатель с идентификатором {}.", id);

        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(Customer customer) {
        JpaCustomer savedCustomer = repository.save(new JpaCustomer(0, customer.getName()));
        cartRepository.save(new JpaCart(savedCustomer));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    @Override
    public int getCount() {
        return (int) repository.count();
    }

    @Override
    public double getTotalPriceById(int id) {
        return getById(id).getCart().getTotalPrice();
    }

    @Override
    public double getAveragePriceById(int id) {
        Cart cart = getById(id).getCart();
        return cart.getTotalPrice() / cart.getProducts().size();
    }

    @Transactional
    @Override
    public void addToCartById(int customerId, int productId) {
        Customer customer = repository.findById(customerId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);
        Cart cart = customer.getCart();
        cart.addProduct(product);
    }

    @Transactional
    @Override
    public void deleteFromCart(int customerId, int productId) {
        getById(customerId).getCart().deleteProduct(productId);
    }

    @Transactional
    @Override
    public void clearCart(int customerId) {
        getById(customerId).getCart().clear();
    }
}