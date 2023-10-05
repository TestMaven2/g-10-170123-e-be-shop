package de.telran.g10170123ebeshop.repository.jpa;

import de.telran.g10170123ebeshop.domain.entity.jpa.JpaCustomer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<JpaCustomer, Integer> {

    @Transactional
    void deleteByName(String name);
}