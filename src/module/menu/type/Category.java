package module.menu.type;

import module.io.input.exception.InputException;
import module.io.input.type.InputExceptionCode;


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

        throw new InputException(InputExceptionCode.INPUT_WRONG);
    }
}
