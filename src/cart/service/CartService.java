package cart.service;

import cart.domain.model.Cart;
import menu.domain.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<Cart> cartList = new ArrayList<>();

    public List<Cart> addCartItem(Menu menu) {
        cartList.forEach(cart -> {
            if (cart.getMenu().getName().equals(menu.getName())) {
                cart.setQuantity(cart.getQuantity() + 1);
            }
        });

        cartList.add(new Cart(menu, 1));

        return cartList;
    }

    public List<Cart> removeCartList() {
        this.cartList = new ArrayList<>();
        return this.cartList;
    }
}
