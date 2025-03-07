package module.io.input.type;

public enum InputExceptionCode {
    INPUT_WRONG("옳바르지 않은 입력값입니다.");

    private final String message;

    InputExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
