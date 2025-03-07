package module.kiosk;

import module.cart.domain.model.Cart;
import module.cart.exception.CartException;
import module.cart.service.CartService;
import module.io.input.Input;
import module.io.input.exception.InputException;
import module.io.output.Output;
import module.menu.domain.model.Menu;
import module.menu.exception.MenuException;
import module.menu.service.MenuService;
import module.menu.type.Category;

import java.util.ArrayList;
import java.util.List;

import static module.menu.type.MenuExceptionCode.CANCEL_CONFIRM_ORDER;
import static module.menu.type.MenuExceptionCode.CONFIRM_ORDER;


public class Kiosk {
    private final MenuService menuService;
    private final Input input;
    private final Output output;
    private final CartService cartService;

    private static boolean orderMenuFlag = false;
    private List<Cart> cartList = new ArrayList<>();

    public Kiosk(MenuService menuService, Input input, Output output, CartService cartService) {
        this.menuService = menuService;
        this.input = input;
        this.output = output;
        this.cartService = cartService;
    }

    public void start() {
        menuService.inputTestData();

        do {
            Category category;
            try {
                output.printCategory();
                if (orderMenuFlag) {
                    output.printCheckOrderMenu();
                }
                category = menuService.selectCategory(cartList, orderMenuFlag);
                output.printMenu(menuService.getMenuList(category));
            } catch (MenuException e) {
                if (e.getErrorCode().equals(CONFIRM_ORDER)) {
                    continue;
                } else if (e.getErrorCode().equals(CANCEL_CONFIRM_ORDER)) {
                    System.out.println(e.getMessage());
                    continue;
                } else {
                    System.out.println(e.getMessage());
                    break;
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            Menu selectedMenu;
            try {
                selectedMenu = menuService.getMenu(category);
                output.printSelectedMenu(selectedMenu);
                output.printAddCart(selectedMenu);
            } catch (CartException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (MenuException e) {
                System.out.println(e.getMessage());
                continue;

            }

            try {
                cartList = cartService.addCart(selectedMenu);
                output.printCart(selectedMenu);
                orderMenuFlag = true;
            } catch (InputException e) {
                System.out.println(e.getMessage());
                continue;
            }

        } while (true);

        input.closeScanner();
    }

    public static void setOrderMenuFlag(boolean orderMenuFlag) {
        Kiosk.orderMenuFlag = orderMenuFlag;
    }
}
