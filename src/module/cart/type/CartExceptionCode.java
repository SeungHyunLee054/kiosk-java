package module.cart.type;

public enum CartExceptionCode {
    CANCEL_ADD_CART("장바구니 추가를 취소합니다."),
    CANCEL_CONFIRM_ORDER("주문을 취소합니다."),
    CONFIRM_ORDER("주문을 완료."),
    INPUT_WRONG("옳바르지 않은 입력값입니다.");

    private final String message;

    CartExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
