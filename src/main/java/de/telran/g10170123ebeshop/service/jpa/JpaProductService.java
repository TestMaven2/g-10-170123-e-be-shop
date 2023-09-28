package de.telran.g10170123ebeshop.service.jpa;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.domain.entity.jpa.JpaProduct;
import de.telran.g10170123ebeshop.repository.jpa.JpaProductRepository;
import de.telran.g10170123ebeshop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaProductService implements ProductService {

    @Autowired
    private JpaProductRepository repository;

    @Override
    public List<Product> getAll() {
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Product getById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void addProduct(Product product) {
        repository.save(new JpaProduct(0, product.getName(), product.getPrice()));
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public int getCount() {
        return (int) repository.count();
    }

    @Override
    public double getTotalPrice() {
        return repository.getTotalPrice();
    }

    @Override
    public double getAveragePrice() {
        return repository.getAveragePrice();
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
}