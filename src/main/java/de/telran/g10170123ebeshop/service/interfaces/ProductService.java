package de.telran.g10170123ebeshop.service.interfaces;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(int id);

    void addProduct(Product product);

    void deleteById(int id);

    int getCount();

    double getTotalPrice();

    double getAveragePrice();

    void deleteByName(String name);
}