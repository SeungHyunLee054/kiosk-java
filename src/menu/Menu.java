package menu;

import exception.CustomException;
import type.Category;

import java.util.ArrayList;
import java.util.List;

import static type.CustomExceptionCode.EMPTY_MENU;

public class Menu {
    private final List<MenuItem> hamburgerList = new ArrayList<>();
    private final List<MenuItem> drinkList = new ArrayList<>();
    private final List<MenuItem> dessertList = new ArrayList<>();

    public List<MenuItem> getMenuList(Category category) {
        return switch (category) {
            case HAMBURGER -> hamburgerList;
            case DRINKS -> drinkList;
            case DESSERTS -> dessertList;
        };
    }

    public void addMenuItem(MenuItem menuItem) {
        switch (menuItem.getCategory()) {
            case HAMBURGER -> hamburgerList.add(menuItem);
            case DRINKS -> drinkList.add(menuItem);
            case DESSERTS -> dessertList.add(menuItem);
        }
    }

    public void inputTestData() {
        hamburgerList.add(new MenuItem(1, Category.HAMBURGER, "hamburger", 4500, "기본 햄버거"));
        hamburgerList.add(new MenuItem(2, Category.HAMBURGER, "cheese hamburger", 5000, "치즈 햄버거"));
        hamburgerList.add(new MenuItem(3, Category.HAMBURGER, "bacon hamburger", 5500, "베이컨 햄버거"));
        hamburgerList.add(new MenuItem(4, Category.HAMBURGER, "chicken hamburger", 5500, "치킨버거"));

        drinkList.add(new MenuItem(1, Category.DRINKS, "coke", 2000, "콜라"));
        drinkList.add(new MenuItem(2, Category.DRINKS, "cider", 2000, "사이다"));
        drinkList.add(new MenuItem(3, Category.DRINKS, "fanta", 2000, "환타"));
        drinkList.add(new MenuItem(4, Category.DRINKS, "coffee", 2000, "커피"));

        dessertList.add(new MenuItem(1, Category.DESSERTS, "pancake", 5000, "팬 케이크"));
        dessertList.add(new MenuItem(2, Category.DESSERTS, "snack wrap", 2500, "스낵랩"));
        dessertList.add(new MenuItem(3, Category.DESSERTS, "chicken tender", 2000, "치킨 텐더"));
        dessertList.add(new MenuItem(4, Category.DESSERTS, "cheese ball", 1500, "치즈 볼"));
    }

    public MenuItem getMenuItem(int id, Category category) {
        return switch (category) {
            case HAMBURGER -> hamburgerList.stream()
                    .filter(x -> x.getId() == id)
                    .findFirst()
                    .orElseThrow(()->new CustomException(EMPTY_MENU));
            case DRINKS -> drinkList.stream()
                    .filter(x -> x.getId() == id)
                    .findFirst()
                    .orElseThrow(()->new CustomException(EMPTY_MENU));
            case DESSERTS -> dessertList.stream()
                    .filter(x -> x.getId() == id)
                    .findFirst()
                    .orElseThrow(()->new CustomException(EMPTY_MENU));
        };
    }
}
