package module.cart.domain.model;

import module.menu.domain.model.MenuItem;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private Map<MenuItem, Integer> cartItems = new HashMap<>();

    public void addMenuToCart(MenuItem menuItem) {
        cartItems.put(menuItem, cartItems.getOrDefault(menuItem, 0) + 1);
    }

    public Map<MenuItem, Integer> getCartItems() {
        return cartItems;
    }

    public void removeCart() {
        this.cartItems = new HashMap<>();
    }

}
