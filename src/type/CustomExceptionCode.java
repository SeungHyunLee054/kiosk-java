package type;

public enum CustomExceptionCode {
    INPUT_ZERO_EXIT("종료"),
    INPUT_ZERO_BACK("뒤로가기"),
    EMPTY_MENU("해당 메뉴가 없습니다.")
    ;

    private final String message;

    CustomExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
