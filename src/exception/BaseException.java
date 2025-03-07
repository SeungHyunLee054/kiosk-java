package exception;

public abstract class BaseException extends RuntimeException {
    public abstract String getMessage();

    public abstract Enum<?> getErrorCode();

}
