package module.menu.service;

import module.menu.domain.model.Menu;
import module.menu.domain.model.MenuItem;
import module.menu.exception.MenuException;
import module.menu.type.Category;
import module.menu.type.MenuExceptionCode;

import java.util.ArrayList;
import java.util.List;

public class MenuService {
    private final List<Menu> menuList;

    public MenuService(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public List<MenuItem> getMenuItemList(Category category) {
        return getMenu(category).getMenuItemList();
    }

    public Menu getMenu(Category category) {
        return menuList.stream()
                .filter(x -> x.getCategory().equals(category))
                .findFirst()
                .orElseThrow(() -> new MenuException(MenuExceptionCode.EMPTY_MENU));
    }

    public MenuItem getMenuItem(Category category, int input) {
        Menu menu = getMenu(category);
        MenuItem menuItem = menu.getMenuItemList().stream()
                .filter(x -> x.getId() == input)
                .findFirst()
                .orElseThrow(() -> new MenuException(MenuExceptionCode.EMPTY_MENU_ITEM));

        System.out.println("선택한 메뉴 : " + menuItem.getName() + "     | W "
                + menuItem.getPrice() + "       | " + menuItem.getDescription());

        return menuItem;
    }

    public void inputTestData() {
        List<MenuItem> hamburgerList = new ArrayList<>();
        hamburgerList.add(new MenuItem(1, "hamburger", 4500, "기본 햄버거"));
        hamburgerList.add(new MenuItem(2, "cheese hamburger", 5000, "치즈 햄버거"));
        hamburgerList.add(new MenuItem(3, "bacon hamburger", 5500, "베이컨 햄버거"));
        hamburgerList.add(new MenuItem(4, "chicken hamburger", 5500, "치킨버거"));

        List<MenuItem> drinkList = new ArrayList<>();
        drinkList.add(new MenuItem(1, "coke", 2000, "콜라"));
        drinkList.add(new MenuItem(2, "cider", 2000, "사이다"));
        drinkList.add(new MenuItem(3, "fanta", 2000, "환타"));
        drinkList.add(new MenuItem(4, "coffee", 2000, "커피"));

        List<MenuItem> dessertList = new ArrayList<>();
        dessertList.add(new MenuItem(1, "pancake", 5000, "팬 케이크"));
        dessertList.add(new MenuItem(2, "snack wrap", 2500, "스낵랩"));
        dessertList.add(new MenuItem(3, "chicken tender", 2000, "치킨 텐더"));
        dessertList.add(new MenuItem(4, "cheese ball", 1500, "치즈 볼"));

        menuList.add(new Menu(hamburgerList, Category.HAMBURGER));
        menuList.add(new Menu(drinkList, Category.DRINKS));
        menuList.add(new Menu(dessertList, Category.DESSERTS));
    }

}
