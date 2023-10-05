package de.telran.g10170123ebeshop.controller;

import de.telran.g10170123ebeshop.domain.entity.common.CommonProduct;
import de.telran.g10170123ebeshop.domain.entity.interfaces.Product;
import de.telran.g10170123ebeshop.exception.Response;
import de.telran.g10170123ebeshop.exception.exceptions.EmptyProductListException;
import de.telran.g10170123ebeshop.exception.exceptions.EntityValidationException;
import de.telran.g10170123ebeshop.exception.exceptions.IdDoesNotExistException;
import de.telran.g10170123ebeshop.service.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер продуктов.
 * Принимает входящие http-запросы
 * для получения, добавления и удаления продуктов.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * Сервис продуктов.
     * Содержит бизнес-логику, относящуюся к продуктам.
     */
    @Autowired
    private ProductService service;

    /**
     * Получение всех продуктов.
     *
     * @return список всех продуктов, хранящихся в БД.
     */
    @GetMapping
    public List<Product> getAll() {
        List<Product> all = service.getAll();
        if (all.isEmpty()) {
            throw new EmptyProductListException("Product list is empty!");
        }
        return all;
    }

    /**
     * Получение продукта по идентификатору.
     *
     * @param id    идентификатор продукта.
     * @return      продукт, имеющий переданный идентификатор.
     */
    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        Product product = service.getById(id);
        if (product == null) {
            throw new IdDoesNotExistException("Id doesn`t exist!");
        }
        return product;
    }

    /**
     * Добавление продукта.
     *
     * @param product   объект продукта, содержащийся в теле POST-запроса.
     * @return          объект сохраняемого продукта в случае успешного сохранения.
     */
    @PostMapping
    public Product add(@Valid @RequestBody CommonProduct product) {
        try {
            service.addProduct(product);
            return product;
        } catch (Exception e) {
            throw new EntityValidationException(e.getMessage());
        }
    }

    /**
     * Удаление продукта.
     *
     * @param id идентификатор удаляемого из БД продукта.
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteById(id);
    }

    /**
     * Удаление продукта.
     *
     * @param name наименование удаляемого из БД продукта.
     */
    @DeleteMapping("/name/{name}")
    public void delete(@PathVariable String name) {
        service.deleteByName(name);
    }

    /**
     * Получение количества продуктов.
     *
     * @return количество продуктов, содержащихся в БД.
     */
    @GetMapping("/count")
    public int getCount() {
        return service.getCount();
    }

    /**
     * Получение стоимости всех продуктов.
     *
     * @return суммарная стоимость всех продуктов, содержащихся в БД.
     */
    @GetMapping("/total")
    public double getTotalPrice() {
        return service.getTotalPrice();
    }

    /**
     * Получение средней стоимости.
     *
     * @return средняя стоимость продукта из всех продуктов, содержащихся в БД.
     */
    @GetMapping("/average")
    public double getAverage() {
        return service.getAveragePrice();
    }

    // Минус данного подхода заключается в том,
    // что такой метод нужно писать в каждом контроллере,
    // в котором мы хотим обрабатывать наши ошибки,
    // либо размещать этот метод в супер-классе или интерфейсе,
    // от которого наследуются наши контроллеры (а это не всегда удобно)
    @ExceptionHandler(EmptyProductListException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(EmptyProductListException e) {
        return new Response(e.getMessage());
    }
}