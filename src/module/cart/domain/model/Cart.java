package module.cart.domain.model;

import module.menu.domain.model.Menu;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private final Map<Menu, Integer> cart = new HashMap<>();

    public void addMenuToCart(Menu menu) {
        cart.put(menu, cart.getOrDefault(menu, 0) + 1);
    }

    public Map<Menu, Integer> getCart() {
        return cart;
    }

    public void removeCart() {
        for (Map.Entry<Menu, Integer> entry : cart.entrySet()) {
            cart.remove(entry.getKey());
        }
    }

}
