package menu.exception;

import exception.BaseException;
import menu.type.MenuExceptionCode;

public class MenuException extends BaseException {
    private final MenuExceptionCode menuExceptionCode;
    private final String message;


    public MenuException(MenuExceptionCode menuExceptionCode) {
        this.menuExceptionCode = menuExceptionCode;
        this.message = menuExceptionCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Enum<?> getErrorCode() {
        return menuExceptionCode;
    }
}
