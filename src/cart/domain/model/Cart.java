package cart.domain.model;

import menu.domain.model.Menu;

public class Cart {
    private final Menu menu;
    private int quantity;

    public Cart(Menu menuId, int quantity) {
        this.menu = menuId;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
