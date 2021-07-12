package com.amazon.demo.exceptions;

/**
 * @author Anand B S
 * @date 04 Jul 2021
 */
public class JsonKeyNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2152933494779949744L;

    public JsonKeyNotFoundException() {
        super();
    }

    public JsonKeyNotFoundException(String message) {
        super(message);
    }

    public JsonKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}