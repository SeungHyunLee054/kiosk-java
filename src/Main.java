import module.cart.domain.model.Cart;
import module.cart.service.CartService;
import module.io.input.Input;
import module.io.output.Output;
import module.kiosk.Kiosk;
import module.menu.domain.model.Menu;
import module.menu.domain.model.MenuItem;
import module.menu.service.MenuService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Input input = Input.getInstance();
        Output output = new Output();
        List<Menu> menuList = new ArrayList<>();
        Map<MenuItem, Integer> cartItems = new HashMap<>();
        Cart cart = new Cart(cartItems);
        CartService cartService = new CartService(input, cart);
        MenuService menuService = new MenuService(menuList);
        Kiosk kiosk = new Kiosk(menuService, input, output, cartService);

        kiosk.start();
    }
}
