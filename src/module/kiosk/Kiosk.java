package module.kiosk;

import module.cart.domain.model.Cart;
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
import java.util.logging.Level;
import java.util.logging.Logger;


public class Kiosk {
    private final Logger logger = Logger.getLogger(Kiosk.class.getName());
    private final MenuService menuService;
    private final Input input;
    private final Output output;
    private final CartService cartService;

    public Kiosk(MenuService menuService, Input input, Output output, CartService cartService) {
        this.menuService = menuService;
        this.input = input;
        this.output = output;
        this.cartService = cartService;
        this.menuService.inputTestData();
    }

    /**
     * 각 기능을 불러와 실행
     * depth를 낮추기 위해 메서드로 추출
     */
    public void start() {
        try (input) {
            runKioskLoop();
        } catch (MenuException e) {
            output.printMessage(e.getMessage());
        }
    }

    /**
     * do-while문을 통해 kiosk 기능 실행, exception을 통해 무한 루프 컨트롤
     */
    private void runKioskLoop() {
        do {
            Category category;
            try {
                output.printCategory();

                if (!cartService.getCart().getCartItems().isEmpty()) {
                    category = orderMenuOrGetCategory();
                } else {
                    category = getCategory();
                }

                List<MenuItem> menuItemList = menuService.getMenuItemList(category);
                output.printMenuItemList(category, menuItemList);

                MenuItem seletedMenuItem = getSeletedMenuItem(category);

                cartService.addCart(seletedMenuItem);
            } catch (MenuException e) {
                if (e.getErrorCode().equals(MenuExceptionCode.INPUT_ZERO_EXIT)) {
                    throw e;
                }
                output.printMessage(e.getMessage());
            } catch (CartException e) {
                if (!e.getErrorCode().equals(CartExceptionCode.CONFIRM_ORDER) &&
                        !e.getErrorCode().equals(CartExceptionCode.SUCCESS_DELETE)) {
                    output.printMessage(e.getMessage());
                }
            } catch (InputException e) {
                output.printMessage(e.getMessage());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "예상치 못한 오류 발생", e);
                break;
            }
        } while (true);
    }

    /**
     * 카테고리 값을 가져오는 메서드, 0 입력시 프로그램 종료
     *
     * @return Category
     */
    private Category getCategory() {
        int input = this.input.inputInt();
        validateZeroExit(input);

        return Category.fromCategoryVal(input);
    }

    /**
     * 주문을 진행하거나 카테고리 값을 가져오는 메서드, 0 입력시 프로그램 종료
     *
     * @return Category
     */
    private Category orderMenuOrGetCategory() {
        output.printCheckOrderMenu();
        int input = this.input.inputInt();
        validateZeroExit(input);

        if (input >= 1 && input <= 3) {
            return Category.fromCategoryVal(input);
        } else if (input == 4) {
            confirmOrderOrReturn();
        } else if (input == 5) {
            cartService.removeCart();
            throw new CartException(CartExceptionCode.CANCEL_CONFIRM_ORDER);
        }

        throw new MenuException(MenuExceptionCode.INPUT_WRONG);
    }

    /**
     * 주문을 확정하거나 메뉴판으로 돌아가는 메서드
     */
    private void confirmOrderOrReturn() {
        Cart cart = cartService.getCart();
        int sum = output.printConfirmOrderMenu(cart);

        int input = this.input.inputInt();
        if (input == 1) {
            applyDiscount(sum);
        } else if (input == 2) {
            throw new CartException(CartExceptionCode.RETURN_TO_MENU);
        } else if (input == 3) {
            removeSelectedMenuItemInCart();
        }

        throw new CartException(CartExceptionCode.INPUT_WRONG);
    }

    /**
     * 메뉴명을 입력하여 장바구니에서 해당 메뉴를 삭제하는 메서드
     */
    private void removeSelectedMenuItemInCart() {
        output.printRemoveMenuItemInCart();

        String input = this.input.inputString();
        cartService.removeMenuItemInCart(input);

        throw new CartException(CartExceptionCode.SUCCESS_DELETE);
    }

    /**
     * 총 결제 비용에 할인을 적용하는 메서드
     *
     * @param sum 할인 전 총 결제 금액
     */
    private void applyDiscount(int sum) {
        output.printDiscountOrder();

        int input = this.input.inputInt();

        Discount discount = Discount.fromDiscountPercent(input);
        sum = (int) (sum * (1 - discount.getDiscountPercent() / 100.00));
        System.out.println("주문이 완료되었습니다. 금액은 W " + sum + " 입니다.");
        cartService.removeCart();
        throw new CartException(CartExceptionCode.CONFIRM_ORDER);
    }

    /**
     * 내가 선택한 메뉴의 정보를 가져오는 메서드, 0 입력시 뒤로가기
     *
     * @param category 메뉴의 카테고리
     * @return 내가 선택한 메뉴
     */
    private MenuItem getSeletedMenuItem(Category category) {
        int input = this.input.inputInt();
        if (input == 0) {
            throw new MenuException(MenuExceptionCode.INPUT_ZERO_BACK);
        }

        return menuService.getMenuItem(category, input);
    }

    /**
     * 종료를 위해 0이 입력되었는지 확인하는 메서드
     *
     * @param input 입력받은 값
     */
    private void validateZeroExit(int input) {
        if (input == 0) {
            throw new MenuException(MenuExceptionCode.INPUT_ZERO_EXIT);
        }
    }
}
