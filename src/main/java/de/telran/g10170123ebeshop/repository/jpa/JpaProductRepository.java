package de.telran.g10170123ebeshop.repository.jpa;

import de.telran.g10170123ebeshop.domain.entity.jpa.JpaProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaProductRepository extends JpaRepository<JpaProduct, Integer> {

    @Transactional
    void deleteByName(String name);

    @Query(value = "select sum(price) from product;", nativeQuery = true)
    double getTotalPrice();

    @Query(value = "select avg(price) from product;", nativeQuery = true)
    double getAveragePrice();
}