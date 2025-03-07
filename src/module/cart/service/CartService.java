package module.cart.service;

import module.cart.domain.model.Cart;
import module.cart.exception.CartException;
import module.io.input.Input;
import module.menu.domain.model.Menu;

import java.util.ArrayList;
import java.util.List;

import static module.cart.type.CartExceptionCode.CANCEL_ADD_CART;
import static module.cart.type.CartExceptionCode.INPUT_WRONG;


public class CartService {
    private List<Cart> cartList = new ArrayList<>();
    private final Input input = new Input();
    private final int ONE = 1;
    private final int TWO = 2;

    public void addCart(Menu menu) {
        System.out.println(menu.getName() + "     | W "
                + menu.getPrice() + "       | " + menu.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인     2. 취소");

        int input = this.input.inputInt();

        if (ONE == input) {
            addCartItem(menu);
            System.out.println(menu.getName() + " 이 장바구니에 추가되었습니다.");
        } else if (TWO == input) {
            throw new CartException(CANCEL_ADD_CART);
        } else {
            throw new CartException(INPUT_WRONG);
        }
    }

    private void addCartItem(Menu menu) {
        cartList.forEach(cart -> {
            if (cart.getMenu().getName().equals(menu.getName())) {
                cart.setQuantity(cart.getQuantity() + 1);
            }
        });

        cartList.add(new Cart(menu, 1));

    }

    public void removeCartList() {
        this.cartList = new ArrayList<>();
    }

    public List<Cart> getCartList() {
        return cartList;
    }
}
