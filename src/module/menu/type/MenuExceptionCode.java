package module.menu.type;

public enum MenuExceptionCode {
    INPUT_ZERO_EXIT("Kiosk 를 종료합니다."),
    INPUT_ZERO_BACK("뒤로가기"),
    CANCEL_CONFIRM_ORDER("주문을 취소합니다."),
    CONFIRM_ORDER("주문 완료"),
    INPUT_WRONG("옳바르지 않은 입력값입니다."),
    EMPTY_MENU("해당 메뉴가 없습니다."),
    EMPTY_MENU_ITEM("해당 메뉴 아이템이 없습니다.");

    private final String message;

    MenuExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
