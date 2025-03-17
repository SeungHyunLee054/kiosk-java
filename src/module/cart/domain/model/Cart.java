package module.cart.domain.model;

import module.menu.domain.model.MenuItem;

import java.util.HashMap;
import java.util.Map;


/**
 * 장바구니는 메뉴의 정보와 수량을 키-값으로 매핑
 */
public class Cart {
    private Map<MenuItem, Integer> cartItems;

    public Cart(Map<MenuItem, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public void addMenuToCart(MenuItem menuItem) {
        cartItems.put(menuItem, cartItems.getOrDefault(menuItem, 0) + 1);
    }

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }

    public void removeCart() {
        this.cartItems.clear();
    }

}
