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

    /**
     * 카테고리에 해당하는 메뉴의 리스트를 가져오는 메서드
     *
     * @param category 선택한 카테고리
     * @return 해당 카테고리의 메뉴 리스트
     */
    public List<MenuItem> getMenuItemList(Category category) {
        return getMenu(category).getMenuItemList();
    }

    /**
     * 메뉴 리스트에서 해당 카테고리의 메뉴를 가져오는 메서드
     *
     * @param category 선택한 카테고리
     * @return 해당 카테고리의 메뉴
     */
    public Menu getMenu(Category category) {
        return menuList.stream()
                .filter(x -> x.getCategory().equals(category))
                .findFirst()
                .orElseThrow(() -> new MenuException(MenuExceptionCode.EMPTY_MENU));
    }

    /**
     * 해당 카테고리의 메뉴에서 내가 선택한 메뉴 정보를 가져오는 메서드
     *
     * @param category 선택한 카테고리
     * @param input    선택한 메뉴 id
     * @return 선택한 메뉴 정보
     */
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

    /**
     * 초기 데이터 init
     */
    public void inputTestData() {
        List<MenuItem> hamburgerList = new ArrayList<>();
        hamburgerList.add(MenuItem.builder()
                .id(1)
                .name("hamburger")
                .price(4500)
                .description("기본 햄버거")
                .build());
        hamburgerList.add(MenuItem.builder()
                .id(2)
                .name("cheese hamburger")
                .price(5000)
                .description("치즈 햄버거")
                .build());
        hamburgerList.add(MenuItem.builder()
                .id(3)
                .name("bacon hamburger")
                .price(5500)
                .description("베이컨 햄버거")
                .build());
        hamburgerList.add(MenuItem.builder()
                .id(4)
                .name("chicken hamburger")
                .price(5500)
                .description("치킨버거")
                .build());

        List<MenuItem> drinkList = new ArrayList<>();
        drinkList.add(MenuItem.builder()
                .id(1)
                .name("coke")
                .price(2000)
                .description("콜라")
                .build());
        drinkList.add(MenuItem.builder()
                .id(2)
                .name("cider")
                .price(2000)
                .description("사이다")
                .build());
        drinkList.add(MenuItem.builder()
                .id(3)
                .name("fanta")
                .price(2000)
                .description("환타")
                .build());
        drinkList.add(MenuItem.builder()
                .id(4)
                .name("coffee")
                .price(2000)
                .description("커피")
                .build());

        List<MenuItem> dessertList = new ArrayList<>();
        drinkList.add(MenuItem.builder()
                .id(1)
                .name("pancake")
                .price(5000)
                .description("팬 케이크")
                .build());
        drinkList.add(MenuItem.builder()
                .id(2)
                .name("snack wrap")
                .price(2500)
                .description("스낵랩")
                .build());
        drinkList.add(MenuItem.builder()
                .id(3)
                .name("chicken tender")
                .price(2000)
                .description("치킨 텐더")
                .build());
        drinkList.add(MenuItem.builder()
                .id(4)
                .name("cheese ball")
                .price(1500)
                .description("치즈 볼")
                .build());

        menuList.add(new Menu(hamburgerList, Category.HAMBURGER));
        menuList.add(new Menu(drinkList, Category.DRINKS));
        menuList.add(new Menu(dessertList, Category.DESSERTS));
    }

}
