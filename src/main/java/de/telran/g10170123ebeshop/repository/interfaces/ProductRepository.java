package de.telran.g10170123ebeshop.repository.interfaces;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAll();

    Product getById(int id);

    void add(String name, double price);

    void delete(int id);
}