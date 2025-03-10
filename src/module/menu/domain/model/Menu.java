package module.menu.domain.model;

import module.menu.type.Category;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItemList;
    private final Category category;

    public Menu(List<MenuItem> menuItemList, Category category) {
        this.menuItemList = menuItemList;
        this.category = category;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public Category getCategory() {
        return category;
    }
}
