package de.telran.g10170123ebeshop.domain.entity.common;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;

public class CommonProduct implements Product {

    private int id;

    private String name;

    private double price;

    public CommonProduct(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}