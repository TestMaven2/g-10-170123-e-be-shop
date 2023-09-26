package de.telran.g10170123ebeshop.repository.mysql;

import de.telran.g10170123ebeshop.domain.entity.common.CommonProduct;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.repository.interfaces.ProductRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static de.telran.g10170123ebeshop.domain.database.MySqlConnector.getConnection;
import static de.telran.g10170123ebeshop.constants.Constants.*;

public class MySqlProductRepository implements ProductRepository {

    @Override
    public List<Product> getAll() {
        try (Connection connection = getConnection()) {

            String query = "select * from product;";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
//                int id = resultSet.getInt(1);
                int id = resultSet.getInt(ID);
                String name = resultSet.getString(NAME);
                double price = resultSet.getDouble(PRICE);
                products.add(new CommonProduct(id, name, price));
            }

            return products;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getById(int id) {
        try (Connection connection = getConnection()) {

            String query = String.format("select * from product where id = %d;", id);
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            resultSet.next();
            String name = resultSet.getString(NAME);
            double price = resultSet.getDouble(PRICE);
            return new CommonProduct(id, name, price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(String name, double price) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = getConnection()) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}