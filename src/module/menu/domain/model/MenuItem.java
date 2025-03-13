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

    /**
     * builder를 직접 구현
     * @return MenuItem
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private long id;
        private String name;
        private int price;
        private String description;

        Builder() {
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(this.id, this.name, this.price, this.description);
        }
    }
}
