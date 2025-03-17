package module.menu.type;

import module.io.input.exception.InputException;
import module.io.input.type.InputExceptionCode;

import java.util.Arrays;


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
        return Arrays.stream(Category.values())
                .filter(category -> category.value == value)
                .findFirst()
                .orElseThrow(() -> new InputException(InputExceptionCode.INPUT_WRONG));
    }
}
