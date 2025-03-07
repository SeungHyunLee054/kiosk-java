package kiosk;

import cart.domain.model.Cart;
import io.input.Input;
import io.input.exception.InputException;
import io.output.Output;
import menu.domain.model.Menu;
import menu.exception.MenuException;
import menu.service.MenuService;
import menu.type.Category;

import java.util.ArrayList;
import java.util.List;

import static io.input.type.InputExceptionCode.CANCEL_CONFIRM_ORDER;

public class Kiosk {
    private static final MenuService menuService = new MenuService();
    private static final Input input = new Input();
    private static final Output output = new Output();
    private static boolean orderMenuFlag = false;
    private static List<Cart> cartList = new ArrayList<>();

    public static void start() {
        menuService.inputTestData();

        do {
            Category category;
            try {
                output.printCategory();
                if (orderMenuFlag) {
                    input.checkOrderMenu();
                }
                category = input.selectCategory(cartList, orderMenuFlag);
                output.printMenu(menuService.getMenuList(category));
            } catch (InputException e) {
                System.out.println(e.getMessage());
                if (e.getErrorCode().equals(CANCEL_CONFIRM_ORDER)) {
                    continue;
                } else {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            Menu selectedMenu;
            try {
                int id = input.selectMenu();
                selectedMenu = menuService.getMenu(id, category);
                output.printSelectedMenu(selectedMenu);
            } catch (InputException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (MenuException e) {
                System.out.println(e.getMessage());
                continue;

            }

            try {
                cartList = input.addCart(selectedMenu);
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
