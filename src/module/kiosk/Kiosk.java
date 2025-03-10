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


public class Kiosk {
    private final MenuService menuService;
    private final Input input;
    private final Output output;
    private final CartService cartService;

    private boolean orderMenuFlag = false;
    private final int ZERO = 0;
    private final int ONE = 1;
    private final int TWO = 2;
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
        do {
            Category category;
            try {
                output.printCategory();
                int input;

                if (orderMenuFlag) {
                    output.printCheckOrderMenu();
                    input = this.input.inputInt();
                    validateZeroExit(input);
                    if (input == FOUR) {
                        int sum = output.printConfirmOrderMenu(cartService.getCart());

                        int nextInput = this.input.inputInt();
                        if (nextInput == ONE) {
                            discountOrder(sum);
                            continue;
                        } else if (nextInput == TWO) {
                            continue;
                        } else {
                            throw new MenuException(MenuExceptionCode.INPUT_WRONG);
                        }
                    } else if (input == FIVE) {
                        cartService.removeCart();
                        orderMenuFlag = false;
                        throw new CartException(CartExceptionCode.CANCEL_CONFIRM_ORDER);
                    }
                } else {
                    input = this.input.inputInt();
                    validateZeroExit(input);
                }

                category = Category.fromCategoryVal(input);
                menuService.getMenuItemList(category);
            } catch (MenuException e) {
                System.out.println(e.getMessage());
                if (e.getErrorCode().equals(MenuExceptionCode.INPUT_ZERO_EXIT)) {
                    break;
                } else {
                    continue;
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (CartException e) {
                System.out.println(e.getMessage());
                continue;
            }

            MenuItem selectedMenu;
            try {
                int input = this.input.inputInt();
                if (input == ZERO) {
                    throw new MenuException(MenuExceptionCode.INPUT_ZERO_BACK);
                }

                selectedMenu = menuService.getMenuItem(category, input);
            } catch (CartException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (MenuException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                cartService.addCart(selectedMenu);
                orderMenuFlag = true;
            } catch (CartException e) {
                System.out.println(e.getMessage());
                continue;
            }

        } while (true);

        input.closeScanner();
    }

    private void validateZeroExit(int input) {
        if (input == ZERO) {
            throw new MenuException(MenuExceptionCode.INPUT_ZERO_EXIT);
        }
    }

    private void discountOrder(int sum) {
        output.printDiscountOrder();

        int input = this.input.inputInt();

        Discount discount = Discount.fromDiscountPercent(input);
        sum = (int) (sum * ((100 - discount.getDiscountPercent()) * 0.01));
        System.out.println("주문이 완료되었습니다. 금액은 W " + sum + " 입니다.");
        cartService.removeCart();
        orderMenuFlag = false;
    }
}
