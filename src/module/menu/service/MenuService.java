package module.menu.service;

import module.menu.domain.model.Menu;
import module.menu.exception.MenuException;
import module.menu.type.Category;

import java.util.ArrayList;
import java.util.List;

import static module.menu.type.MenuExceptionCode.EMPTY_MENU;

public class MenuService {
    private final List<Menu> hamburgerList = new ArrayList<>();
    private final List<Menu> drinkList = new ArrayList<>();
    private final List<Menu> dessertList = new ArrayList<>();

    public void getMenuList(Category category) {
        List<Menu> menuList = switch (category) {
            case HAMBURGER -> hamburgerList;
            case DRINKS -> drinkList;
            case DESSERTS -> dessertList;
        };

        System.out.println("[ " + menuList.get(0).getCategory().name() + " MENU ]");
        for (Menu menu : menuList) {
            System.out.println(menu.getId() + ". " + menu.getName() + "     | W " + menu.getPrice()
                    + "       | " + menu.getDescription());
        }
        System.out.println("0. 뒤로가기");
    }

    public Menu getMenu(Category category, int input) {
        Menu menu = switch (category) {
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

        System.out.println("선택한 메뉴 : " + menu.getName() + "     | W "
                + menu.getPrice() + "       | " + menu.getDescription());

        return menu;
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

}
