package org.codesolt.mongokubernetesdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoffeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CoffeeNotFoundException(@Nullable String s) {
        super(s);
    }

    public CoffeeNotFoundException(@Nullable String s, @Nullable Throwable throwable) {
        super(s, throwable);
    }
}
