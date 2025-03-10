package module.menu.domain.model;

public class MenuItem {
    private final long id;
    private final String name;
    private final int price;
    private final String description;

    public MenuItem(long id, String name, int price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public long getId() {
        return id;
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
