package module.menu.type;

public enum Category {
    HAMBURGER(1), DRINKS(2), DESSERTS(3);
    private final int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Category fromCategoryVal(int value) {
        for (Category category : Category.values()) {
            if (category.getValue() == value) {
                return category;
            }
        }

        throw new IllegalArgumentException("옳바르지 않은 입력값입니다.");
    }
}
