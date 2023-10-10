package de.telran.g10170123ebeshop.logging;

import de.telran.g10170123ebeshop.domain.entity.common.CommonProduct;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private Logger logger = LoggerFactory.getLogger(AspectLogging.class);

    @Pointcut("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.addProduct(..))")
    public void addProduct() {}

    @Before("addProduct()")
    public void beforeAddingProduct(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        logger.info("Вызван метод addProduct класса JpaProductService с параметром {}.", params[0]);
    }

    @After("addProduct()")
    public void afterAddingProduct(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        logger.info("Сохранённому продукту присвоен идентификатор {}.",
                ((CommonProduct) params[0]).getId());
    }

    @Pointcut("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.getCount(..))")
    public void getCount() {}

    @AfterReturning("getCount()")
    public void afterProductCountReturning() {
        logger.info("Метод getCount успешно вернул значение");
    }

    @Pointcut("execution(* de.telran.g10170123ebeshop.service.jpa.JpaProductService.deleteById(..))")
    public void deleteById() {}

    @AfterThrowing("deleteById()")
    public void throwsExceptionIfIdIsIncorrect(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        logger.error("Метод deleteById выбросил исключение. Некорректный ID - {}.",
                params[0]);
    }

    @Around("getCount()")
    public Object aroundGetProductCount(ProceedingJoinPoint joinPoint) {
        logger.info("Отработал Around метода getCount()");
        try {
            Object result = joinPoint.proceed();
            logger.info("Метод getCount() отработал с результатом {}", result);
            logger.info("Подменяем результат и возвращаем 777");
            return 777;
        } catch (Throwable e) {
            logger.error("Тут какая-то ошибка!");
            throw new RuntimeException(e);
        }
    }
}