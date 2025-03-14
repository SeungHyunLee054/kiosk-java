package module.cart.type;

public enum CartExceptionCode {
    CANCEL_ADD_CART("장바구니 추가를 취소합니다."),
    CANCEL_CONFIRM_ORDER("주문을 취소합니다."),
    CONFIRM_ORDER("주문을 완료."),
    SUCCESS_DELETE("장바구니에서 해당 메뉴 삭제"),
    RETURN_TO_MENU("메뉴판으로 돌아갑니다."),
    NOT_EXIST_MENU("장바구니에 존재하지 않는 메뉴입니다."),
    INPUT_WRONG("옳바르지 않은 입력값입니다.");

    private final String message;

    CartExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
