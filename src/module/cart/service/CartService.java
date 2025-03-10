package module.cart.service;

import module.cart.domain.model.Cart;
import module.cart.exception.CartException;
import module.cart.type.CartExceptionCode;
import module.io.input.Input;
import module.menu.domain.model.MenuItem;

import java.util.Map;


public class CartService {
    private final Input input;
    private final Cart cart;

    public CartService(Input input, Cart cart) {
        this.input = input;
        this.cart = cart;
    }

    private static final int ONE = 1;
    private static final int TWO = 2;

    public void addCart(MenuItem menuItem) {
        System.out.println(menuItem.getName() + "     | W "
                + menuItem.getPrice() + "       | " + menuItem.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인     2. 취소");

        int input = this.input.inputInt();

        if (ONE == input) {
            cart.addMenuToCart(menuItem);
            System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.");
        } else if (TWO == input) {
            throw new CartException(CartExceptionCode.CANCEL_ADD_CART);
        } else {
            throw new CartException(CartExceptionCode.INPUT_WRONG);
        }
    }

    public Map<MenuItem, Integer> getCart() {
        return cart.getCartItems();
    }

    public void removeCart() {
        cart.removeCart();
    }

}