package de.telran.g10170123ebeshop.controller;

import de.telran.g10170123ebeshop.domain.entity.common.CommonCustomer;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Customer;
import de.telran.g10170123ebeshop.exception.exceptions.CustomerIdDoesNotExistException;
import de.telran.g10170123ebeshop.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер покупателей.
 * Принимает входящие http-запросы
 * для получения, добавления и удаления покупателей.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    /**
     * Сервис покупателей.
     * Содержит бизнес-логику, относящуюся к покупателям.
     */
    @Autowired
    private CustomerService service;

    /**
     * Получение всех покупателей.
     *
     * @return список всех покупателей, хранящихся в БД.
     */
    @GetMapping
    public List<Customer> getAll() {
        return service.getAll();
    }

    /**
     * Получение покупателя по идентификатору.
     *
     * @param id    идентификатор покупателя.
     * @return      покупатель, имеющий переданный идентификатор.
     */
    @GetMapping("/{id}")
    public Customer getById(@PathVariable int id) {
        Customer customer = service.getById(id);
        if (customer == null) {
            throw new CustomerIdDoesNotExistException("Customer doesn`t exist!");
        }
        return customer;
    }

    /**
     * Добавление покупателя.
     *
     * @param customer  объект покупателя, содержащийся в теле POST-запроса.
     * @return          объект сохраняемого покупателя в случае успешного сохранения.
     */
    @PostMapping
    public Customer add(@RequestBody CommonCustomer customer) {
        service.add(customer);
        return customer;
    }

    /**
     * Удаление покупателя.
     *
     * @param id идентификатор удаляемого из БД покупателя.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    /**
     * Удаление покупателя.
     *
     * @param name имя удаляемого из БД покупателя.
     */
    @DeleteMapping("/name/{name}")
    public void delete(@PathVariable String name) {
        service.deleteByName(name);
    }

    /**
     * Получение количества покупателей.
     *
     * @return количество покупателей, содержащихся в БД.
     */
    @GetMapping("/count")
    public int getCount() {
        return service.getCount();
    }

    /**
     * Получение стоимости продуктов конкретного покупателя.
     *
     * @param id    идентификатор покупателя.
     * @return      стоимость всех продуктов в корзине покупателя с переданным идентификатором.
     */
    @GetMapping("/total/{id}")
    public double getTotalPrice(@PathVariable int id) {
        return service.getTotalPriceById(id);
    }

    /**
     * Получение средней стоимости продукта конкретного покупателя.
     *
     * @param id    идентификатор покупателя.
     * @return      средняя стоимость продукта в корзине покупателя с переданным идентификатором.
     */
    @GetMapping("/average/{id}")
    public double getAverage(@PathVariable int id) {
        return service.getAveragePriceById(id);
    }

    /**
     * Добавление продукта в корзину.
     *
     * @param customerId    идентификатор покупателя, которому добавляется продукт.
     * @param productId     идентификатор добавляемого продукта.
     */
    @GetMapping("/{customerId}/{productId}")
    public void addToCart(@PathVariable int customerId, @PathVariable int productId) {
        service.addToCartById(customerId, productId);
    }

    /**
     * Удаление продукта из корзины.
     *
     * @param customerId    идентификатор покупателя, из корзины которого удаляется продукт.
     * @param productId     идентификатор удаляемого продукта.
     */
    @DeleteMapping("/{customerId}/{productId}")
    public void deleteFromCart(@PathVariable int customerId, @PathVariable int productId) {
        service.deleteFromCart(customerId, productId);
    }

    /**
     * Очистка корзины.
     *
     * @param customerId идентификатор покупателя, у которого очищается корзина.
     */
    @DeleteMapping("/clear/{customerId}")
    public void clearCart(@PathVariable int customerId) {
        service.clearCart(customerId);
    }
}