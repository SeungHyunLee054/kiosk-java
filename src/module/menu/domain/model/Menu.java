package module.menu.domain.model;

import java.util.List;

public class Menu {
    private final List<MenuItem> menuItemList;
    private final String category;

    public Menu(List<MenuItem> menuItem, String category) {
        this.menuItemList = menuItem;
        this.category = category;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public String getCategory() {
        return category;
    }
}
