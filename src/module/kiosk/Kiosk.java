package module.kiosk;

import module.cart.exception.CartException;
import module.cart.service.CartService;
import module.cart.type.CartExceptionCode;
import module.io.input.Input;
import module.io.input.exception.InputException;
import module.io.output.Output;
import module.kiosk.type.Discount;
import module.menu.domain.model.MenuItem;
import module.menu.exception.MenuException;
import module.menu.service.MenuService;
import module.menu.type.Category;
import module.menu.type.MenuExceptionCode;

import java.util.List;


public class Kiosk {
    private final MenuService menuService;
    private final Input input;
    private final Output output;
    private final CartService cartService;

    private boolean orderMenuFlag = false;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int TWO = 2;
    private final int THREE = 3;
    private final int FOUR = 4;
    private final int FIVE = 5;

    public Kiosk(MenuService menuService, Input input, Output output, CartService cartService) {
        this.menuService = menuService;
        this.input = input;
        this.output = output;
        this.cartService = cartService;
        this.menuService.inputTestData();
    }

    public void start() {
        try {
            runKioskLoop();
        } catch (MenuException e) {
            output.printMessage(e.getMessage());
        } finally {
            input.closeScanner();
        }
    }

    private void runKioskLoop() {
        do {
            Category category;
            try {
                output.printCategory();

                if (orderMenuFlag) {
                    category = orderMenuOrGetCategory();
                } else {
                    category = getCategory();
                }

                List<MenuItem> menuItemList = menuService.getMenuItemList(category);
                output.printMenuItemList(category, menuItemList);

                MenuItem seletedMenuItem = getSeletedMenuItem(category);

                cartService.addCart(seletedMenuItem);
                orderMenuFlag = true;
            } catch (MenuException e) {
                if (e.getErrorCode().equals(MenuExceptionCode.INPUT_ZERO_EXIT)) {
                    throw e;
                }
                output.printMessage(e.getMessage());
            } catch (CartException e) {
                if (!e.getErrorCode().equals(CartExceptionCode.CONFIRM_ORDER)) {
                    output.printMessage(e.getMessage());
                }
            } catch (InputException e) {
                output.printMessage(e.getMessage());
            }
        } while (true);
    }

    private Category getCategory() {
        int input = this.input.inputInt();
        validateZeroExit(input);

        return Category.fromCategoryVal(input);
    }

    private Category orderMenuOrGetCategory() {
        output.printCheckOrderMenu();
        int input = this.input.inputInt();
        validateZeroExit(input);

        if (input == FOUR) {
            confirmOrder();
        } else if (input >= ONE && input <= THREE) {
            return Category.fromCategoryVal(input);
        } else if (input == FIVE) {
            cartService.removeCart();
            orderMenuFlag = false;
            throw new CartException(CartExceptionCode.CANCEL_CONFIRM_ORDER);
        }

        throw new MenuException(MenuExceptionCode.INPUT_WRONG);
    }

    private void confirmOrder() {
        int sum = output.printConfirmOrderMenu(cartService.getCart());

        int input = this.input.inputInt();
        if (input == ONE) {
            applyDiscount(sum);
        } else if (input != TWO) {
            throw new MenuException(MenuExceptionCode.INPUT_WRONG);
        }
    }

    private void applyDiscount(int sum) {
        output.printDiscountOrder();

        int input = this.input.inputInt();

        Discount discount = Discount.fromDiscountPercent(input);
        sum = (int) (sum * ((100 - discount.getDiscountPercent()) * 0.01));
        System.out.println("주문이 완료되었습니다. 금액은 W " + sum + " 입니다.");
        cartService.removeCart();
        orderMenuFlag = false;
        throw new CartException(CartExceptionCode.CONFIRM_ORDER);
    }

    private MenuItem getSeletedMenuItem(Category category) {
        int input = this.input.inputInt();
        if (input == ZERO) {
            throw new MenuException(MenuExceptionCode.INPUT_ZERO_BACK);
        }

        return menuService.getMenuItem(category, input);
    }

    private void validateZeroExit(int input) {
        if (input == ZERO) {
            throw new MenuException(MenuExceptionCode.INPUT_ZERO_EXIT);
        }
    }
}
