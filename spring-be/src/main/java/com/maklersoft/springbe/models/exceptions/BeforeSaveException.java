package com.maklersoft.springbe.models.exceptions;

public class BeforeSaveException extends Exception{
    public BeforeSaveException(String msg, Throwable t) {
        super(msg, t);
    }

    public BeforeSaveException(String msg) {
        super(msg);
    }
}
