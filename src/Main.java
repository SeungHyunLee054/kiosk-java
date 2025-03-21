import module.cart.domain.model.Cart;
import module.cart.service.CartService;
import module.io.input.Input;
import module.io.output.Output;
import module.kiosk.Kiosk;
import module.menu.domain.model.Menu;
import module.menu.service.MenuService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Input input = Input.getInstance();
        Output output = new Output();
        List<Menu> menuList = new ArrayList<>();
        CartService cartService = new CartService(input, Cart.getInstance());
        MenuService menuService = new MenuService(menuList);
        Kiosk kiosk = new Kiosk(menuService, input, output, cartService);

        kiosk.start();
    }
}
