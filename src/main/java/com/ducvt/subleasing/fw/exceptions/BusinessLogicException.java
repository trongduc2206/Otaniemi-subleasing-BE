package com.ducvt.subleasing.fw.exceptions;



public class BusinessLogicException extends ApplicationException {
    public BusinessLogicException(String code) {
        super(code);
    }

    public BusinessLogicException(String code, String message) {
        super(code, message);
    }
}
