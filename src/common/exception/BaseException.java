package common.exception;

/**
 * 추상 클래스를 통해 구현해야하는 CustomException의 메서드를 정의
 */
public abstract class BaseException extends RuntimeException {
    public abstract String getMessage();

    public abstract Enum<?> getErrorCode();

}
