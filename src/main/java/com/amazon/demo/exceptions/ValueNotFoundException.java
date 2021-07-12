package com.amazon.demo.exceptions;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class ValueNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2152933494779949744L;

    public ValueNotFoundException() {
        super();
    }

    public ValueNotFoundException(String message) {
        super(message);
    }

    public ValueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}