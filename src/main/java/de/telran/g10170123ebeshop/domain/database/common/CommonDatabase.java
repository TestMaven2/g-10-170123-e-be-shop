package de.telran.g10170123ebeshop.domain.database.common;

import de.telran.g10170123ebeshop.domain.database.interfaces.Database;
import de.telran.g10170123ebeshop.domain.entity.common.CommonCart;
import de.telran.g10170123ebeshop.domain.entity.common.CommonCustomer;
import de.telran.g10170123ebeshop.domain.entity.common.CommonProduct;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommonDatabase implements Database {

    private List<Customer> customers = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private int currentCustomerId;
    private int currentProductId;

    public CommonDatabase() {
        customers.add(new CommonCustomer(1, "Vanya", new CommonCart()));
        customers.add(new CommonCustomer(2, "Petya", new CommonCart()));
        customers.add(new CommonCustomer(3, "Sidor", new CommonCart()));

        products.add(new CommonProduct(1, "Banana", 150));
        products.add(new CommonProduct(2, "Apple", 120));
        products.add(new CommonProduct(3, "Orange", 200));
        products.add(new CommonProduct(4, "Cherry", 250));
        products.add(new CommonProduct(5, "Peach", 220));

        currentCustomerId = 3;
        currentProductId = 5;
    }

    @Override
    public void execute(String query) throws SQLException {
        if (!query.startsWith("Add") && !query.startsWith("Delete")) {
            throw new SQLException();
        }

        String[] parts = query.split(" ");

        if (query.startsWith("Add")) {
            if (parts[2].equals("customer")) {
                customers.add(new CommonCustomer(++currentCustomerId, parts[5], new CommonCart()));
            }
            if (parts[2].equals("product")) {
                products.add(new CommonProduct(++currentProductId, parts[5], Double.parseDouble(parts[8])));
            }
        }

        if (query.startsWith("Delete")) {
            if (parts[1].equals("customer")) {
                customers.removeIf(x -> x.getId() == Integer.parseInt(parts[5]));
            }
            if (parts[1].equals("product")) {
                products.removeIf(x -> x.getId() == Integer.parseInt(parts[5]));
            }
        }
    }

    @Override
    public List<Object> select(String query) throws SQLException {
        if (!query.startsWith("Select")) {
            throw new SQLException();
        }

        String[] parts = query.split(" ");
        List<Object> result = new ArrayList<>();

        if (parts.length == 3) {
            if (parts[2].equals("customers")) {
                result.addAll(customers);
            }

            if (parts[2].equals("products")) {
                result.addAll(products);
            }
        }

        if (parts.length == 6) {
            if (parts[1].equals("customer")) {
                result.add(customers.stream().filter(x -> x.getId() == Integer.parseInt(parts[5])).toList().get(0));
            }

            if (parts[1].equals("product")) {
                result.add(products.stream().filter(x -> x.getId() == Integer.parseInt(parts[5])).toList().get(0));
            }
        }

        return result;
    }
}