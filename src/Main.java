import module.cart.service.CartService;
import module.io.input.Input;
import module.io.output.Output;
import module.kiosk.Kiosk;
import module.menu.service.MenuService;

public class Main {
    public static void main(String[] args) {
        Input input = new Input();
        Output output = new Output();
        CartService cartService = new CartService();
        MenuService menuService = new MenuService(input, output, cartService);
        Kiosk kiosk = new Kiosk(menuService, input, output, cartService);

        kiosk.start();
    }
}
