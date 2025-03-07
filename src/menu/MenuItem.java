package menu;

import type.Category;

public class MenuItem {
    private final long id;
    private final Category category;
    private final String name;
    private final int price;
    private final String description;

    public MenuItem(long id, Category category, String name, int price, String description) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
