package module.menu.type;

public enum MenuExceptionCode {
    INPUT_ZERO_EXIT("종료"),
    CANCEL_CONFIRM_ORDER("주문을 취소합니다."),
    CONFIRM_ORDER("주문 완료"),
    INPUT_WRONG("잘못된 값을 입력하였습니다."),
    EMPTY_MENU("해당 메뉴가 없습니다.");

    private final String message;

    MenuExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
