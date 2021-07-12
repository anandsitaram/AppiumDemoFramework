package com.amazon.demo.exceptions;

/**
 * @author Anand B S
 * @date 30 Jun 2021
 */
public class SearchResultNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2152933494779949744L;

    public SearchResultNotFoundException() {
        super();
    }

    public SearchResultNotFoundException(String message) {
        super(message);
    }

    public SearchResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
