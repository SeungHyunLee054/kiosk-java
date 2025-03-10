package module.kiosk;

import module.cart.exception.CartException;
import module.cart.service.CartService;
import module.io.input.Input;
import module.io.input.exception.InputException;
import module.io.input.type.Discount;
import module.io.output.Output;
import module.menu.domain.model.Menu;
import module.menu.exception.MenuException;
import module.menu.service.MenuService;
import module.menu.type.Category;

import static module.menu.type.MenuExceptionCode.*;


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
        menuService.inputTestData();
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
                    if (input == FOUR && orderMenuFlag) {
                        int sum = output.printConfirmOrderMenu(cartService.getCart());

                        int nextInput = this.input.inputInt();
                        if (nextInput == ONE) {
                            discountOrder(sum);
                            continue;
                        } else if (nextInput == TWO) {
                            continue;
                        } else {
                            throw new MenuException(INPUT_WRONG);
                        }
                    } else if (input == FIVE && orderMenuFlag) {
                        cartService.removeCart();
                        orderMenuFlag = false;
                        throw new MenuException(CANCEL_CONFIRM_ORDER);
                    }
                } else {
                    input = this.input.inputInt();
                    validateZeroExit(input);
                }

                category = Category.fromCategoryVal(input);
                menuService.getMenuList(category);
            } catch (MenuException e) {
                System.out.println(e.getMessage());
                if (e.getErrorCode().equals(INPUT_WRONG)) {
                    continue;
                } else {
                    break;
                }
            } catch (InputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            Menu selectedMenu;
            try {
                int input = this.input.inputInt();
                if (input == ZERO) {
                    throw new MenuException(INPUT_ZERO_BACK);
                }

                selectedMenu = menuService.getMenu(category, input);
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
            throw new MenuException(INPUT_ZERO_EXIT);
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
