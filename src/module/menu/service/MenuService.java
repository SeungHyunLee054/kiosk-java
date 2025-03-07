package module.menu.service;

import module.cart.domain.model.Cart;
import module.cart.service.CartService;
import module.io.input.Input;
import module.io.input.type.Discount;
import module.io.output.Output;
import module.kiosk.Kiosk;
import module.menu.domain.model.Menu;
import module.menu.exception.MenuException;
import module.menu.type.Category;

import java.util.ArrayList;
import java.util.List;

import static module.menu.type.MenuExceptionCode.*;

public class MenuService {
    private final List<Menu> hamburgerList = new ArrayList<>();
    private final List<Menu> drinkList = new ArrayList<>();
    private final List<Menu> dessertList = new ArrayList<>();

    private final Input input;
    private final Output output;
    private final CartService cartService;

    private final int ZERO = 0;
    private final int ONE = 1;
    private final int TWO = 2;

    public MenuService(Input input, Output output, CartService cartService) {
        this.input = input;
        this.output = output;
        this.cartService = cartService;

    }

    public List<Menu> getMenuList(Category category) {
        return switch (category) {
            case HAMBURGER -> hamburgerList;
            case DRINKS -> drinkList;
            case DESSERTS -> dessertList;
        };
    }

    public void inputTestData() {
        hamburgerList.add(new Menu(1, Category.HAMBURGER, "hamburger", 4500, "기본 햄버거"));
        hamburgerList.add(new Menu(2, Category.HAMBURGER, "cheese hamburger", 5000, "치즈 햄버거"));
        hamburgerList.add(new Menu(3, Category.HAMBURGER, "bacon hamburger", 5500, "베이컨 햄버거"));
        hamburgerList.add(new Menu(4, Category.HAMBURGER, "chicken hamburger", 5500, "치킨버거"));

        drinkList.add(new Menu(1, Category.DRINKS, "coke", 2000, "콜라"));
        drinkList.add(new Menu(2, Category.DRINKS, "cider", 2000, "사이다"));
        drinkList.add(new Menu(3, Category.DRINKS, "fanta", 2000, "환타"));
        drinkList.add(new Menu(4, Category.DRINKS, "coffee", 2000, "커피"));

        dessertList.add(new Menu(1, Category.DESSERTS, "pancake", 5000, "팬 케이크"));
        dessertList.add(new Menu(2, Category.DESSERTS, "snack wrap", 2500, "스낵랩"));
        dessertList.add(new Menu(3, Category.DESSERTS, "chicken tender", 2000, "치킨 텐더"));
        dessertList.add(new Menu(4, Category.DESSERTS, "cheese ball", 1500, "치즈 볼"));
    }

    public Category selectCategory(List<Cart> cartList, boolean orderMenuFlag) {
        int input = this.input.inputInt();
        if (input == ZERO) {
            throw new MenuException(INPUT_ZERO_EXIT);
        }

        if (input == 4 && orderMenuFlag) {
            int sum = output.printConfirmOrderMenu(cartList);

            int nextInput = this.input.inputInt();
            if (ONE == nextInput) {
                discountOrder(sum);
                throw new MenuException(CONFIRM_ORDER);
            } else if (TWO == nextInput) {
                throw new MenuException(CANCEL_CONFIRM_ORDER);
            } else {
                throw new MenuException(INPUT_WRONG);
            }
        } else if (input == 5 && orderMenuFlag) {
            cartService.removeCartList();
            Kiosk.setOrderMenuFlag(false);
            throw new MenuException(CANCEL_CONFIRM_ORDER);
        }
        return Category.fromCategoryVal(input);
    }

    public Menu getMenu(Category category) {
        int input = this.input.inputInt();

        return switch (category) {
            case HAMBURGER -> hamburgerList.stream()
                    .filter(x -> x.getId() == input)
                    .findFirst()
                    .orElseThrow(() -> new MenuException(EMPTY_MENU));
            case DRINKS -> drinkList.stream()
                    .filter(x -> x.getId() == input)
                    .findFirst()
                    .orElseThrow(() -> new MenuException(EMPTY_MENU));
            case DESSERTS -> dessertList.stream()
                    .filter(x -> x.getId() == input)
                    .findFirst()
                    .orElseThrow(() -> new MenuException(EMPTY_MENU));
        };
    }

    private void discountOrder(int sum) {
        output.printDiscountOrder(sum);

        int input = this.input.inputInt();

        Discount discount = Discount.fromDiscountPercent(input);
        sum = (int) (sum * ((100 - discount.getDiscountPercent()) * 0.01));
        System.out.println("주문이 완료되었습니다. 금액은 W " + sum + " 입니다.");
        cartService.removeCartList();
        Kiosk.setOrderMenuFlag(false);
    }

}
