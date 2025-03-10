package module.cart.domain.model;

import module.menu.domain.model.MenuItem;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private Map<MenuItem, Integer> cart = new HashMap<>();

    public void addMenuToCart(MenuItem menuItem) {
        cart.put(menuItem, cart.getOrDefault(menuItem, 0) + 1);
    }

    public Map<MenuItem, Integer> getCart() {
        return cart;
    }

    public void removeCart() {
        this.cart = new HashMap<>();
    }

}
