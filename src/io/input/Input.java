package io.input;

import cart.domain.model.Cart;
import cart.service.CartService;
import io.input.exception.InputException;
import io.input.type.Discount;
import kiosk.Kiosk;
import menu.domain.model.Menu;
import menu.type.Category;

import java.util.List;
import java.util.Scanner;

import static io.input.type.InputExceptionCode.*;

public class Input {
    private final int ZERO = 0;
    private final String ONE = "1";
    private final String TWO = "2";

    private final Scanner scanner = new Scanner(System.in);
    private final CartService cartService = new CartService();

    public Menu inputMenu() {
        System.out.print("id를 입력해 주세요 : ");
        String strId = scanner.nextLine();
        if (isZero(strId)) {
            throw new InputException(INPUT_ZERO_EXIT);
        }
        long id = Long.parseLong(strId);

        System.out.print("종류를 입력해 주세요 : ");
        String strCategory = scanner.nextLine();
        if (isZero(strCategory)) {
            throw new InputException(INPUT_ZERO_EXIT);
        }
        Category category = Category.fromCategoryVal(Integer.parseInt(strCategory));

        System.out.print("메뉴 명을 입력해 주세요 : ");
        String name = scanner.nextLine();
        if (isZero(name)) {
            throw new InputException(INPUT_ZERO_EXIT);
        }

        System.out.print("가격을 입력해 주세요(원) : ");
        String strPrice = scanner.nextLine();
        if (isZero(strPrice)) {
            throw new InputException(INPUT_ZERO_EXIT);
        }
        int price = Integer.parseInt(strPrice);

        System.out.print("설명을 입력해주세요 : ");
        String description = scanner.nextLine();
        if (isZero(description)) {
            throw new InputException(INPUT_ZERO_EXIT);
        }

        return new Menu(id, category, name, price, description);
    }

    public Category selectCategory(List<Cart> cartList, boolean orderMenuFlag) {
        String input = scanner.nextLine();
        if (isZero(input)) {
            throw new InputException(INPUT_ZERO_EXIT);
        }

        int intInput = Integer.parseInt(input);
        if (intInput == 4 && orderMenuFlag) {
            confirmOrderMenu(cartList);
        } else if (intInput == 5 && orderMenuFlag) {
            cartService.removeCartList();
            Kiosk.setOrderMenuFlag(false);
            throw new InputException(CANCEL_CONFIRM_ORDER);
        }
        return Category.fromCategoryVal(Integer.parseInt(input));
    }

    public int selectMenu() {
        String strMenu = scanner.nextLine();
        if (isZero(strMenu)) {
            throw new InputException(INPUT_ZERO_BACK);
        }

        return Integer.parseInt(strMenu);
    }

    public List<Cart> addCart(Menu menu) {
        System.out.println(menu.getName() + "     | W "
                + menu.getPrice() + "       | " + menu.getDescription());
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인     2. 취소");
        String select = scanner.nextLine();
        if (ONE.equals(select)) {
            return cartService.addCartItem(menu);
        } else if (TWO.equals(select)) {
            throw new InputException(CANCEL_ADD_CART);
        } else {
            throw new InputException(INPUT_WRONG);
        }
    }

    public void checkOrderMenu() {
        System.out.println("[ Order Menu ]");
        System.out.println("4. Orders");
        System.out.println("5. Cancel");
    }

    private void confirmOrderMenu(List<Cart> cartList) {
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println("[ Orders ]");

        int sum = 0;
        for (Cart cart : cartList) {
            System.out.println(cart.getMenu().getName() + "     | W " + cart.getMenu().getPrice()
                    + "       | " + cart.getMenu().getDescription());
            sum += cart.getMenu().getPrice() * cart.getQuantity();
        }

        System.out.println("[ Total Price ]");
        System.out.println("W " + sum);

        System.out.println("1. 주문   2. 메뉴판");
        String input = scanner.nextLine();
        if (ONE.equals(input)) {
            discountOrder(sum);
        } else if (TWO.equals(input)) {
            throw new InputException(CANCEL_CONFIRM_ORDER);
        } else {
            throw new InputException(INPUT_WRONG);
        }
    }

    private void discountOrder(int sum) {
        System.out.println("할인 정보를 입력해주세요.");
        for (Discount discount : Discount.values()) {
            System.out.println(discount.getValue() + ". " + discount.getName() + " : "
                    + discount.getDiscountPercent() + "%");
        }
        String discountPerson = scanner.nextLine();
        int intDiscountPerson;
        try {
            intDiscountPerson = Integer.parseInt(discountPerson);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("잘못된 값을 입력하였습니다.");
        }

        Discount discount = Discount.fromDiscountPercent(intDiscountPerson);
        sum = (int) (sum * ((100 - discount.getDiscountPercent()) * 0.01));
        System.out.println("주문이 완료되었습니다. 금액은 W " + sum + " 입니다.");
        cartService.removeCartList();
        Kiosk.setOrderMenuFlag(false);
    }

    public void closeScanner() {
        scanner.close();
    }


    private boolean isZero(String str) {
        int intStr;
        try {
            intStr = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("잘못된 값을 입력하였습니다.");
        }

        return ZERO == intStr;
    }
}
