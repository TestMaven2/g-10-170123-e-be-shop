package de.telran.g10170123ebeshop.repository.jpa;

import de.telran.g10170123ebeshop.domain.entity.jpa.JpaCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCartRepository extends JpaRepository<JpaCart, Integer> {
}