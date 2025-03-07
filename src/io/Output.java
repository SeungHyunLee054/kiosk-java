package io;

import menu.MenuItem;
import type.Category;

import java.util.List;

public class Output {

    public void printMenu(List<MenuItem> menuItemList) {
        System.out.println("[ " + menuItemList.get(0).getCategory().name() + " MENU ]");
        for (MenuItem menuItem : menuItemList) {
            System.out.println(menuItem.getId() + ". " + menuItem.getName() + "     | W " + menuItem.getPrice()
                    + "       | " + menuItem.getDescription());
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

    public void printMenuItem(MenuItem menuItem) {
        System.out.println("선택한 메뉴 : " + menuItem.getId() + ". " + menuItem.getName() + "     | W "
                + menuItem.getPrice() + "       | " + menuItem.getDescription());
    }
}
