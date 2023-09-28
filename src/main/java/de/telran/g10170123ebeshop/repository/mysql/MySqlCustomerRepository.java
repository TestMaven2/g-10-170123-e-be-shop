package de.telran.g10170123ebeshop.repository.mysql;

import de.telran.g10170123ebeshop.domain.entity.common.CommonCart;
import de.telran.g10170123ebeshop.domain.entity.common.CommonCustomer;
import de.telran.g10170123ebeshop.domain.entity.common.CommonProduct;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Cart;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.repository.interfaces.CustomerRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.telran.g10170123ebeshop.domain.database.MySqlConnector.getConnection;

public class MySqlCustomerRepository implements CustomerRepository {

    @Override
    public List<Customer> getAll() {
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM customer as c left join customer_to_product as cp on c.customer_id = cp.customer_id left join product as p on cp.product_id = p.product_id;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Map<Integer, Customer> map = new HashMap<>();

            while (resultSet.next()) {
                int customerId = resultSet.getInt(1);

                int productId = resultSet.getInt(6);
                String productName = resultSet.getString(7);
                double productPrice = resultSet.getDouble(8);
                Product product = new CommonProduct(productId, productName, productPrice);

                if (map.containsKey(customerId)) {
                    map.get(customerId).getCart().addProduct(product);
                } else {
                    String customerName = resultSet.getString(2);
                    Cart cart = new CommonCart();

                    if (productName != null) {
                        cart.addProduct(product);
                    }

                    Customer customer = new CommonCustomer(customerId, customerName, cart);
                    map.put(customerId, customer);
                }
            }

            return map.values().stream().toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getById(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("SELECT * FROM customer as c left join customer_to_product as cp on c.customer_id = cp.customer_id left join product as p on cp.product_id = p.product_id where c.customer_id = %d;", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            Customer customer = null;

            while (resultSet.next()) {
                int productId = resultSet.getInt(6);
                String productName = resultSet.getString(7);
                double productPrice = resultSet.getDouble(8);
                Product product = new CommonProduct(productId, productName, productPrice);

                if (customer == null) {
                    String customerName = resultSet.getString(2);
                    customer = new CommonCustomer(id, customerName, new CommonCart());
                }

                if (productName != null) {
                    customer.getCart().addProduct(product);
                }
            }

            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String name) {
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `customer` (`name`) VALUES ('%s');", name);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `customer` WHERE (`customer_id` = '%d');", id);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToCartById(int customerId, int productId) {
        try (Connection connection = getConnection()) {
            String query = String.format("INSERT INTO `customer_to_product` (`customer_id`, `product_id`) VALUES ('%d', '%d');", customerId, productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFromCart(int customerId, int productId) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `customer_to_product` WHERE (`customer_id` = '%d' and `product_id` = '%d');", customerId, productId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCart(int customerId) {
        try (Connection connection = getConnection()) {
            String query = String.format("DELETE FROM `customer_to_product` WHERE (`customer_id` = '%d');", customerId);
            connection.createStatement().execute(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}