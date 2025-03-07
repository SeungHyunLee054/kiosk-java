package module.cart.exception;

import common.exception.BaseException;
import module.cart.type.CartExceptionCode;
import module.io.input.type.InputExceptionCode;

public class CartException extends BaseException {
    private final CartExceptionCode cartExceptionCode;
    private final String message;


    public CartException(CartExceptionCode cartExceptionCode) {
        this.cartExceptionCode = cartExceptionCode;
        this.message = cartExceptionCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Enum<?> getErrorCode() {
        return this.cartExceptionCode;
    }
}
