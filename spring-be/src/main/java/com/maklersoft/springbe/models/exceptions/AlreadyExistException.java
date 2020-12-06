package com.maklersoft.springbe.models.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AlreadyExistException extends AuthenticationException {
    public AlreadyExistException(String msg, Throwable t) {
        super(msg, t);
    }

    public AlreadyExistException(String msg) {
        super(msg);
    }
}
