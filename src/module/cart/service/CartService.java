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

    public List<Cart> addCart(Menu menu) {
        int input = this.input.inputInt();

        if (ONE == input) {
            return addCartItem(menu);
        } else if (TWO == input) {
            throw new CartException(CANCEL_ADD_CART);
        } else {
            throw new CartException(INPUT_WRONG);
        }
    }

    private List<Cart> addCartItem(Menu menu) {
        cartList.forEach(cart -> {
            if (cart.getMenu().getName().equals(menu.getName())) {
                cart.setQuantity(cart.getQuantity() + 1);
            }
        });

        cartList.add(new Cart(menu, 1));

        return cartList;
    }

    public void removeCartList() {
        this.cartList = new ArrayList<>();
    }
}
