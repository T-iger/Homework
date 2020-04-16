package com.lihu.homework;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Li
 * @date 2020/4/12
 **/

@ResponseStatus(HttpStatus.NOT_FOUND)//会定义成资源找不到
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
