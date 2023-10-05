package de.telran.g10170123ebeshop.domain.entity.jpa;

import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "product")
public class JpaProduct implements Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @Pattern(regexp = "[A-Z][a-z]{2,49}")
    // Мы хотим, чтобы наименование товара обязательно было с большой буквы,
    // остальные были маленькие, и в названии было бы как минимум три буквы,
    // при этом не буквенные символы не допускаются.
    // Не допускается длина товара свыше 50 букв.
    // Абв - ОК
    // АБВ - Х
    // Аб - Х
    // абв - Х
    // Абв1 - Х
    private String name;

    @Column(name = "price")
    @Min(10)
    @Max(99999)
    private double price;

    public JpaProduct() {
    }

    public JpaProduct(int id, String name, double price) {
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