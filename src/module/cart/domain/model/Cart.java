package module.cart.domain.model;

import module.cart.exception.CartException;
import module.cart.type.CartExceptionCode;
import module.menu.domain.model.MenuItem;

import java.util.HashMap;
import java.util.Map;


/**
 * 장바구니는 메뉴의 정보와 수량을 키-값으로 매핑
 */
public class Cart {
    private static final Map<MenuItem, Integer> cartItems = new HashMap<>();

    private static final Cart instance = new Cart();

    private Cart() {
    }

    public static Cart getInstance() {
        return instance;
    }

    public void addMenuToCart(MenuItem menuItem) {
        cartItems.put(menuItem, cartItems.getOrDefault(menuItem, 0) + 1);
    }

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
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
