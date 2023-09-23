package de.telran.g10170123ebeshop.service.common;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.repository.interfaces.ProductRepository;
import de.telran.g10170123ebeshop.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommonProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.getAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.add(product.getName(), product.getPrice());
    }

    @Override
    public void deleteById(int id) {
        productRepository.delete(id);
    }

    @Override
    public int getCount() {
        return productRepository.getAll().size();
    }

    @Override
    public double getTotalPrice() {
        return productRepository.getAll().stream().mapToDouble(x -> x.getPrice()).sum();
    }

    @Override
    public double getAveragePrice() {
        int count = getCount();
        if (count == 0) {
            return 0;
        }
        return getTotalPrice() / count;
    }

    @Override
    public void deleteByName(String name) {
        Product productToDelete = getAll().stream().filter(x -> name.equals(x.getName())).findFirst().orElse(null);
        if (productToDelete != null) {
            deleteById(productToDelete.getId());
        }
    }
}