package de.telran.g10170123ebeshop.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Здесь недостаток в том, что мы отправляем
// только статус ответа, без какого-то пояснения
// причин, из-за чего ошибка возникла
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class IdDoesNotExistException extends RuntimeException {

    public IdDoesNotExistException(String message) {
        super(message);
    }
}