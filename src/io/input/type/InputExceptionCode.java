package io.input.type;

public enum InputExceptionCode {
    INPUT_ZERO_EXIT("종료"),
    INPUT_ZERO_BACK("뒤로가기"),
    CANCEL_ADD_CART("장바구니 추가를 취소합니다."),
    CANCEL_CONFIRM_ORDER("주문을 취소합니다."),
    INPUT_WRONG("잘못된 값을 입력하였습니다.");

    private final String message;

    InputExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
