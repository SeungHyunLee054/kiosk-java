package module.cart.service;

import module.cart.domain.model.Cart;
import module.cart.exception.CartException;
import module.io.input.Input;
import module.menu.domain.model.Menu;

import java.util.Map;

import static module.cart.type.CartExceptionCode.CANCEL_ADD_CART;
import static module.cart.type.CartExceptionCode.INPUT_WRONG;


public class CartService {
    private final Input input = new Input();
    private final Cart cart = new Cart();

    private final int ONE = 1;
    private final int TWO = 2;

    public void addCart(Menu menu) {
        System.out.println(menu.getName() + "     | W "
                + menu.getPrice() + "       | " + menu.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인     2. 취소");

        int input = this.input.inputInt();

        if (ONE == input) {
            cart.addMenuToCart(menu);
            System.out.println(menu.getName() + " 이 장바구니에 추가되었습니다.");
        } else if (TWO == input) {
            throw new CartException(CANCEL_ADD_CART);
        } else {
            throw new CartException(INPUT_WRONG);
        }
    }

    public Map<Menu, Integer> getCart() {
        return cart.getCart();
    }

    public void removeCart() {
        cart.removeCart();
    }

}