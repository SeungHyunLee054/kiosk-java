package io.output;

import cart.service.CartService;
import menu.domain.model.Menu;
import menu.type.Category;

import java.util.List;

public class Output {
    private final CartService cartService = new CartService();

    public void printMenu(List<Menu> menuList) {
        System.out.println("[ " + menuList.get(0).getCategory().name() + " MENU ]");
        for (Menu menu : menuList) {
            System.out.println(menu.getId() + ". " + menu.getName() + "     | W " + menu.getPrice()
                    + "       | " + menu.getDescription());
        }
        System.out.println("0. 뒤로가기");
    }

    public void printCategory() {
        System.out.println("[ MENU ]");
        for (Category category : Category.values()) {
            System.out.println(category.getValue() + ". " + category.name());
        }
        System.out.println("0. 종료");
    }

    public void printSelectedMenu(Menu menu) {
        System.out.println("선택한 메뉴 : " + menu.getName() + "     | W "
                + menu.getPrice() + "       | " + menu.getDescription());
    }

    public void printCart(Menu menu) {
        System.out.println(menu.getName() + " 이 장바구니에 추가되었습니다.");
    }
}
