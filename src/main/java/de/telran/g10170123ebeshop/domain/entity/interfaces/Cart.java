package de.telran.g10170123ebeshop.domain.entity.interfaces;

import java.util.List;

public interface Cart {

    List<Product> getProducts();

    void addProduct(Product product);

    double getTotalPrice();

    double getAveragePrice();

    void deleteProduct(int id);

    void clear();
}