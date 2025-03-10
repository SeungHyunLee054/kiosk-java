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
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;

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
        try {
            runKioskLoop();
        } catch (MenuException e) {
            output.printMessage(e.getMessage());
        } finally {
            input.closeScanner();
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

    /**
     * 카테고리 값을 가져오는 메서드, 0 입력시 프로그램 종료
     * @return Category
     */
    private Category getCategory() {
        int input = this.input.inputInt();
        validateZeroExit(input);

        return Category.fromCategoryVal(input);
    }

    /**
     * 주문을 진행하거나 카테고리 값을 가져오는 메서드, 0 입력시 프로그램 종료
     * @return Category
     */
    private Category orderMenuOrGetCategory() {
        output.printCheckOrderMenu();
        int input = this.input.inputInt();
        validateZeroExit(input);

        if (input == FOUR) {
            confirmOrReturnOrder();
        } else if (input >= ONE && input <= THREE) {
            return Category.fromCategoryVal(input);
        } else if (input == FIVE) {
            cartService.removeCart();
            orderMenuFlag = false;
            throw new CartException(CartExceptionCode.CANCEL_CONFIRM_ORDER);
        }

        throw new MenuException(MenuExceptionCode.INPUT_WRONG);
    }

    /**
     * 주문을 확정하거나 메뉴판으로 돌아가는 메서드
     */
    private void confirmOrReturnOrder() {
        int sum = output.printConfirmOrderMenu(cartService.getCart());

        int input = this.input.inputInt();
        if (input == ONE) {
            applyDiscount(sum);
        } else if (input != TWO) {
            throw new MenuException(MenuExceptionCode.INPUT_WRONG);
        }
    }

    /**
     * 총 결제 비용에 할인을 적용하는 메서드
     * @param sum 할인 전 총 결제 금액
     */
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

    /**
     * 내가 선택한 메뉴의 정보를 가져오는 메서드, 0 입력시 뒤로가기
     * @param category 메뉴의 카테고리
     * @return 내가 선택한 메뉴
     */
    private MenuItem getSeletedMenuItem(Category category) {
        int input = this.input.inputInt();
        if (input == ZERO) {
            throw new MenuException(MenuExceptionCode.INPUT_ZERO_BACK);
        }

        return menuService.getMenuItem(category, input);
    }

    /**
     * 종료를 위해 0이 입력되었는지 확인하는 메서드
     * @param input 입력받은 값
     */
    private void validateZeroExit(int input) {
        if (input == ZERO) {
            throw new MenuException(MenuExceptionCode.INPUT_ZERO_EXIT);
        }
    }
}
