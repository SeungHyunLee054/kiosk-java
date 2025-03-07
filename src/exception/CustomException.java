package exception;

import type.CustomExceptionCode;

public class CustomException extends RuntimeException {
    private final CustomExceptionCode customException;
    private final String message;


    public CustomException(CustomExceptionCode customExceptionCode) {
        this.customException = customExceptionCode;
        this.message = customExceptionCode.getMessage();
    }

    public CustomExceptionCode getCustomException() {
        return customException;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
