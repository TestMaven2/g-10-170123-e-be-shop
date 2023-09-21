package de.telran.g10170123ebeshop.domain.entity.common;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;

import java.util.ArrayList;
import java.util.List;

public class CommonCart implements Cart {

    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).reduce(Double::sum).orElse(0);
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