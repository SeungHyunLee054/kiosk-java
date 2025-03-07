package menu.type;

public enum MenuExceptionCode {
    EMPTY_MENU("해당 메뉴가 없습니다.");

    private final String message;

    MenuExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
