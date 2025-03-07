package io.input.exception;

import exception.BaseException;
import io.input.type.InputExceptionCode;

public class InputException extends BaseException {
    private final InputExceptionCode inputExceptionCode;
    private final String message;


    public InputException(InputExceptionCode inputExceptionCode) {
        this.inputExceptionCode = inputExceptionCode;
        this.message = inputExceptionCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Enum<?> getErrorCode() {
        return this.inputExceptionCode;
    }
}
