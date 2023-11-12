package com.ducvt.subleasing.fw.exceptions;

public class  AuthorizationException extends ApplicationException{
    public AuthorizationException(String code) {
        super(code);
    }
}
