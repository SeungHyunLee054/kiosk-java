package module.io.input.type;

public enum InputExceptionCode {
    INPUT_WRONG("잘못된 값을 입력하였습니다.");

    private final String message;

    InputExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
