package module.cart.domain.model;

import module.cart.exception.CartException;
import module.cart.type.CartExceptionCode;
import module.menu.domain.model.MenuItem;

import java.util.Map;


/**
 * 장바구니는 메뉴의 정보와 수량을 키-값으로 매핑
 */
public class Cart {
    private final Map<MenuItem, Integer> cartItems;

    public Cart(Map<MenuItem, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    public void addMenuToCart(MenuItem menuItem) {
        cartItems.put(menuItem, cartItems.getOrDefault(menuItem, 0) + 1);
    }

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        this.cartItems.clear();
    }

    public MenuItem findMenuItemByName(String name) {
        return cartItems.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new CartException(CartExceptionCode.NOT_EXIST_MENU))
                .getKey();
    }

    public void removeMenuItemFromCart(MenuItem menuItem) {
        cartItems.remove(menuItem);
    }

}
